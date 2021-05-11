package gen.proto;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
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
import static gen.Components.ResultDTOPageResultVOProxyJobJianchaDetailVO;

/**
 * @author created by JohnnyTing
 * @version v0.0.1
 * description: gen.proto
 * date: 2021/3/3
 */
public class PcJianchaProto implements IImporter {
  @Override
  public void doImport() {
    path("/pc/jiancha/add", pb -> {
      post(ppb -> {
        ppb.summary("招募代理-创建明察暗访任务");
        ppb.description("返回新创建的任务id");
        ppb.tags("PcJianchaProto");
        ppb.tags("PcJiancha");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ProxyJobAddRequestVO.getTitle());
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

    path("/pc/jiancha/update", pb -> {
      post(ppb -> {
        ppb.summary("更新检查任务信息, 重新提交审核");
        ppb.tags("PcJianchaProto");
        ppb.tags("PcJiancha");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(JianchaInfoVO.getTitle());
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

    path("/pc/jiancha/found/list", pb -> {
      post(ppb -> {
        ppb.summary("我发起的：明察暗访分页查询");
        ppb.tags("PcJianchaProto");
        ppb.tags("PcJiancha");

        parameter(para -> {
          para.required(false);
          para.description("是否更新为已读");
          para.in("query");
          para.name("updateRead");
          para.schema(schema(updateRead -> {
            updateRead.type("boolean");
            updateRead.defaultValue(true);
          }));
        });

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ProxyJobCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOProxyJobJianchaDetailVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/jiancha/proxyJob/finish", pb -> {
      post(ppb -> {
        ppb.summary("结束任务");
        ppb.tags("PcJianchaProto");
        ppb.tags("PcJiancha");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("id");
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

    path("/pc/jiancha/found/info", pb -> {
      get(ppb -> {
        ppb.summary("我发起的：明察暗访任务详情");
        ppb.tags("PcJianchaProto");
        ppb.tags("PcJiancha");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("id");
          para.schema(int32);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("updateRead");
          para.description("是否更新为已读");
          para.schema(schema(updateRead -> {
            updateRead.type("boolean");
            updateRead.defaultValue(false);
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOProxyJobJianchaInfoVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/join/jiancha/list", pb -> {
      post(ppb -> {
        ppb.summary("我参与的（明察暗访）-分页列表查询接口");
        ppb.tags("PcJianchaProto");
        ppb.tags("PcJiancha");

        parameter(para -> {
          para.required(false);
          para.description("是否更新为已读");
          para.in("query");
          para.name("updateRead");
          para.schema(schema(updateRead -> {
            updateRead.type("boolean");
            updateRead.defaultValue(true);
          }));
        });

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ShopHasProxyJobJianchaSearchConditionVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOShopHasProxyJobJianchaVO.getTitle());
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

