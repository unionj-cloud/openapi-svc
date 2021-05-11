package gen.admin;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;
import static cloud.unionj.generator.openapi3.dsl.paths.Content.content;
import static cloud.unionj.generator.openapi3.dsl.paths.Delete.delete;
import static cloud.unionj.generator.openapi3.dsl.paths.Get.get;
import static cloud.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static cloud.unionj.generator.openapi3.dsl.paths.Parameter.parameter;
import static cloud.unionj.generator.openapi3.dsl.paths.Path.path;
import static cloud.unionj.generator.openapi3.dsl.paths.Post.post;
import static cloud.unionj.generator.openapi3.dsl.paths.RequestBody.requestBody;
import static cloud.unionj.generator.openapi3.dsl.paths.Response.response;
import static cloud.unionj.generator.openapi3.dsl.paths.Responses.responses;
import static gen.Common.errorResponse;
import static gen.Components.BaseSearchCondition;
import static gen.Components.ExternalVO;
import static gen.admin.Components.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class AdminProto implements IImporter {
  @Override
  public void doImport() {
    path("/admin/onlineSurvey/record/list", pb -> {
      post(ppb -> {
        ppb.summary("网络调查任务记录列表");
        ppb.tags("admin_onlinesurvey");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(OnlineSurveyRecordConditionVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOAdminOnlineSurveyRecordVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/admin/onlineSurvey/audit", pb -> {
      post(ppb -> {
        ppb.summary("审批网络调查任务");
        ppb.tags("admin_onlinesurvey");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(OnlineSurveyAuditRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOVoid.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/admin/onlineSurvey/audit/list", pb -> {
      post(ppb -> {
        ppb.summary("网络调查任务审核列表");
        ppb.tags("admin_onlinesurvey");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(OnlineSurveyAuditCondition.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOAdminOnlineSurveyAuditVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/admin/onlineSurvey/top/update", pb -> {
      post(ppb -> {
        ppb.summary("网络调查任务置顶");
        ppb.tags("admin_onlinesurvey");

        parameter(para -> {
          para.name("id");
          para.in("query");
          para.required(true);
          para.schema(string);
        });

        parameter(para -> {
          para.name("top");
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

    path("/admin/onlineSurvey/del", pb -> {
      post(ppb -> {
        ppb.summary("网络调查任务删除");
        ppb.tags("admin_onlinesurvey");

        parameter(para -> {
          para.name("id");
          para.in("query");
          para.required(true);
          para.schema(string);
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


    path("/admin/proxyJob/top/update", pb -> {
      post(ppb -> {

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

    path("/admin/proxyJob/del", pb -> {
      post(ppb -> {

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


    path("/admin/offlineSurvey/list", pb -> {
      post(ppb -> {
        ppb.summary("面访任务列表");
        ppb.tags("admin_offlinesurvey");

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
                  rrrb.ref(ResultDTOPageResultVOProxyJobOfflineSurveyVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/admin/offlineSurvey/visitor/list", pb -> {
      post(ppb -> {
        ppb.summary("面对面访问（招募访问员）任务列表");
        ppb.tags("admin_offlinesurvey");

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
                  rrrb.ref(ResultDTOPageResultVOOfflineSurveyVisitorVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/admin/offlineSurvey/record/list", pb -> {
      post(ppb -> {
        ppb.summary("面访任务【记录】列表");
        ppb.tags("admin_offlinesurvey");

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
                  rrrb.ref(ResultDTOPageResultVOProxyJobOfflineSurveyVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/admin/offlineSurvey/audit", pb -> {
      post(ppb -> {
        ppb.summary("审核面访任务");
        ppb.tags("admin_offlinesurvey");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(refArray(AuditRequestVO.getTitle()));
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


    path("/admin/jiancha/list", pb -> {
      post(ppb -> {
        ppb.summary("明察暗访任务列表");
        ppb.tags("admin_secretvisit");

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
                  rrrb.ref(ResultDTOPageResultVOProxyJobJianchaVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/admin/jiancha/record/list", pb -> {
      post(ppb -> {
        ppb.summary("明察暗访任务【记录】列表");
        ppb.tags("admin_secretvisit");

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
                  rrrb.ref(ResultDTOPageResultVOProxyJobJianchaVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/admin/jiancha/audit", pb -> {
      post(ppb -> {
        ppb.summary("审核明察暗访任务");
        ppb.tags("admin_secretvisit");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(refArray(AuditRequestVO.getTitle()));
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


    path("/admin/news", pb -> {
      get(ppb -> {
        ppb.summary("获取最新资讯信息");
        ppb.tags("admin_news");

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
                  rrrb.ref(ResultDTONewsVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });

      delete(ppb -> {
        ppb.summary("删除资讯");
        ppb.tags("admin_news");

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

      post(ppb -> {
        ppb.summary("资讯发布");
        ppb.tags("admin_news");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(NewsVO.getTitle());
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

    path("/admin/news/update", pb -> {
      post(ppb -> {
        ppb.summary("修改资讯");
        ppb.tags("admin_news");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(NewsVO.getTitle());
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

    path("/admin/news/list", pb -> {
      post(ppb -> {
        ppb.summary("最新资讯分页");
        ppb.tags("admin_news");

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


    path("/admin/external", pb -> {
      get(ppb -> {
        ppb.summary("友情分享信息");
        ppb.tags("admin_friendshare");

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

      delete(ppb -> {
        ppb.summary("删除友情分享");
        ppb.tags("admin_friendshare");

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

      post(ppb -> {
        ppb.summary("发布友情分享");
        ppb.tags("admin_friendshare");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ExternalVO.getTitle());
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

    path("/admin/external/update", pb -> {
      post(ppb -> {
        ppb.summary("更新友情分享");
        ppb.tags("admin_friendshare");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(ExternalVO.getTitle());
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

    path("/admin/external/list", pb -> {
      post(ppb -> {
        ppb.summary("友情分享分页");
        ppb.tags("admin_friendshare");

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
                  rrrb.ref(ResultDTOPageResultVOExternalVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    path("/admin/recruit/page", pb -> {
      post(ppb -> {
        ppb.summary("招募人员分页查询");
        ppb.tags("admin_recruit");


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

    path("/admin/recruit/audit", pb -> {
      post(ppb -> {
        ppb.summary("招募人员审核");
        ppb.tags("admin_recruit");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(RecruitAuditRequestVO.getTitle());
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

    path("/admin/recruit/get", pb -> {
      get(ppb -> {
        ppb.summary("招募人员详情");
        ppb.tags("admin_recruit");

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

    path("/admin/offlineSurvey/visitor/info", pb -> {
      get(ppb -> {
        ppb.summary("面访招募访问员任务详情");
        ppb.tags("admin_offlinesurvey");

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
                  rrrb.ref(ResultDTOOfflineSurveyVisitorInfoVO.getTitle());
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
