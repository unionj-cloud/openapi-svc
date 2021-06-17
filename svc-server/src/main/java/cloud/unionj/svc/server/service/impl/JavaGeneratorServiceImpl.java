package cloud.unionj.svc.server.service.impl;

import cloud.unionj.svc.server.service.JavaGeneratorService;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.core.util.ZipUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;


@Service
public class JavaGeneratorServiceImpl implements JavaGeneratorService {

  private final String generatorSourceDir = JavaGeneratorServiceImpl.class.getClassLoader().getResource("generator").getPath();
  private final String pomTemplateName = "pom-template.xml";
  private final String generatorPomTemplateName = "generator-pom-template.xml";
  private final String tempRoot = "/tmp";
  @Value("${maven.home}")
  private String mavenHome;
  @Value("${svc.java.packageType:zip}")
  private String packageType;

  @Override
  @SneakyThrows
  public String generate(MultipartFile openapiJsonFile, String groupId, String artifactId, String version, String name, String invokerPackage, String apiPackage, String modelPackage) {
    if (openapiJsonFile == null || openapiJsonFile.getSize() == 0) {
      throw new Exception("无效的openapi文件");
    }
    String output = tempRoot + File.separator + new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
    String inputSpec = output + File.separator + openapiJsonFile.getOriginalFilename();
    FileUtil.writeFromStream(openapiJsonFile.getInputStream(), inputSpec);
    String generatorSourceDir;
    if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
      generatorSourceDir = this.generatorSourceDir.replaceFirst("/", "");
    } else {
      generatorSourceDir = this.generatorSourceDir;
    }
    Arrays.asList(FileUtil.newFile(generatorSourceDir).listFiles()).forEach(file -> FileUtil.copy(file.getAbsolutePath(), output, true));


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
    //将用户传入的项目信息填充至内置好的pom文件，并生成到output目录下
    String outputPom = fillPom(output, groupId, artifactId, version, name);

    if (!StrUtil.equalsAnyIgnoreCase(packageType, "jar", "zip")) {
      packageType = "zip";
    }

    if (StrUtil.equalsIgnoreCase(packageType, "jar")) {
      InvocationRequest packageRequest = new DefaultInvocationRequest();
      packageRequest.setPomFile(new File(outputPom));
      packageRequest.setGoals(Collections.singletonList("package"));
      InvocationResult execute = invoker.execute(packageRequest);
      if (execute.getExitCode() == 0) {
        return output + File.separator + "target" + File.separator + artifactId + "-" + version + ".jar";
      }
    }

    File zip = ZipUtil.zip(output);
    return zip.getAbsolutePath();
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
