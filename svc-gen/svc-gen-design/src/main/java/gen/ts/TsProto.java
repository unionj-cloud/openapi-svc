package gen.ts;

import cloud.unionj.generator.openapi3.PathConfig;
import cloud.unionj.generator.openapi3.dsl.IImporter;
import cloud.unionj.generator.openapi3.expression.paths.ParameterBuilder;
import cloud.unionj.generator.openapi3.model.paths.Parameter;

import static cloud.unionj.generator.openapi3.PathHelper.get;
import static cloud.unionj.generator.openapi3.PathHelper.post;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.string;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class TsProto implements IImporter {
  @Override
  public void doImport() {
    post("/ts/upload", PathConfig.builder()
        .summary("通过上传openapi3.json文件生成代码")
        .reqSchema(schema(sb -> {
          sb.type("object");
          sb.properties("file", schema(file -> {
            file.type("string");
            file.format("binary");
          }));
        }))
        .reqSchemaType(PathConfig.SchemaType.FORMDATA)
        .respSchemaType(PathConfig.SchemaType.STREAM)
        .build()
    );

    get("/ts/url", PathConfig.builder()
        .summary("通过openapi3.json文件的下载地址生成代码")
        .parameters(new Parameter[]{
            ParameterBuilder.builder()
                .description("下载地址")
                .name("url")
                .in(Parameter.InEnum.QUERY)
                .required(true)
                .schema(string)
                .build()
        })
        .respSchemaType(PathConfig.SchemaType.STREAM)
        .build()
    );
  }
}
