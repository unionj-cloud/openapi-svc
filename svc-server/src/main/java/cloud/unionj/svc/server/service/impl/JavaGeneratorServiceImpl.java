package cloud.unionj.svc.server.service.impl;

import cloud.unionj.svc.server.service.JavaGeneratorService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.core.util.ZipUtil;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.shared.invoker.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class JavaGeneratorServiceImpl implements JavaGeneratorService {
  private final Logger log = LoggerFactory.getLogger(JavaGeneratorServiceImpl.class);
  private final String generatorResourceRoot = "/generator";
  private final String pomTemplateName = "pom-template.xml";
  private final String generatorPomTemplateName = "generator-pom-template.xml";
  @Value("${tmp.root}")
  private String tempRoot;
  @Value("${maven.home}")
  private String mavenHome;
  @Value("${svc.java.packageType:zip}")
  private String packageType;
  @Value("${generator.source.dir:}")
  private String generatorSourceDir;
  @Value("${svc-client-java.version:}")
  private String svcClientJavaVersion;


  @Override
  @SneakyThrows
  public File generate(InputStream openapiJsonInputStream,
                       String openapiJsonFileName,
                       String groupId,
                       String artifactId,
                       String version,
                       String name,
                       String invokerPackage,
                       String apiPackage,
                       String modelPackage) {
    String outputRoot = tempRoot.replaceAll("[\\/|\\\\]+", ReUtil.escape(File.separator));
    String nowTimeStr = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
    String output = outputRoot + (outputRoot.endsWith(File.separator) ? "" : File.separator) + nowTimeStr;
    String inputSpec = output + File.separator + openapiJsonFileName;
    FileUtil.writeFromStream(openapiJsonInputStream, inputSpec);
    //如果配置了generator资源文件目录，则从配置的路径读取文件
    if (StrUtil.isNotEmpty(generatorSourceDir)) {
      Arrays.asList(FileUtil.newFile(generatorSourceDir).listFiles()).forEach(file -> FileUtil.copy(file.getAbsolutePath(), output, true));
    }
    //否则，读取classpath:resources下内置generator资源文件
    else {
      PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
      Arrays.asList(resolver.getResources(generatorResourceRoot + "/**/*.*")).forEach(resource -> {
        try {
          String relativePath;
          if (resource instanceof ClassPathResource) {
            relativePath = ((ClassPathResource) resource).getPath().replaceFirst(generatorResourceRoot.substring(1), "");
          } else {
            String path = resource.getURI().getPath();
            relativePath = path.substring(path.lastIndexOf(generatorResourceRoot + "/")).replaceFirst(generatorResourceRoot, "");
          }
          if (StrUtil.isNotEmpty(relativePath)) {
            FileUtil.writeFromStream(resource.getInputStream(), output + File.separator + relativePath.replaceAll("[\\/|\\\\]+", ReUtil.escape(File.separator)));
          }
        } catch (IOException e) {
          log.error("复制资源文件[" + resource.getDescription() + "]失败！", e);
        }
      });
    }

    if (StringUtils.isEmpty(groupId)) {
      groupId = "cloud.unionj";
    }
    //groupId只允许数字、字母、横杠、点
    groupId = groupId.replaceAll("[^A-Za-z0-9\\-\\.]", ".");
    if (StringUtils.isEmpty(artifactId)) {
      artifactId = "generator";
    }
    //artifactId只允许数字、字母、横杠
    artifactId = artifactId.replaceAll("[^A-Za-z0-9\\-]", "-");
    if (StringUtils.isEmpty(version)) {
      version = "1.0.0";
    }
    //version只允许数字、字母、下划线、横杠、点
    version = version.replaceAll("[^A-Za-z0-9\\-_\\.]", ".");
    if (StringUtils.isEmpty(name)) {
      name = groupId + "-" + artifactId;
    }
    //name只允许数字、字母、横杠
    name = name.replaceAll("[^A-Za-z0-9\\-]", "-");
    if (StringUtils.isEmpty(invokerPackage)) {
      invokerPackage = groupId + "." + artifactId;
    }
    //invokerPackage只允许数字、字母、下划线、点
    invokerPackage = invokerPackage.replaceAll("[^A-Za-z0-9_\\.]", ".");
    if (StringUtils.isEmpty(apiPackage)) {
      apiPackage = invokerPackage + ".client";
    }
    //apiPackage只允许数字、字母、下划线、点
    apiPackage = apiPackage.replaceAll("[^A-Za-z0-9_\\.]", ".");
    if (StringUtils.isEmpty(modelPackage)) {
      modelPackage = invokerPackage + ".vo";
    }
    //modelPackage只允许数字、字母、下划线、点
    modelPackage = modelPackage.replaceAll("[^A-Za-z0-9_\\.]", ".");

    String outputGeneratorPom = fillGeneratorPom(inputSpec, output, invokerPackage, apiPackage, modelPackage);

    Invoker invoker = new DefaultInvoker();
    invoker.setMavenHome(FileUtil.newFile(mavenHome));

    InvocationRequest generateRequest = new DefaultInvocationRequest();
    generateRequest.setPomFile(new File(outputGeneratorPom));
    generateRequest.setGoals(Collections.singletonList("compile"));
    invoker.execute(generateRequest);

    //删除多余文件和文件夹
    FileUtil.del(output + File.separator + "api");
    FileUtil.del(output + File.separator + "gradle");
    FileUtil.del(output + File.separator + "mustache");
    FileUtil.del(output + File.separator + ".openapi-generator");
    FileUtil.del(output + File.separator + ".openapi-generator-ignore");
    FileUtil.del(output + File.separator + "target");
    //生成代码用的pom文件生成代码后就没用了
    FileUtil.del(outputGeneratorPom);

    //公共代码已经被独立为项目进行改造为生成代码的依赖包，这里删除生成出来的原有的公共代码（被apiPackage和modelPackage包中依赖的除外）
    File outputJavaDir = FileUtil.newFile(output + (".src.main.java." + invokerPackage).replace(".", File.separator));
    List<String> remainFileList = Lists.newArrayList(
        apiPackage.substring(apiPackage.lastIndexOf(".") + 1),
        modelPackage.substring(modelPackage.lastIndexOf(".") + 1),
        "CollectionFormats.java",
        "StringUtil.java"
    );
    if (outputJavaDir.exists() && outputJavaDir.isDirectory()) {
      for (File sub : outputJavaDir.listFiles()) {
        //只留下apiPackage和modelPackage的代码，另外，CollectionFormats.java被apiPackage中的代码引用，StringUtil.java被CollectionFormats.java引用
        if (remainFileList.contains(sub.getName())) {
          continue;
        }
        FileUtil.del(sub);
      }
    }

    //将用户传入的项目信息填充至内置好的pom文件，并生成到output目录下
    String outputPom = fillPom(output, groupId, artifactId, version, name);

    if (!StrUtil.equalsAnyIgnoreCase(packageType, "jar", "zip")) {
      packageType = "zip";
    }

    String fileNameWithoutFix = artifactId + "-" + version;

    if (StrUtil.equalsIgnoreCase(packageType, "jar")) {
      InvocationRequest packageRequest = new DefaultInvocationRequest();
      packageRequest.setPomFile(new File(outputPom));
      packageRequest.setGoals(Collections.singletonList("clean compile assembly:single -DskipTests"));
      InvocationResult execute = invoker.execute(packageRequest);
      if (execute.getExitCode() == 0) {
        return FileUtil.newFile(output + File.separator + "target" + File.separator + fileNameWithoutFix + "-jar-with-dependencies.jar");
      } else {
        //maven打包失败，删除打包生成的target目录
        FileUtil.del(output + File.separator + "target");
      }
    }
    File zip = ZipUtil.zip(output, FileUtil.getParent(output, 1) + File.separator + fileNameWithoutFix + "-generate-" + nowTimeStr + ".zip");
    return zip;
  }

  private void replaceInvokerPackage(File file, String invokerPackage) {
    if (FileUtil.isDirectory(file)) {
      for (File subFile : file.listFiles()) {
        replaceInvokerPackage(subFile, invokerPackage);
      }
    } else {
      String content = FileUtil.readUtf8String(file);
      if (StrUtil.isEmpty(content) || !content.contains("${invokerPackage}")) {
        return;
      }
      String newContent = content.replace("${invokerPackage}", invokerPackage);
      FileUtil.writeUtf8String(newContent, file);
    }
  }

  private String fillPom(String output, String groupId, String artifactId, String version, String name) {
    String outputPomTemplate = output + File.separator + pomTemplateName;
    Document document = XmlUtil.readXML(outputPomTemplate);
    Node groupIdNode = document.getElementsByTagName("groupId").item(0);
    groupIdNode.setTextContent(groupId);
    Node artifactIdNode = document.getElementsByTagName("artifactId").item(0);
    artifactIdNode.setTextContent(artifactId);
    Node versionNode = document.getElementsByTagName("version").item(0);
    versionNode.setTextContent(version);
    Node nameNode = document.getElementsByTagName("name").item(0);
    nameNode.setTextContent(name);
    if (StrUtil.isNotEmpty(svcClientJavaVersion)) {
      Node inputSpecNode = document.getElementsByTagName("svc-client-java.version").item(0);
      inputSpecNode.setTextContent(svcClientJavaVersion);
    }
    String outputPom = output + File.separator + "pom.xml";
    XmlUtil.toFile(document, outputPom);
    FileUtil.del(outputPomTemplate);
    return outputPom;
  }

  private String fillGeneratorPom(String inputSpec, String output, String invokerPackage, String apiPackage, String modelPackage) {
    String outputGeneratorPomTemplate = output + File.separator + generatorPomTemplateName;
    Document document = XmlUtil.readXML(outputGeneratorPomTemplate);
    Node inputSpecNode = document.getElementsByTagName("generator.inputSpec").item(0);
    inputSpecNode.setTextContent(inputSpec);
    Node invokerPackageNode = document.getElementsByTagName("generator.invokerPackage").item(0);
    invokerPackageNode.setTextContent(invokerPackage);
    Node apiPackageNode = document.getElementsByTagName("generator.apiPackage").item(0);
    apiPackageNode.setTextContent(apiPackage);
    Node modelPackageNode = document.getElementsByTagName("generator.modelPackage").item(0);
    modelPackageNode.setTextContent(modelPackage);
    String outputGeneratorPom = output + File.separator + "generator-pom.xml";
    XmlUtil.toFile(document, outputGeneratorPom);
    FileUtil.del(outputGeneratorPomTemplate);
    return outputGeneratorPom;
  }
}
