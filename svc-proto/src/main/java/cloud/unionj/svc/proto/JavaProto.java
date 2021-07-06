package cloud.unionj.svc.proto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface JavaProto {

  @PostMapping("/java/upload")
  ResponseEntity<byte[]> postJavaUpload(
      @RequestPart(value = "file") MultipartFile file,
      @RequestParam(value = "groupId", required = false) String groupId,
      @RequestParam(value = "artifactId", required = false) String artifactId,
      @RequestParam(value = "version", required = false) String version,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "invokerPackage", required = false) String invokerPackage,
      @RequestParam(value = "apiPackage", required = false) String apiPackage,
      @RequestParam(value = "modelPackage", required = false) String modelPackage,
      @RequestParam(value = "packageTypes", required = false) String packageTypes
  );

  @GetMapping("/java/url")
  ResponseEntity<byte[]> getJavaUrl(
      @RequestParam("url") String url,
      @RequestParam(value = "groupId", required = false) String groupId,
      @RequestParam(value = "artifactId", required = false) String artifactId,
      @RequestParam(value = "version", required = false) String version,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "invokerPackage", required = false) String invokerPackage,
      @RequestParam(value = "apiPackage", required = false) String apiPackage,
      @RequestParam(value = "modelPackage", required = false) String modelPackage,
      @RequestParam(value = "packageTypes", required = false) String packageTypes
  );

  @GetMapping("/java/packageTypes")
  @ResponseBody
  Map<String, String> getPackageTypes();
}
