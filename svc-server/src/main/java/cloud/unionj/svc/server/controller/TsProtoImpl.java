package cloud.unionj.svc.server.controller;

import cloud.unionj.generator.frontend.vue.VueProjectGenerator;
import cloud.unionj.svc.proto.TsProto;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author created by wubin
 * @version v0.0.1
 * description: cloud.unionj.svc.server.controller
 * date:2021/5/11
 */
@Controller
public class TsProtoImpl implements TsProto {

  @SneakyThrows
  @Override
  public ResponseEntity<byte[]> postTsUpload(MultipartFile file) {
    String projectName = StringUtils.stripEnd(file.getOriginalFilename(), ".json");
    try (InputStream is = file.getInputStream()) {
      VueProjectGenerator vueProjectGenerator = new VueProjectGenerator.Builder(projectName).is(is).build();
      String absolutePath = FileSystems.getDefault().getPath(vueProjectGenerator.getOutputFile()).normalize().toAbsolutePath().toString();
      FileUtils.forceDelete(new File(absolutePath));
      String outputFile = vueProjectGenerator.generate();
      File output = new File(outputFile);
      byte[] body = FileUtils.readFileToByteArray(output);
      output.delete();
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(output.getName(), StandardCharsets.UTF_8.name()) + "\"")
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .body(body);
    }
  }

  @SneakyThrows
  @Override
  public ResponseEntity<byte[]> getTsUrl(String url) {
    String path = new URL(url).getPath();
    String[] split = StringUtils.split(path, "/");
    List<String> collect = Lists.newArrayList(split).stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
    String projectName = collect.get(collect.size() - 1);
    projectName = StringUtils.stripEnd(projectName, ".json");
    try (BufferedInputStream is = new BufferedInputStream(new URL(url).openStream())) {
      VueProjectGenerator vueProjectGenerator = new VueProjectGenerator.Builder(projectName).is(is).build();
      String outputFile = vueProjectGenerator.generate();
      File output = new File(outputFile);
      byte[] body = FileUtils.readFileToByteArray(output);
      output.delete();
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(output.getName(), StandardCharsets.UTF_8.name()) + "\"")
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .body(body);
    }
  }
}
