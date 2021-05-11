package gen.proto;

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
import static gen.Components.*;

/**
 * @author created by JohnnyTing
 * @version v0.0.1
 * description: gen.proto
 * date: 2021/4/29
 */
public class CustomerIdentifyProto implements IImporter {
  @Override
  public void doImport() {
    path("/customerIdentify/interviewer/status", pb -> {
      post(ppb -> {
        ppb.summary("用户认证");
        ppb.tags("CustomerIdentifyProto");
        ppb.description("查询访问员用户认证状态");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOCustomerIdentifyStatusResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/customerIdentify/proxy/status", pb -> {
      post(ppb -> {
        ppb.summary("用户认证");
        ppb.tags("CustomerIdentifyProto");
        ppb.description("查询个人用户、代理用户认证状态");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOCustomerIdentifyStatusResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/customerIdentify/company/status", pb -> {
      post(ppb -> {
        ppb.summary("用户认证");
        ppb.tags("CustomerIdentifyProto");
        ppb.description("查询企业认证状态");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOCustomerIdentifyStatusResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/customerIdentify/interviewer/create", pb -> {
      post(ppb -> {
        ppb.summary("用户认证");
        ppb.tags("CustomerIdentifyProto");
        ppb.description("上传访问员用户认证");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(InterviewerIdentifyRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOInterviewerIdentifyResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/customerIdentify/proxy/create", pb -> {
      post(ppb -> {
        ppb.summary("用户认证");
        ppb.tags("CustomerIdentifyProto");
        ppb.description("上传个人用户/代理认证");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(CustomerIdentifyRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOCustomerIdentifyResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/customerIdentify/company/create", pb -> {
      post(ppb -> {
        ppb.summary("用户认证");
        ppb.tags("CustomerIdentifyProto");
        ppb.description("上传企业认证");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(CompanyIdentifyRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOCompanyIdentifyResultVO.getTitle());
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

