package cloud.unionj.svc.server.controller;

import cloud.unionj.svc.proto.JavaProto;
import cloud.unionj.svc.server.enums.JavaPackageType;
import cloud.unionj.svc.server.service.JavaGeneratorService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Controller
public class JavaProtoImpl implements JavaProto {

  private final JavaGeneratorService javaGeneratorService;

  public JavaProtoImpl(JavaGeneratorService javaGeneratorService) {
    this.javaGeneratorService = javaGeneratorService;
  }

  @Override
  @SneakyThrows
  public ResponseEntity<byte[]> postJavaUpload(MultipartFile file, String groupId, String artifactId, String version, String name, String invokerPackage, String apiPackage, String modelPackage, String packageTypes) {
    if (file == null || file.getSize() == 0) {
      throw new Exception("无效文件");
    }
    InputStream inputStream = file.getInputStream();
    String filename = file.getOriginalFilename();
    return getResponseEntity(inputStream, filename, groupId, artifactId, version, name, invokerPackage, apiPackage, modelPackage, packageTypes);
  }

  @Override
  @SneakyThrows
  public ResponseEntity<byte[]> getJavaUrl(String url, String groupId, String artifactId, String version, String name, String invokerPackage, String apiPackage, String modelPackage, String packageTypes) {
    byte[] bytes = HttpUtil.downloadBytes(url);
    if (bytes == null || bytes.length == 0) {
      throw new Exception("无效文件");
    }
    String filename = StrUtil.subAfter(url, "/", true);
    InputStream inputStream = new ByteArrayInputStream(bytes);
    return getResponseEntity(inputStream, filename, groupId, artifactId, version, name, invokerPackage, apiPackage, modelPackage, packageTypes);
  }

  @Override
  public Map<String, String> getPackageTypes() {
    return JavaPackageType.getMap();
  }


  private ResponseEntity<byte[]> getResponseEntity(InputStream inputStream, String filename, String groupId, String artifactId, String version, String name, String invokerPackage, String apiPackage, String modelPackage, String packageTypes) {
    if (StrUtil.isEmpty(packageTypes)) {
      packageTypes = String.valueOf(JavaPackageType.ORIGIN_ZIP.getName());
    }
    String[] packageTypeArray = StrUtil.split(packageTypes, ",");
    List<JavaPackageType> javaPackageTypeList = JavaPackageType.find(packageTypeArray);
    File generateFile = javaGeneratorService.generate(inputStream, filename, groupId, artifactId, version, name, invokerPackage, apiPackage, modelPackage, Sets.newLinkedHashSet(javaPackageTypeList));
    return fileToResponseEntity(generateFile);
  }

  private ResponseEntity<byte[]> fileToResponseEntity(File file) {
    if (file == null) {
      return ResponseEntity.ok(new byte[0]);
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setContentDispositionFormData("attachment", file.getName());
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    byte[] bytes = FileUtil.readBytes(file);
    return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
  }
}
