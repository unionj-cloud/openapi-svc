package gen.java;

import cloud.unionj.generator.openapi3.PathConfig;
import cloud.unionj.generator.openapi3.dsl.IImporter;
import cloud.unionj.generator.openapi3.expression.paths.ParameterBuilder;
import cloud.unionj.generator.openapi3.model.paths.Parameter;

import static cloud.unionj.generator.openapi3.PathHelper.get;
import static cloud.unionj.generator.openapi3.PathHelper.post;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.string;

public class JavaProto implements IImporter {
  @Override
  public void doImport() {
    post("/java/upload", PathConfig.builder()
        .summary("通过上传openapi3.json文件生成代码")
        .reqSchema(schema(sb -> {
          sb.type("object");
          sb.properties("file", schema(file -> {
            file.type("string");
            file.format("binary");
          }));

          sb.properties("groupId", string("groupId，缺省为cloud.unionj"));
          sb.properties("artifactId", string("artifactId，缺省为generator"));
          sb.properties("version", string("version，缺省为1.0.0"));
          sb.properties("name", string("name，缺省为{groupId}-{artifactId}（特殊符号替换为-）"));
          sb.properties("invokerPackage", string("生成代码的根包完整包名，缺省为{groupId}.{artifactId}"));
          sb.properties("apiPackage", string("生成api接口代码的相对根包的包名，缺省为client"));
          sb.properties("modelPackage", string("生成api接口代码的相对根包的包名，缺省为vo"));
          sb.properties("packageTypes", string("打包类型名称，多个类型之间英文逗号分隔"));
        }))
        .reqSchemaType(PathConfig.SchemaType.FORMDATA)
        .respSchemaType(PathConfig.SchemaType.STREAM)
        .build()
    );

    get("/java/url", PathConfig.builder()
        .summary("通过openapi3.json文件的下载地址生成代码")
        .parameters(new Parameter[]{
            ParameterBuilder.builder().name("url").in(Parameter.InEnum.QUERY).required(true).schema(string).description("下载地址").build(),
            ParameterBuilder.builder().name("groupId").in(Parameter.InEnum.QUERY).required(false).schema(string).description("groupId，缺省为cloud.unionj").build(),
            ParameterBuilder.builder().name("artifactId").in(Parameter.InEnum.QUERY).required(false).schema(string).description("artifactId，缺省为generator").build(),
            ParameterBuilder.builder().name("version").in(Parameter.InEnum.QUERY).required(false).schema(string).description("version，缺省为1.0.0").build(),
            ParameterBuilder.builder().name("name").in(Parameter.InEnum.QUERY).required(false).schema(string).description("name，缺省为{groupId}-{artifactId}（特殊符号替换为-）").build(),
            ParameterBuilder.builder().name("invokerPackage").in(Parameter.InEnum.QUERY).required(false).schema(string).description("生成代码的根包完整包名，缺省为{groupId}.{artifactId}").build(),
            ParameterBuilder.builder().name("apiPackage").in(Parameter.InEnum.QUERY).required(false).schema(string).description("生成api接口代码的相对根包的包名，缺省为client").build(),
            ParameterBuilder.builder().name("modelPackage").in(Parameter.InEnum.QUERY).required(false).schema(string).description("生成api接口代码的相对根包的包名，缺省为vo").build(),
            ParameterBuilder.builder().name("packageTypes").in(Parameter.InEnum.QUERY).required(false).schema(string).description("打包类型名称，多个类型之间英文逗号分隔").build()
        })
        .respSchemaType(PathConfig.SchemaType.STREAM)
        .build()
    );

    get("/java/packageTypes", PathConfig.builder()
        .summary("查询java代码打包类型")
        .respSchemaType(PathConfig.SchemaType.JSON)
        .build()
    );
  }
}
