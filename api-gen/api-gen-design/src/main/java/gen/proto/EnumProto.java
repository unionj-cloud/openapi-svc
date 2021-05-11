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
import static gen.Components.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class EnumProto implements IImporter {
  @Override
  public void doImport() {

    path("/enum/user/sampleDict", pb -> {
      get(ppb -> {
        ppb.summary("用户样本字典值");
        ppb.tags("enum");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("name");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListSampleDictDTO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/user/majorSubject", pb -> {
      get(ppb -> {
        ppb.summary("专业学科");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOSetString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/user/majorName", pb -> {
      get(ppb -> {
        ppb.summary("专业名称");
        ppb.tags("enum");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("subjectName");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/user/countryData", pb -> {
      get(ppb -> {
        ppb.summary("地区字典");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListCountryDataVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/user/collegeProvince", pb -> {
      get(ppb -> {
        ppb.summary("院校省份列表");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOSetString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/user/collegeName", pb -> {
      get(ppb -> {
        ppb.summary("院校名称列表");
        ppb.tags("enum");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("provinceName");
          para.schema(string);
        });

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("cityName");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/user/collegeCity", pb -> {
      get(ppb -> {
        ppb.summary("院校城市列表");
        ppb.tags("enum");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("provinceName");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOSetString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/shop/shopRecommendApplyStatusEnum", pb -> {
      get(ppb -> {
        ppb.summary("店铺推荐申请：审核状态");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/shop/shopHasJobStatusEnum", pb -> {
      get(ppb -> {
        ppb.summary("店铺报名：选中状态");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/proxyJob/proxyJobStatusEnum", pb -> {
      get(ppb -> {
        ppb.summary("招募代理任务：状态");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/pc/onlineSurveyAuditStatus", pb -> {
      get(ppb -> {
        ppb.summary("用户端:我的任务（我参与的）:我的任务审核状态字典（答卷的状态）");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/onlineSurveyStatus", pb -> {
      get(ppb -> {
        ppb.summary("管理后台:网络调查任务【审核状态】字典");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/onlineSurveyRecordStatus", pb -> {
      get(ppb -> {
        ppb.summary("管理后台:网络调查任务【记录状态】字典");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/hall/onlineSurveyFoundStatus", pb -> {
      get(ppb -> {
        ppb.summary("网络调查任务：我发起的：问卷状态");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/hall/onlineSurveySubmitStatus", pb -> {
      get(ppb -> {
        ppb.summary("网络调查任务：答题状态");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/hall/offlineSurveyJoinStatus", pb -> {
      get(ppb -> {
        ppb.summary("任务大厅:面访任务报名状态字典");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/hall/jianchaJoinStatus", pb -> {
      get(ppb -> {
        ppb.summary("任务大厅:明察暗访任务报名状态字典");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/hall/externalStatus", pb -> {
      get(ppb -> {
        ppb.summary("任务大厅:友情分享状态字典");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/recruitStatus", pb -> {
      get(ppb -> {
        ppb.summary("人员招募记录状态");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/proxyJobType", pb -> {
      get(ppb -> {
        ppb.summary("代理任务类型");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/identifyStatus", pb -> {
      get(ppb -> {
        ppb.summary("认证状态");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/enum/offlineSurveyVisitorStatus", pb -> {
      get(ppb -> {
        ppb.summary("面访访问员审核状态");
        ppb.tags("enum");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOMapStringString.getTitle());
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
