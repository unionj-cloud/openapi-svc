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

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class ProxyJobProto implements IImporter {
  @Override
  public void doImport() {
    path("/proxyJob/join/offline/list", pb -> {
      post(ppb -> {
        ppb.summary("我参与的：报名列表：面访任务");
        ppb.tags("proxyjob");

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
                rrb.ref(ShopHasProxyJobOfflineSurveySearchCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOShopHasProxyJobOfflineSurveyVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/proxyJob/found/offline/list", pb -> {
      post(ppb -> {
        ppb.summary("我发起的：面访任务分页查询");
        ppb.tags("proxyjob");

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
                  rrrb.ref(ResultDTOPageResultVOProxyJobOfflineSurveyDetailVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/proxyJob/found/list", pb -> {
      post(ppb -> {
        ppb.summary("我发起的：面访任务分页查询");
        ppb.tags("proxyjob");
        ppb.deprecated(true);

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
                  rrrb.ref(ResultDTOPageResultVOProxyJobOfflineSurveyDetailVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/proxyJob/join/jiancha/list", pb -> {
      post(ppb -> {
        ppb.summary("我参与的：报名列表：检查任务");
        ppb.tags("proxyjob");

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

    path("/proxyJob/found/jiancha/list", pb -> {
      post(ppb -> {
        ppb.summary("我发起的：面访任务分页查询");
        ppb.tags("proxyjob");

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


    path("/proxyJob/found/countUnRead", pb -> {
      get(ppb -> {
        ppb.summary("我发起的:未读消息数目");
        ppb.deprecated(true);
        ppb.tags("proxyjob");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOAuditCountUnReadVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/proxyJob/countJobTotal", pb -> {
      get(ppb -> {
        ppb.summary("任务数量统计");
        ppb.deprecated(true);
        ppb.tags("proxyjob");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOJobTotalVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/proxyJob/finish", pb -> {
      post(ppb -> {
        ppb.summary("结束任务");
        ppb.tags("proxyjob");

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
  }
}
