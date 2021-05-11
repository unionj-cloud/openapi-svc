package gen.proto;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.string;
import static cloud.unionj.generator.openapi3.dsl.paths.Content.content;
import static cloud.unionj.generator.openapi3.dsl.paths.Get.get;
import static cloud.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static cloud.unionj.generator.openapi3.dsl.paths.Parameter.parameter;
import static cloud.unionj.generator.openapi3.dsl.paths.Path.path;
import static cloud.unionj.generator.openapi3.dsl.paths.Response.response;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static gen.Common.errorResponse;
import static gen.Components.ResultDTOMiniCode2SessionResultVO;
import static gen.Components.ResultDTOOpenOauthAccessTokenVO;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class WxProto implements IImporter {
  @Override
  public void doImport() {
    path("/wx/open/oauth2/accessToken", pb -> {
      get(ppb -> {
        ppb.summary("开放平台获取access token");
        ppb.tags("wechat");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("code");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOOpenOauthAccessTokenVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/wx/mini/code2Session", pb -> {
      get(ppb -> {
        ppb.summary("小程序：获取会话信息");
        ppb.tags("wechat");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("code");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMiniCode2SessionResultVO.getTitle());
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
