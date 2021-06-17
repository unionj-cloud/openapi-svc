package cloud.unionj.svc.server.controller;

import cloud.unionj.svc.proto.JavaProto;
import cloud.unionj.svc.server.service.JavaGeneratorService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class JavaProtoImpl implements JavaProto {

  @Override
  @SneakyThrows
  public String postTsUpload(MultipartFile file, String groupId, String artifactId, String version, String name, String invokerPackage, String apiPackage, String modelPackage) {
    JavaGeneratorService javaGeneratorService = SpringUtil.getBean(JavaGeneratorService.class);
    String generate = javaGeneratorService.generate(file, groupId, artifactId, version, name, invokerPackage, apiPackage, modelPackage);
    return generate;
  }

  @Override
  public ResponseEntity<byte[]> getTsUrl(String url) {
    return null;
  }
}
