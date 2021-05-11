package gen.pcsearch;

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
import static gen.pcsearch.Components.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen.proto
 * @date:2020/12/28
 */
public class PcSearchProto implements IImporter {
  @Override
  public void doImport() {
    path("/pc/open/search/job/fuzzy", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜任务模糊匹配");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FuzzySearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListFuzzySearchResponseVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    path("/pc/open/search/job/full", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜任务全部结果列表");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FuzzySearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultVOJobVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/open/search/job/online", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜任务网络调查结果列表");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FuzzySearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultOnlineSurveyJobVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/open/search/job/offline", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜任务面对面访问招募代理结果列表");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FuzzySearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultOfflineSurveyJobVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/open/search/job/offlinevisitor", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜任务面对面访问招募访问员结果列表");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FuzzySearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultOfflineSurveyVisitorJobVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/open/search/job/jiancha", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜任务明察暗访结果列表");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FuzzySearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultJianchaJobVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/open/search/job/external", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜任务友情分享结果列表");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(FuzzySearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultExternalJobVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/open/search/partyb/company", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜企业结果列表");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(PartyBSearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultPartyBVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    path("/pc/open/search/partyb/person", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜人才结果列表");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(PartyBSearchRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPageResultPartyBVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/pc/search/contactme", pb -> {
      post(ppb -> {
        ppb.summary("PC端搜企业和搜人才与我联系接口");
        ppb.tags("任务搜索和任务列表");
        ppb.tags("PcSearch");

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
