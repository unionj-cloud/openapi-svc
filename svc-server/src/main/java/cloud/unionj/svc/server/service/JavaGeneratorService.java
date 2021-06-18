package cloud.unionj.svc.server.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

public interface JavaGeneratorService {
  /**
   * @param openapiJsonInputStream openapi.json的文件流
   * @param openapiJsonFileName    openapi.json的原始文件名称
   * @param groupId                groupId，缺省为cloud.unionj
   * @param artifactId             artifactId，缺省为generator
   * @param version                version，缺省为1.0.0
   * @param name                   name，缺省为{groupId}-{artifactId}（特殊符号替换为-）
   * @param invokerPackage         生成代码的根包完整包名，缺省为{groupId}.{artifactId}
   * @param apiPackage             生成api接口代码的相对根包的包名，缺省为client
   * @param modelPackage           生成api接口代码的相对根包的包名，缺省为vo
   * @return
   */
  File generate(InputStream openapiJsonInputStream,
                String openapiJsonFileName,
                String groupId,
                String artifactId,
                String version,
                String name,
                String invokerPackage,
                String apiPackage,
                String modelPackage
  );
}
