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
 * @author created by tqccc
 * @version v0.0.1
 * description: gen.proto
 * date:2021/2/24
 */
public class AppProto implements IImporter {

  @Override
  public void doImport() {

    // 首页
    path("/app/open/hall/stat", pb -> {
      get(ppb -> {
        ppb.summary("累计发布任务数量和累计赚钱金额");
        ppb.tags("AppProto");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOHallStatResultDTO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/open/hall/rank", pb -> {
      get(ppb -> {
        ppb.summary("收入排行榜数据");
        ppb.tags("AppProto");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListRankAwardResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/open/hall/rank/finish", pb -> {
      get(ppb -> {
        ppb.summary("累计完成答卷数量排行数据");
        ppb.tags("AppProto");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListRankAwardResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/open/hall/latestOrder", pb -> {
      get(ppb -> {
        ppb.summary("最新任务数据");
        ppb.tags("AppProto");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListLatestOrderResultDTO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    // 网调
    path("/app/open/hall/onlineSurvey/list", pb -> {
      post(ppb -> {
        ppb.summary("网络调查分页");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(BaseSearchCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOHallOnlineSurveyVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    // 面访
    path("/app/hall/offlineSurvey/update", pb -> {
      post(ppb -> {
        ppb.summary("面访更新信息, 重新提交审核");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(OfflineSurveyInfoVO.getTitle());
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

    path("/app/open/hall/offlineSurvey/list", pb -> {
      post(ppb -> {
        ppb.summary("首页面访任务分页查询");
        ppb.tags("AppProto");

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

    path("/app/hall/offlineSurvey/add", pb -> {
      post(ppb -> {
        ppb.summary("创建面访任务");
        ppb.description("返回新创建的任务id");
        ppb.tags("AppProto");

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

    path("/app/open/hall/offlineSurvey/info", pb -> {
      get(ppb -> {
        ppb.summary("面访任务详情");
        ppb.tags("AppProto");

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
                  rrrb.ref(ResultDTOProxyJobOfflineSurveyInfoVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    // 检查
    path("/app/open/hall/jiancha/list", pb -> {
      post(ppb -> {
        ppb.summary("首页检查任务分页查询");
        ppb.tags("AppProto");

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

    path("/app/open/hall/jiancha/info", pb -> {
      get(ppb -> {
        ppb.summary("检查任务详情");
        ppb.tags("AppProto");

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

    path("/app/jiancha/add", pb -> {
      post(ppb -> {
        ppb.summary("创建检查任务");
        ppb.description("返回新创建的任务id");
        ppb.tags("AppProto");

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

    path("/app/jiancha/update", pb -> {
      post(ppb -> {
        ppb.summary("更新检查任务信息, 重新提交审核");
        ppb.tags("AppProto");

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


    // 最新资讯
    path("/app/open/hall/news/list", pb -> {
      post(ppb -> {
        ppb.summary("分页");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(BaseSearchCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVONewsVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/open/hall/news", pb -> {
      get(ppb -> {
        ppb.summary("获取信息");
        ppb.tags("AppProto");

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
                  rrrb.ref(ResultDTONewsVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    // 友情分享
    path("/app/open/hall/external/list", pb -> {
      post(ppb -> {
        ppb.summary("友情分享信息分页");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(BaseSearchCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOHallExternalVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/open/hall/external/info", pb -> {
      get(ppb -> {
        ppb.summary("友情分享详情");
        ppb.tags("AppProto");

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
                  rrrb.ref(ResultDTOExternalVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    // 新鲜事
    path("/app/open/hall/event/list", pb -> {
      post(ppb -> {
        ppb.summary("新鲜事分页");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(BaseSearchCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOEventVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    // 人员招募
    path("/app/recruit/add", pb -> {
      post(ppb -> {
        ppb.summary("发布招募人员");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(RecruitVO.getTitle());
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

    path("/app/open/recruit/get", pb -> {
      get(ppb -> {
        ppb.summary("招募人员详情");
        ppb.tags("AppProto");

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
                  rrrb.ref(ResultDTORecruitVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/open/recruit/page", pb -> {
      post(ppb -> {
        ppb.summary("招募人员分页查询");
        ppb.tags("AppProto");


        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(RecruitConditionVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVORecruitVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    // 平台推荐
    path("/app/open/recommend/page", pb -> {
      post(ppb -> {
        ppb.summary("平台推荐分页查询");
        ppb.tags("AppProto");

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

    // 代理任务
    path("/app/open/proxyJob/join/List", pb -> {
      post(ppb -> {
        ppb.summary("查询某用户参与的所有代理任务列表");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ShopHasProxyJobSearchConditionVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOProxyJobVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/proxyJob/join/offline/list", pb -> {
      post(ppb -> {
        ppb.summary("我参与的：报名列表：面访任务");
        ppb.tags("AppProto");

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

    path("/app/proxyJob/found/offline/list", pb -> {
      post(ppb -> {
        ppb.summary("我发起的：面访任务分页查询");
        ppb.tags("AppProto");

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

    path("/app/proxyJob/join/jiancha/list", pb -> {
      post(ppb -> {
        ppb.summary("我参与的：报名列表：检查任务");
        ppb.tags("AppProto");

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

    path("/app/proxyJob/found/jiancha/list", pb -> {
      post(ppb -> {
        ppb.summary("我发起的：面访任务分页查询");
        ppb.tags("AppProto");

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

    path("/app/proxyJob/finish", pb -> {
      post(ppb -> {
        ppb.summary("结束任务");
        ppb.tags("AppProto");

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


    // 用户
    path("/app/user/surveyAward", pb -> {
      get(ppb -> {
        ppb.summary("问卷奖励");
        ppb.tags("AppProto");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOSurveyAwardResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/user/submitStat", pb -> {
      get(ppb -> {
        ppb.summary("答卷情况");
        ppb.tags("AppProto");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOSubmitStatResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/user/info", pb -> {
      get(ppb -> {
        ppb.summary("用户信息");
        ppb.tags("AppProto");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOFrontUserInfoVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/user/post", pb -> {
      post(ppb -> {
        ppb.summary("新增/更新用户信息");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FrontUserInfoVO.getTitle());
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

    path("/app/onlineSurvey/record/list", pb -> {
      post(ppb -> {
        ppb.summary("我的任务（我参与的）:网络调查任务");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FrontUserOnlineSurveyRecordCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOFrontOnlineSurveyRecordVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/onlineSurvey/found/list", pb -> {
      post(ppb -> {
        ppb.summary("我发起的: 网络调查任务");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(BaseSearchCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOFrontOnlineSurveyFoundVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    // 店铺（个人、企业完善信息+淘汰选中）
    path("/app/shop/updateJoin", pb -> {
      post(ppb -> {
        ppb.summary("选中/淘汰报名店铺");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ShopHasProxyJobVO.getTitle());
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

    path("/app/shop/joinProxyJob", pb -> {
      post(ppb -> {
        ppb.summary("店铺报名");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ShopHasProxyJobVO.getTitle());
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

    path("/app/open/shop/list", pb -> {
      post(ppb -> {
        ppb.summary("店铺分页数据");
        ppb.tags("AppProto");

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

    path("/app/shop/add", pb -> {
      post(ppb -> {
        ppb.summary("完善店铺信息");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ShopInfoVO.getTitle());
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

    path("/app/shop/update", pb -> {
      post(ppb -> {
        ppb.summary("更新店铺");
        ppb.tags("AppProto");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ShopInfoVO.getTitle());
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

    path("/app/open/shop/info", pb -> {
      get(ppb -> {
        ppb.summary("店铺详情");
        ppb.tags("AppProto");

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("id");
          para.schema(int32);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("queryStat");
          para.description("是否查询统计信息");
          para.schema(schema(queryStat -> {
            queryStat.type("boolean");
            queryStat.defaultValue(false);
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOShopInfoVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/app/shop/hasJoinProxyJob", pb -> {
      get(ppb -> {
        ppb.summary("是否已报名");
        ppb.tags("AppProto");

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("shopId");
          para.schema(int32);
        });

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("proxyJobId");
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

    path("/app/shop/checkInfoComplete", pb -> {
      get(ppb -> {
        ppb.summary("查询用户店铺信息是否已提交");
        ppb.tags("AppProto");

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

    path("/app/shop/checkPersonCertification", pb -> {
      get(ppb -> {
        ppb.summary("个人是否已认证");
        ppb.tags("AppProto");

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

    path("/app/shop/checkEnterpriseCertification", pb -> {
      get(ppb -> {
        ppb.summary("企业是否已认证");
        ppb.tags("AppProto");

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
