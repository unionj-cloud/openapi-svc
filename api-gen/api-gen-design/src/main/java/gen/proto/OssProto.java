package gen.proto;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.bool;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.string;
import static cloud.unionj.generator.openapi3.dsl.paths.Content.content;
import static cloud.unionj.generator.openapi3.dsl.paths.Get.get;
import static cloud.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static cloud.unionj.generator.openapi3.dsl.paths.Parameter.parameter;
import static cloud.unionj.generator.openapi3.dsl.paths.Path.path;
import static cloud.unionj.generator.openapi3.dsl.paths.Post.post;
import static cloud.unionj.generator.openapi3.dsl.paths.RequestBody.requestBody;
import static cloud.unionj.generator.openapi3.dsl.paths.Response.response;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static gen.Common.errorResponse;
import static gen.Components.ResultDTOString;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class OssProto implements IImporter {
  @Override
  public void doImport() {
    path("/oss/upload", pb -> {
      post(ppb -> {
        ppb.summary("上传附件");
        ppb.tags("attachment");

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("returnKey");
          para.schema(bool);
        });

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.multipartFormData(mediaType(mb -> {
              mb.schema(schema(upload -> {
                upload.type("object");
                upload.properties("file", schema(file -> {
                  file.type("string");
                  file.format("binary");
                }));
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/oss/get", pb -> {
      get(ppb -> {
        ppb.summary("获取附件");
        ppb.tags("attachment");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("key");
          para.schema(string);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("style");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationOctetStream(mediaType(mb -> {
                mb.schema(schema(download -> {
                  download.type("string");
                  download.format("binary");
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });
  }
}
