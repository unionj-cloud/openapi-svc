package cloud.unionj.svc.server.controller;

import cloud.unionj.svc.proto.JavaProto;
import cloud.unionj.svc.server.service.JavaGeneratorService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;

@Controller
public class JavaProtoImpl implements JavaProto {

  @Override
  @SneakyThrows
  public ResponseEntity<byte[]> postJavaUpload(MultipartFile file, String groupId, String artifactId, String version, String name, String invokerPackage, String apiPackage, String modelPackage) {
    if (file == null || file.getSize() == 0) {
      throw new Exception("无效文件");
    }
    JavaGeneratorService javaGeneratorService = SpringUtil.getBean(JavaGeneratorService.class);
    File generateFile = javaGeneratorService.generate(file.getInputStream(), file.getOriginalFilename(), groupId, artifactId, version, name, invokerPackage, apiPackage, modelPackage);
    return fileToResponseEntity(generateFile);
  }

  @Override
  @SneakyThrows
  public ResponseEntity<byte[]> getJavaUrl(String url, String groupId, String artifactId, String version, String name, String invokerPackage, String apiPackage, String modelPackage) {
    String fileName = StrUtil.subAfter(url, "/", true);
    byte[] bytes = HttpUtil.downloadBytes(url);
    if (bytes == null || bytes.length == 0) {
      throw new Exception("无效文件");
    }
    JavaGeneratorService javaGeneratorService = SpringUtil.getBean(JavaGeneratorService.class);
    File generateFile = javaGeneratorService.generate(new ByteArrayInputStream(bytes), fileName, groupId, artifactId, version, name, invokerPackage, apiPackage, modelPackage);
    return fileToResponseEntity(generateFile);
  }

  private ResponseEntity<byte[]> fileToResponseEntity(File file) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentDispositionFormData("attachment", file.getName());
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    byte[] bytes = FileUtil.readBytes(file);
    return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
  }
}
