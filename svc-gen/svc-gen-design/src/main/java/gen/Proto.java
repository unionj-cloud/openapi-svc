package gen;

import cloud.unionj.generator.openapi3.PathConfig;
import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.PathHelper.post;
import static gen.Components.UploadFormVO;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class Proto implements IImporter {
  @Override
  public void doImport() {
    post("/upload", PathConfig.builder()
        .summary("上传openapi3.json")
        .reqSchema(UploadFormVO)
        .reqSchemaType(PathConfig.SchemaType.FORMDATA)
        .respSchemaType(PathConfig.SchemaType.STREAM)
        .build()
    );
  }
}
