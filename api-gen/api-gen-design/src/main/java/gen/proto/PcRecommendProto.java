package gen.proto;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.paths.Content.content;
import static cloud.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static cloud.unionj.generator.openapi3.dsl.paths.Path.path;
import static cloud.unionj.generator.openapi3.dsl.paths.Post.post;
import static cloud.unionj.generator.openapi3.dsl.paths.RequestBody.requestBody;
import static cloud.unionj.generator.openapi3.dsl.paths.Response.response;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static gen.Common.errorResponse;
import static gen.Components.RecommendConditionVO;
import static gen.Components.ResultDTOPageResultVORecommendVO;
import static gen.pcsearch.Components.ContactMeRequestVO;
import static gen.pcsearch.Components.ResultDTOString;

/**
 * @author created by tqccc
 * @version v0.0.1
 * description: gen.proto
 * date:2021/3/3
 */
public class PcRecommendProto implements IImporter {
  @Override
  public void doImport() {

    path("/pc/open/recommend/page", pb -> {
      post(ppb -> {
        ppb.summary("平台推荐分页查询");
        ppb.tags("PcRecommend");
        ppb.tags("PcRecommend");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(RecommendConditionVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVORecommendVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/open/recommend/contactme", pb -> {
      post(ppb -> {
        ppb.summary("平台推荐与我联系接口");
        ppb.tags("PcRecommend");
        ppb.tags("PcRecommend");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ContactMeRequestVO.getTitle());
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

  }
}
