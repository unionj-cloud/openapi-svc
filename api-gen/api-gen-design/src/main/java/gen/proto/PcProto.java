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
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class PcProto implements IImporter {
  @Override
  public void doImport() {
    path("/pc/user/surveyAward", pb -> {
      get(ppb -> {
        ppb.summary("问卷奖励");
        ppb.tags("front_usercenter");

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

    path("/pc/user/submitStat", pb -> {
      get(ppb -> {
        ppb.summary("答卷情况");
        ppb.tags("front_usercenter");

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

    path("/pc/user/info", pb -> {
      get(ppb -> {
        ppb.summary("用户信息");
        ppb.tags("front_usercenter");

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

    path("/pc/user/post", pb -> {
      post(ppb -> {
        ppb.summary("新增/更新用户信息");
        ppb.tags("front_usercenter");

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

    path("/pc/onlineSurvey/record/list", pb -> {
      post(ppb -> {
        ppb.summary("我的任务（我参与的）:网络调查任务");
        ppb.tags("front_onlinesurvey");

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

    path("/pc/onlineSurvey/found/list", pb -> {
      post(ppb -> {
        ppb.summary("我发起的: 网络调查任务");
        ppb.tags("front_onlinesurvey");

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

    path("/pc/recruit/add", pb -> {
      post(ppb -> {
        ppb.summary("发布招募人员");
        ppb.tags("front_recruit");

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

    path("/pc/recruit/page", pb -> {
      post(ppb -> {
        ppb.summary("招募人员分页查询");
        ppb.tags("front_recruit");


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

    path("/pc/recruit/get", pb -> {
      get(ppb -> {
        ppb.summary("招募人员详情");
        ppb.tags("front_recruit");

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

    path("/pc/found/recruit/page", pb -> {
      post(ppb -> {
        ppb.summary("我发起的：招募人员分页查询");
        ppb.tags("front_recruit");


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

    path("/pc/found/recruit/get", pb -> {
      get(ppb -> {
        ppb.summary("我发起的（招募人员）-详情接口");
        ppb.tags("front_recruit");

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
  }
}
