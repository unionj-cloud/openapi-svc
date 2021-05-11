package gen.proto;

import cloud.unionj.generator.openapi3.dsl.IImporter;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;
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
public class TreeyeeProto implements IImporter {
  @Override
  public void doImport() {

    path("/treeyee/open/customer/userInfo", pb -> {
      get(ppb -> {
        ppb.summary("调研工厂用户信息");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("treeyeeToken");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOTreeyeeUserInfoVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/treeyee/transactionList", pb -> {
      post(ppb -> {
        ppb.summary("用户交易记录");
        ppb.tags("survey");

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
                  rrrb.ref(ResultDTOPageResultVOTransactionListItemVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/treeyee/shortLink", pb -> {
      post(ppb -> {
        ppb.summary("获取短链接");
        ppb.tags("survey");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.textPlain(mediaType(mb -> {
              mb.schema(string);
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.description("OK");
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

    path("/treeyee/tokenByUnionId", pb -> {
      post(ppb -> {
        ppb.summary("通过unionId获取token");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("unionId");
          para.schema(string);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("weixinNickName");
          para.schema(string);
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

    path("/treeyee/phoneCode", pb -> {
      post(ppb -> {
        ppb.summary("发送验证码");
        ppb.tags("survey");
        ppb.deprecated(true);

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("username");
          para.schema(string);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("effect");
          para.description("企业定制 false: 企业定制不生效 true: 企业定制生效");
          para.schema(bool);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("codeType");
          para.description("验证码种类 0：兼容 1:手机注册/修改密码 2:短信验证码登录 3:绑定/解绑手机 4:手机注销账号  5:提现短信验证 6:面容识别 7:问卷吧答题确认 8:银行卡提现");
          para.schema(int32);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOSendPhoneCodeResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/treeyee/open/phoneCode", pb -> {
      post(ppb -> {
        ppb.summary("发送验证码");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("username");
          para.schema(string);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("effect");
          para.description("企业定制 false: 企业定制不生效 true: 企业定制生效");
          para.schema(bool);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("codeType");
          para.description("验证码种类 0：兼容 1:手机注册/修改密码 2:短信验证码登录 3:绑定/解绑手机 4:手机注销账号  5:提现短信验证 6:面容识别 7:问卷吧答题确认 8:银行卡提现");
          para.schema(int32);
        });


        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOSendPhoneCodeResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });


    path("/treeyee/bindPhone", pb -> {
      post(ppb -> {
        ppb.summary("绑定手机号");
        ppb.tags("survey");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(BindPhoneRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOBindingResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/treeyee/phoneBinding", pb -> {
      get(ppb -> {
        ppb.summary("是否已绑定手机号");
        ppb.tags("survey");

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

    path("/treeyee/customerCaptial", pb -> {
      get(ppb -> {
        ppb.summary("用户资金信息");
        ppb.tags("survey");

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOCustomerCaptialResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/treeyee/contact", pb -> {
      get(ppb -> {
        ppb.summary("发布者联系信息");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("publisherId");
          para.schema(int32);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOPublisherPhoneVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/treeyee/checkToken", pb -> {
      get(ppb -> {
        ppb.summary("检查token是否已过期");
        ppb.description("检查token是否已过期, 默认取请求头token，若传的入参，则取入参token");
        ppb.tags("survey");

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("treeyeeToken");
          para.description("调研工厂token");
          para.schema(string);
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

    path("/treeyee/customer/cash/out", pb -> {
      post(ppb -> {
        ppb.summary("用户红包提现");
        ppb.description("用户红包提现");
        ppb.tags("survey");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(TreeyeeCustomerCashOutRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });

      });
    });

    path("/treeyee/open/current/company/update", pb -> {
      post(ppb -> {
        ppb.summary("设置当前企业");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("treeyeeToken");
          para.schema(string);
        });

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("treeyeeCompanyId");
          para.schema(int32);
        });


        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });

      });
    });

    path("/treeyee/open/current/company/get", pb -> {
      get(ppb -> {
        ppb.summary("获取当前企业");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("treeyeeToken");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOTreeyeeCompanyInfoVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });

      });
    });

    path("/treeyee/open/customer/token", pb -> {
      post(ppb -> {
        ppb.summary("获取token");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("username");
          para.schema(string);
        });

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("password");
          para.schema(string);
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

    path("/treeyee/open/customer/regist", pb -> {
      post(ppb -> {
        ppb.summary("用户注册/修改密码");
        ppb.tags("survey");

        requestBody(rb -> {
          rb.required(true);
          rb.content(content(cb -> {
            cb.applicationJson(mediaType(mb -> {
              mb.schema(reference(rrb -> {
                rrb.ref(TreeyeeCustomerRegistRequestVO.getTitle());
              }));
            }));
          }));
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOTreeyeeCustomerRegistResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });

      });
    });

    path("/treeyee/open/customer/company/list", pb -> {
      get(ppb -> {
        ppb.summary("获取加入的企业列表");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("treeyeeToken");
          para.schema(string);
        });

        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOListCompanyStaffResultVO.getTitle());
                }));
              }));
            }));
          }));
          errorResponse(rb);
        });
      });
    });

    path("/treeyee/open/customer/bindingWeixinByUnionId", pb -> {
      post(ppb -> {
        ppb.summary("绑定微信");
        ppb.tags("survey");

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("treeyeeToken");
          para.schema(string);
        });

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("unionId");
          para.schema(string);
        });

        parameter(para -> {
          para.required(true);
          para.in("query");
          para.name("weixinNickName");
          para.schema(string);
        });

        parameter(para -> {
          para.required(false);
          para.in("query");
          para.name("force");
          para.schema(bool);
        });


        responses(rb -> {
          rb.response200(response(rrb -> {
            rrb.content(content(cb -> {
              cb.applicationJson(mediaType(mb -> {
                mb.schema(reference(rrrb -> {
                  rrrb.ref(ResultDTOBindingResultVO.getTitle());
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
