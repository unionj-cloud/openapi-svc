package gen.proto;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.int32;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.int64;
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
public class HallProto implements IImporter {
  @Override
  public void doImport() {

    path("/hall/stat", pb -> {
      get(ppb -> {
        ppb.summary("累计发布任务数量和累计赚钱金额");
        ppb.tags("hall");

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

    path("/hall/rank", pb -> {
      get(ppb -> {
        ppb.summary("收入排行榜数据");
        ppb.tags("hall");

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

    path("/hall/rank/finish", pb -> {
      get(ppb -> {
        ppb.summary("累计完成答卷数量排行数据");
        ppb.tags("hall");

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

    path("/hall/latestOrder", pb -> {
      get(ppb -> {
        ppb.summary("最新任务数据");
        ppb.tags("hall");

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


    path("/hall/onlineSurvey/list", pb -> {
      post(ppb -> {
        ppb.summary("网络调查分页");
        ppb.tags("hall_onlinesurvey");

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


    path("/hall/offlineSurvey/update", pb -> {
      post(ppb -> {
        ppb.summary("更新信息, 重新提交审核");
        ppb.tags("hall_offlinesurvey");

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

    path("/hall/offlineSurvey/list", pb -> {
      post(ppb -> {
        ppb.summary("首页面访任务分页查询");
        ppb.tags("hall_offlinesurvey");

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

    path("/hall/offlineSurvey/add", pb -> {
      post(ppb -> {
        ppb.summary("创建面访任务");
        ppb.description("返回新创建的任务id");
        ppb.tags("hall_offlinesurvey");

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

    path("/hall/offlineSurvey/info", pb -> {
      get(ppb -> {
        ppb.summary("面访任务详情");
        ppb.tags("hall_offlinesurvey");

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

    path("/hall/jiancha/list", pb -> {
      post(ppb -> {
        ppb.summary("首页检查任务分页查询");
        ppb.tags("hall_secretvisit");

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

    path("/hall/jiancha/join/list", pb -> {
      post(ppb -> {
        ppb.summary("报名者信息分页");
        ppb.deprecated(true);
        ppb.tags("hall_secretvisit");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(JianchaJoinCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOJoinUserVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/hall/jiancha/join", pb -> {
      post(ppb -> {
        ppb.summary("报名参加");
        ppb.deprecated(true);
        ppb.tags("hall_secretvisit");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("id");
          para.schema(int64);
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

    path("/hall/jiancha/info", pb -> {
      get(ppb -> {
        ppb.summary("检查任务详情");
        ppb.tags("hall_secretvisit");

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


    path("/hall/news/list", pb -> {
      post(ppb -> {
        ppb.summary("分页");
        ppb.tags("hall_news");

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

    path("/hall/news", pb -> {
      get(ppb -> {
        ppb.summary("获取信息");
        ppb.tags("hall_news");

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


    path("/hall/external/list", pb -> {
      post(ppb -> {
        ppb.summary("友情分享信息分页");
        ppb.tags("hall_friendshare");

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

    path("/hall/external/info", pb -> {
      get(ppb -> {
        ppb.summary("友情分享详情");
        ppb.tags("hall_friendshare");

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


    path("/hall/event/list", pb -> {
      post(ppb -> {
        ppb.summary("新鲜事分页");
        ppb.tags("hall_event");

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


  }
}
