package cloud.unionj.svc.server.service.impl;

import cloud.unionj.svc.server.enums.JavaPackageType;
import cloud.unionj.svc.server.service.JavaGeneratorService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.*;
import com.google.common.collect.Lists;
import lombok.*;
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
  public File generate(InputStream openapiJsonInputStream,
                       String openapiJsonFileName,
                       String groupId,
                       String artifactId,
                       String version,
                       String name,
                       String invokerPackage,
                       String apiPackage,
                       String modelPackage,
                       List<JavaPackageType> packageTypes) {
    String outputRoot = tempRoot.replaceAll("[\\/|\\\\]+", ReUtil.escape(File.separator));
    String nowTimeStr = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
    String output = outputRoot + (outputRoot.endsWith(File.separator) ? "" : File.separator) + nowTimeStr;
    String inputSpec = output + File.separator + openapiJsonFileName;

    try {
      FileUtil.writeFromStream(openapiJsonInputStream, inputSpec);

      //1、复制模版文件到输出目录
      copyTemplates(output);

      //2、校验和初始化入参
      JavaGeneratorParam generatorParam = new JavaGeneratorParam(output, inputSpec, groupId, artifactId, version, name, invokerPackage, apiPackage, modelPackage, packageTypes);
      generatorParam.init();

      //3、填充模版并生成代码
      generateCode(generatorParam);

      List<File> resultFilePathList = new ArrayList<>();
      for (JavaPackageType packageType : packageTypes) {
        File resultFile = doPackage(packageType, output, generatorParam.getFileNamePrefix());
        resultFilePathList.add(resultFile);
      }

      File result = ZipUtil.zip(
          FileUtil.newFile(outputRoot + File.separator + generatorParam.getFileNamePrefix() + "-" + nowTimeStr + ".zip"),
          false,
          resultFilePathList.toArray(new File[resultFilePathList.size()])
      );
      return result;
    } catch (Exception e) {
      log.error("JAVA代码生成失败！", e);
      return null;
    } finally {
      FileUtil.del(output);
    }
  }

  private void copyTemplates(String output) throws IOException {
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
  }

  private File doPackage(JavaPackageType packageType, String output, String fileNamePrefix) throws MavenInvocationException {

    String dirName = packageType.getName();
    String mvnGoal;
    String pomName;
    String[] includePrefixes;
    String[] excludePrefixes;

    switch (packageType) {
      case SIMPLE_JAR:
        includePrefixes = new String[]{
            "src",
            "pom.xml"
        };
        excludePrefixes = new String[]{
            "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
            "src.main.resources.META-INF".replace(".", File.separator)
        };
        mvnGoal = "clean package -DskipTests";
        pomName = "pom.xml";
        break;
      case SIMPLE_SOURCE_JAR:
        includePrefixes = new String[]{
            "src",
            "pom.xml"
        };
        excludePrefixes = new String[]{
            "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
            "src.main.resources.META-INF".replace(".", File.separator)
        };
        mvnGoal = "source:jar";
        pomName = "pom.xml";
        break;
      case SIMPLE_FAT_JAR:
        includePrefixes = new String[]{
            "src",
            "pom.xml"
        };
        excludePrefixes = new String[]{
            "src.main.java.cloud.unionj.svc.client.java".replace(".", File.separator),
            "src.main.resources.META-INF".replace(".", File.separator)
        };
        mvnGoal = "clean compile assembly:single -DskipTests";
        pomName = "pom.xml";
        break;
      case FULL_JAR:
        includePrefixes = new String[]{
            "src",
            "pom.xml"
        };
        excludePrefixes = null;
        mvnGoal = "clean package -DskipTests";
        pomName = "pom.xml";
        break;
      case FULL_SOURCE_JAR:
        includePrefixes = new String[]{
            "src",
            "pom.xml"
        };
        excludePrefixes = null;
        mvnGoal = "source:jar";
        pomName = "pom.xml";
        break;
      case FULL_FAT_JAR:
        includePrefixes = new String[]{
            "src",
            "pom.xml"
        };
        excludePrefixes = null;
        mvnGoal = "clean compile assembly:single -DskipTests";
        pomName = "pom.xml";
        break;
      case ORIGIN_ZIP:
        includePrefixes = new String[]{
            "src",
            "docs",
            "pom.xml"
        };
        excludePrefixes = null;
        mvnGoal = null;
        pomName = null;
        break;
      default:
        return null;
    }
    log.info("开始打包：" + packageType.getName());
    String dirPath = output + File.separator + dirName;

    copyFiles(output, dirPath, null, includePrefixes, excludePrefixes);

    String resultFilePath = output + File.separator + fileNamePrefix + (StrUtil.isNotEmpty(packageType.getInfix()) ? packageType.getInfix() : "") + packageType.getSuffix();
    if (StrUtil.isNotEmpty(mvnGoal) && StrUtil.isNotEmpty(pomName)) {
      Invoker invoker = new DefaultInvoker();
      invoker.setMavenHome(FileUtil.newFile(mavenHome));
      InvocationRequest request = new DefaultInvocationRequest();
      request.setPomFile(new File(dirPath + File.separator + pomName));
      request.setGoals(Collections.singletonList(mvnGoal));
      request.setQuiet(true);
      invoker.execute(request);
      FileUtil.copy(
          FileUtil.newFile(dirPath + File.separator + "target" + File.separator + fileNamePrefix + packageType.getSuffix()),
          FileUtil.newFile(resultFilePath),
          true);
    } else {
      ZipUtil.zip(dirPath, resultFilePath, false);
    }
    FileUtil.del(dirPath);
    log.info("完成打包：" + packageType.getName());
    return FileUtil.newFile(resultFilePath);
  }

  private void copyFiles(String sourcePath, String targetPath, String relativePath, String[] includePrefixes, String[] excludePrefixes) {
    String sourceAbsolutePath;
    if (StrUtil.isEmpty(relativePath)) {
      relativePath = new String();
      sourceAbsolutePath = sourcePath;
    } else {
      sourceAbsolutePath = sourcePath + File.separator + relativePath;
    }

    File file = FileUtil.newFile(sourceAbsolutePath);
    if (!file.isDirectory()) {
      if (includePrefixes != null && !StrUtil.startWithAny(relativePath, includePrefixes)) {
        return;
      }
      FileUtil.copy(sourceAbsolutePath, targetPath + File.separator + relativePath, true);
      return;
    }
    for (File subFile : file.listFiles()) {
      if (excludePrefixes != null && StrUtil.startWithAny(relativePath, excludePrefixes)) {
        continue;
      }
      copyFiles(sourcePath, targetPath, (StrUtil.isEmpty(relativePath) ? "" : (relativePath + File.separator)) + subFile.getName(), includePrefixes, excludePrefixes);
    }
  }

  private void generateCode(JavaGeneratorParam param) throws MavenInvocationException {
    Invoker invoker = new DefaultInvoker();
    invoker.setMavenHome(FileUtil.newFile(mavenHome));

    String outputGeneratorPom = fillGeneratorPom(param.openapiFilePath, param.output, param.invokerPackage, param.apiPackage, param.modelPackage);
    InvocationRequest request = new DefaultInvocationRequest();
    request.setPomFile(new File(outputGeneratorPom));
    request.setQuiet(true);
    request.setGoals(Collections.singletonList("compile"));
    invoker.execute(request);

    //删除多余文件和文件夹
    FileUtil.del(param.output + File.separator + "api");
    FileUtil.del(param.output + File.separator + "gradle");
    FileUtil.del(param.output + File.separator + "mustache");
    FileUtil.del(param.output + File.separator + ".openapi-generator");
    FileUtil.del(param.output + File.separator + ".openapi-generator-ignore");
    FileUtil.del(param.output + File.separator + "target");
    //生成代码用的pom文件生成代码后就没用了
    FileUtil.del(outputGeneratorPom);

    //删除原有的由openapi-generator提供的公共代码
    File outputJavaDir = FileUtil.newFile(param.output + (".src.main.java." + param.invokerPackage).replace(".", File.separator));
    List<String> remainFileList = Lists.newArrayList(
        param.apiPackage.substring(param.apiPackage.lastIndexOf(".") + 1),
        param.modelPackage.substring(param.modelPackage.lastIndexOf(".") + 1),
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

    //将模板已内置好的公共代码剪切到生成代码后的源码目录
    FileUtil.copy(param.output + File.separator + "core" + File.separator + "src", param.output, true);

    FileUtil.del(param.output + File.separator + "core");

    //将用户传入的项目信息填充至内置好的pom文件，并生成到output目录下
    fillPom(param.output, param.groupId, param.artifactId, param.version, param.name);
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

  private String fillGeneratorPom(String inputSpec, String output, String invokerPackage, String apiPackage, String
      modelPackage) {
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

  @Getter
  @Setter
  @AllArgsConstructor
  public class JavaGeneratorParam {
    String output;
    String openapiFilePath;
    String groupId;
    String artifactId;
    String version;
    String name;
    String invokerPackage;
    String apiPackage;
    String modelPackage;
    List<JavaPackageType> packageTypes;

    public void init() {
      if (StringUtils.isEmpty(this.groupId)) {
        this.groupId = "cloud.unionj";
      }
      //groupId只允许数字、字母、横杠、点
      this.groupId = this.groupId.replaceAll("[^A-Za-z0-9\\-\\.]", ".");
      if (StringUtils.isEmpty(this.artifactId)) {
        this.artifactId = "generator";
      }
      //artifactId只允许数字、字母、横杠
      this.artifactId = this.artifactId.replaceAll("[^A-Za-z0-9\\-]", "-");
      if (StringUtils.isEmpty(this.version)) {
        this.version = "1.0.0";
      }
      //version只允许数字、字母、下划线、横杠、点
      this.version = this.version.replaceAll("[^A-Za-z0-9\\-_\\.]", ".");
      if (StringUtils.isEmpty(this.name)) {
        this.name = this.groupId + "-" + this.artifactId;
      }
      //name只允许数字、字母、横杠
      this.name = this.name.replaceAll("[^A-Za-z0-9\\-]", "-");
      if (StringUtils.isEmpty(this.invokerPackage)) {
        this.invokerPackage = this.groupId + "." + this.artifactId;
      }
      //invokerPackage只允许数字、字母、下划线、点
      this.invokerPackage = this.invokerPackage.replaceAll("[^A-Za-z0-9_\\.]", ".");
      if (StringUtils.isEmpty(this.apiPackage)) {
        this.apiPackage = this.invokerPackage + ".client";
      }
      //apiPackage只允许数字、字母、下划线、点
      this.apiPackage = this.apiPackage.replaceAll("[^A-Za-z0-9_\\.]", ".");
      if (StringUtils.isEmpty(this.modelPackage)) {
        this.modelPackage = this.invokerPackage + ".vo";
      }
      //modelPackage只允许数字、字母、下划线、点
      this.modelPackage = this.modelPackage.replaceAll("[^A-Za-z0-9_\\.]", ".");
    }

    public String getFileNamePrefix() {
      return artifactId + "-" + version;
    }
  }
}
