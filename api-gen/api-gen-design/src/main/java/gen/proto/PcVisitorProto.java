package gen.proto;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.int32;
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
import static gen.Components.*;

/**
 * @author created by JohnnyTing
 * @version v0.0.1
 * description: gen.proto
 * date: 2021/4/28
 */
public class PcVisitorProto implements IImporter {
  @Override
  public void doImport() {
    path("/pc/offlineSurveyVisitor/info", pb -> {
      get(ppb -> {
        ppb.summary("面访任务详情-招募访问员");
        ppb.tags("PcVisitor");
        ppb.tags("PcVisitor");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("id");
          para.description("招募访问员记录id");
          para.schema(int32);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOOfflineSurveyVisitorInfoVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    path("/pc/offlineSurveyVisitor/join", pb -> {
      post(ppb -> {
        ppb.summary("招募访问员报名");
        ppb.tags("PcVisitor");
        ppb.tags("PcVisitor");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ShopHasVisitorVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOInteger.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/offlineSurveyVisitor/has", pb -> {
      get(ppb -> {
        ppb.summary("查询访问员是否报名过");
        ppb.tags("PcVisitor");
        ppb.tags("PcVisitor");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("id");
          para.description("招募访问员记录id");
          para.schema(int32);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOBoolean.getTitle());
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

