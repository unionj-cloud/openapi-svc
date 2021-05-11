package gen;

import cloud.unionj.generator.openapi3.model.Schema;

import static cloud.unionj.generator.openapi3.dsl.Schema.schema;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class Components {

  public static Schema UploadFormVO = schema(sb -> {
    sb.type("object");
    sb.title("UploadFormVO");
    sb.properties("file", schema(file -> {
      file.type("string");
      file.format("binary");
    }));
  });

}
