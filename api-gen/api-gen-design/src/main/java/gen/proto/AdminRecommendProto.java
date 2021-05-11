package gen.proto;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.bool;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.int32;
import static cloud.unionj.generator.openapi3.dsl.paths.Content.content;
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
 * @author created by tqccc
 * @version v0.0.1
 * description: gen.proto
 * date:2021/3/10
 */
public class AdminRecommendProto implements IImporter {

  @Override
  public void doImport() {

    // 平台推荐
    path("/admin/recommend/page", pb -> {
      post(ppb -> {
        ppb.summary("平台推荐分页查询");
        ppb.tags("AdminRecommend");
        ppb.tags("AdminRecommend");

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

    path("/admin/recommend/add", pb -> {
      post(ppb -> {
        ppb.summary("添加平台推荐");
        ppb.tags("AdminRecommend");
        ppb.tags("AdminRecommend");

        parameter(para -> {
          para.name("shopId");
          para.in("query");
          para.required(true);
          para.schema(int32);
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

    path("/admin/recommend/top/update", pb -> {
      post(ppb -> {
        ppb.summary("平台推荐置顶");
        ppb.tags("AdminRecommend");
        ppb.tags("AdminRecommend");

        parameter(para -> {
          para.name("id");
          para.in("query");
          para.required(true);
          para.schema(int32);
        });

        parameter(para -> {
          para.name("top");
          para.in("query");
          para.required(true);
          para.schema(bool);
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

    path("/admin/recommend/del", pb -> {
      post(ppb -> {
        ppb.summary("平台推荐删除");
        ppb.tags("AdminRecommend");
        ppb.tags("AdminRecommend");

        parameter(para -> {
          para.name("id");
          para.in("query");
          para.required(true);
          para.schema(int32);
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

    path("/admin/recommend/searchShop", pb -> {
      post(ppb -> {
        ppb.summary("平台推荐搜索用户/企业");
        ppb.tags("AdminRecommend");
        ppb.tags("AdminRecommend");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ShopPageConditionVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOShopDetailVO.getTitle());
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
