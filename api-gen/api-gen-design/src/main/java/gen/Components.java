package gen;

import cloud.unionj.generator.openapi3.expression.SchemaBuilder;
import cloud.unionj.generator.openapi3.model.Schema;

import static cloud.unionj.generator.openapi3.dsl.Generic.generic;
import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class Components {

  public static Schema sizeProperty = int32("每页条数，默认10，传-1查出全部数据");

  public static Schema currentProperty = int32("当前页，从1开始");

  public static Schema offsetProperty = int32("偏移量");

  public static Schema sortProperty = string("排序条件字符串：排序字段前使用'-'(降序)和'+'(升序)号表示排序规则，多个排序字段用','隔开",
      "+id,-create_at");

  public static Schema pageProperty = int32("当前页，从1开始");

  public static Schema limitProperty = int32("每页条数，默认10, 传-1查出全部数据", 10);

  public static Schema maxPageProperty = int32("导出结束页");

  public static Schema totalProperty = int32("总数，入参传入此参数则不再查询count，以此total为准");

  public static Schema createAtProperty = dateTime("创建时间");

  public static Schema publishAtProperty = dateTime("发布时间");

  public static Schema pushAtProperty = dateTime("任务推送时间");

  public static Schema updateAtProperty = dateTime("更新时间");

  public static Schema endAtProperty = dateTime("截止时间");

  public static Schema publishNameProperty = string("发布人名称");

  public static Schema publishLogoProperty = string("发布人logo");

  public static Schema joinEndAtProperty = dateTime("报名截止时间");

  public static Schema workCityListProperty = stringArray("可工作地区列表");

  public static Schema goodAtListProperty = stringArray("擅长列表");

  public static Schema topStatusProperty = int32("需要排在前的状态");

  public static Schema offlineEndAtEndProperty = dateTime("面访任务截止时间范围结束");

  public static Schema jianchaEndAtEndProperty = dateTime("检查任务截止时间范围结束");

  public static Schema ResultDTO = schema(sb -> {
    sb.type("object");
    sb.title("ResultDTO");
    sb.properties("code", int32);
    sb.properties("msg", string);
    sb.properties("data", T);
  });

  public static Schema ShopRecommendApplyDetailCondition = schema(sb -> {
    sb.type("object");
    sb.title("ShopRecommendApplyDetailCondition");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("topStatus", topStatusProperty);
    sb.properties("shopName", string("店铺名称"));
    sb.properties("status", int32Array("审批状态"));
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema ShopStatInfoDto = schema(sb -> {
    sb.type("object");
    sb.description("店铺统计信息");
    sb.title("ShopStatInfoDto");
    sb.properties("finishQuantity", int32("项目完成数量"));
    sb.properties("finishAmountQuantity", doubleNumer("项目完成总额"));
    sb.properties("recentlyFinishDate", dateTime("最近完成项目日期，可能为空"));
  });

  public static Schema ShopRecommendApplyDTO = schema(sb -> {
    sb.type("object");
    sb.title("ShopRecommendApplyDTO");
    sb.properties("shopId", int32("店铺id"));
    sb.properties("applyReason", string("申请原因"));
  });

  public static Schema ShopHasProxyJobVO = schema(sb -> {
    sb.type("object");
    sb.title("ShopHasProxyJobVO");
    sb.properties("shopId", int32("店铺id，非必填，未填则取登陆用户的店铺"));
    sb.properties("proxyJobId", int32);
    sb.properties("status", int32);
  });

  public static Schema ShopHasVisitorVO = schema(sb -> {
    sb.type("object");
    sb.title("ShopHasVisitorVO");
    sb.properties("offlineSurveyVisitorId", int32);
    sb.properties("shopId", int32("店铺id，非必填，未填则取登陆用户的店铺"));
  });


  public static Schema UserExtraInfoDTO = schema(sb -> {
    sb.type("object");
    sb.description("附加信息");
    sb.title("UserExtraInfoDTO");
    sb.properties("phone", string);
    sb.properties("birthDate", dateTime);
    sb.properties("age", int32);
    sb.properties("resident", stringArray("常住地，多级英文逗号隔开"));
    sb.properties("registry", stringArray("户籍地，多级英文逗号隔开"));
    sb.properties("residentDetail", string("常住地详细"));
    sb.properties("registryDetail", string("户籍地详细"));
    sb.properties("idCardNo", string("身份证号"));
    sb.properties("education", int32("学历"));
    sb.properties("majorSubject", string("专业学科"));
    sb.properties("majorName", string("专业名称"));
    sb.properties("collegeProvince", string("院校省份"));
    sb.properties("collegeCity", string("院校城市"));
    sb.properties("collegeName", string("院校名称"));
    sb.properties("industry", int32("行业，多选逗号隔开"));
    sb.properties("profession", int32("职业"));
    sb.properties("marriage", int32("婚姻状况"));
    sb.properties("monthlyIncome", int32("月收入"));
    sb.properties("professionOther", string("其他职业描述"));
  });

  public static Schema FrontOnlineSurveyRecordVO = schema(sb -> {
    sb.type("object");
    sb.description("我参与的网调答卷数据");
    sb.title("FrontOnlineSurveyRecordVO");
    sb.properties("renwuName", string("任务名称"));
    sb.properties("id", string("任务ID"));
    sb.properties("wenjuanName", string("问卷名称"));
    sb.properties("wenjuanId", string("问卷ID"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("submitAward", bool("答题有否【固定红包】"));
    sb.properties("shareAward", bool("分享有否红包"));
    sb.properties("submitPrice", doubleNumer("【固定红包】答题奖励"));
    sb.properties("sharePrice", doubleNumer("分享奖励"));
    sb.properties("randomAward", bool("是否有答题随机红包"));
    sb.properties("randomMinPrice", doubleNumer("随机红包最小值"));
    sb.properties("randomMaxPrice", doubleNumer("随机红包最大值"));

    sb.properties("award", doubleNumer("已获红包"));
    sb.properties("shareAwardTotal", doubleNumer("已获分享红包（元）"));
    sb.properties("status", int32("状态: 已答题、未答题、已结束"));
    sb.properties("auditStatus", int32("审核状态"));
    sb.properties("link", string("问卷链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
    sb.properties("shareLink", string("分享链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
    sb.properties("submitAt", dateTime("答卷时间"));
    sb.properties("shareQuantity", int32("已分享数量"));
    sb.properties("surveyLinkOpenFlag", bool("答题链接开启状态"));
  });

  public static Schema HallLatestOrderOnlineSurveyVo = schema(sb -> {
    sb.type("object");
    sb.description("任务大厅-最新任务-网调答卷数据");
    sb.title("HallLatestOrderOnlineSurveyVo");
    sb.properties("renwuName", string("任务名称"));
    sb.properties("id", string("任务ID"));
    sb.properties("wenjuanName", string("问卷名称"));
    sb.properties("wenjuanId", string("问卷ID"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("submitAward", bool("答题有否【固定红包】"));
    sb.properties("shareAward", bool("分享有否红包"));
    sb.properties("submitPrice", doubleNumer("【固定红包】答题奖励"));
    sb.properties("sharePrice", doubleNumer("分享奖励"));

    sb.properties("randomAward", bool("是否有答题随机红包"));
    sb.properties("randomMinPrice", doubleNumer("随机红包最小值"));
    sb.properties("randomMaxPrice", doubleNumer("随机红包最大值"));

    sb.properties("status", int32("状态: 已答题、未答题、已结束"));
    sb.properties("link", string("问卷链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
    sb.properties("shareLink", string("分享链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
  });

  public static void pageCondProperties(SchemaBuilder sb) {
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("total", totalProperty);
  }

  @Deprecated
  public static Schema JoinUserVO = schema(sb -> {
    sb.type("object");
    sb.title("JoinUserVO");
    sb.properties("name", string("姓名"));
    sb.properties("phone", string("联系方式"));
    sb.properties("workCity", string("可工作地区"));
    sb.properties("joinAt", dateTime("报名时间"));
  });

  public static Schema ExternalVO = schema(sb -> {
    sb.type("object");
    sb.description("大厅友情分享任务");
    sb.title("ExternalVO");
    sb.properties("id", int32);
    sb.properties("name", string("友情分享任务名称"));
    sb.properties("from", string("任务来源"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("link", string("任务链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
    sb.properties("describe", string("说明"));
    sb.properties("status", int32("状态, 已过期，未过期"));
  });

  public static Schema OpenOauthAccessTokenVO = schema(sb -> {
    sb.type("object");
    sb.title("OpenOauthAccessTokenVO");
    sb.properties("accessToken", string("接口调用凭证"));
    sb.properties("expiresIn", int64("access_token 接口调用凭证超时时间，单位（秒）"));
    sb.properties("refreshToken", string("用户刷新 access_token"));
    sb.properties("openid", string("授权用户唯一标识"));
    sb.properties("scope", string("用户授权的作用域，使用逗号（,）分隔"));
    sb.properties("unionid", string("当且仅当该移动应用已获得该用户的 userinfo 授权时，才会出现该字段"));
  });

  public static Schema MiniCode2SessionResultVO = schema(sb -> {
    sb.type("object");
    sb.title("MiniCode2SessionResultVO");
    sb.properties("unionid", string("当且仅当该移动应用已获得该用户的 userinfo 授权时，才会出现该字段"));
    sb.properties("errcode", int32);
    sb.properties("errmsg", string);
  });

  public static Schema UserIdentityVO = schema(sb -> {
    sb.type("object");
    sb.title("UserIdentityVO");
    sb.properties("companyTreeyeeId", int32);
    sb.properties("user_name", string);
    sb.properties("companyName", string);
    sb.properties("active", bool);
    sb.properties("authorities", stringArray);
    sb.properties("issuer", string);
    sb.properties("client_id", string);
    sb.properties("companyId", int32);
    sb.properties("isEnterprise", bool);
    sb.properties("expires_at", int64);
    sb.properties("scope", stringArray);
    sb.properties("staffName", string);
    sb.properties("treeyeeId", int32);
    sb.properties("expire_at", string);
    sb.properties("id", int32);
    sb.properties("isCompanyOwner", bool);
    sb.properties("exp", int64);
    sb.properties("treeyeeToken", string);
    sb.properties("username", string);
  });

  public static Schema CustomerCaptialResultVO = schema(sb -> {
    sb.type("object");
    sb.title("CustomerCaptialResultVO");
    sb.properties("balance", int32("账户余额（分）"));
    sb.properties("cashOut", int32("已提现资金额（分）"));
    sb.properties("freezes", int32("冻结金额（分）"));
    sb.properties("id", int32("用户id"));
    sb.properties("reward", int32("可提现资金额（分）"));
    sb.properties("sumBal", int32("累计充值金额（分）"));
    sb.properties("sumReward", int32("累计可提现金额（分）"));
    sb.properties("sumUse", int32("累计花费金额（分）"));
  });

  public static Schema CustomerIdentifyStatusResultVO = schema(sb -> {
    sb.type("object");
    sb.title("CustomerIdentifyStatusResultVO");
    sb.properties("id", int32("用户id/企业id/代理id"));
    sb.properties("status", int32("认证状态 0:未知 1:认证中 2:驳回 3:通过"));
    sb.properties("identifyPass", bool("认证状态是否通过"));
  });

  public static Schema PublisherPhoneVO = schema(sb -> {
    sb.type("object");
    sb.title("PublisherPhoneVO");
    sb.properties("phone", string);
    sb.properties("name", string);
  });

  public static Schema EventVO = schema(sb -> {
    sb.type("object");
    sb.description("事件");
    sb.title("EventVO");
    sb.properties("id", int32);
    sb.properties("name", string("事件名称/描述", "获得5元钱红包"));
    sb.properties("realName", string("用户姓名"));
    sb.properties("createAt", createAtProperty);
  });

  public static Schema ProxyShopVO = schema(sb -> {
    sb.type("object");
    sb.description("代理店铺");
    sb.title("ProxyShopVO");
    sb.properties("serialNo", int32("序号"));
    sb.properties("proxyJobName", string("中标任务名称"));
    sb.properties("proxyCompanyName", string("代理公司名称"));
    sb.properties("contact", string("联系方式"));
    sb.properties("proxyId", int32("店铺id"));
    sb.properties("proxyName", string("店铺名称"));
    sb.properties("belongsTo", string("用户类型"));
    sb.properties("relationId", int32("用户/企业id"));
  });

  public static Schema ProxyJobDTO = schema(sb -> {
    sb.type("object");
    sb.title("ProxyJobDTO");
    sb.properties("id", int32);
    sb.properties("userId", int32("雇主id"));
    sb.properties("companyId", int32("雇主companyId"));
    sb.properties("auditReason", string);
    sb.properties("status", int32);
    sb.properties("type", int32);
    sb.properties("typeName", string);
    sb.properties("version", int32);
    sb.properties("isRead", bool);

    sb.properties("shopName", string("个人/企业名称"));
    sb.properties("shopLogo", string("个人/企业头像"));
    sb.properties("isEnterprise", bool("是否企业"));
    sb.properties("isPublisher", bool("是否是发布者"));
    sb.properties("visitorListUrl", string("查看报名列表url"));
  });

  public static Schema OfflineSurveyWorkAreaDTO = schema(sb -> {
    sb.type("object");
    sb.description("可工作地区");
    sb.title("OfflineSurveyWorkAreaDTO");
    sb.properties("serialNo", int32);
    sb.properties("id", int32);
    sb.properties("proxyJobId", int32);
    sb.properties("name", string);
    sb.properties("note", string);
    sb.properties("amount", int32("任务数量"));
    sb.properties("price", doubleNumer);
  });

  public static Schema OfflineSurveyVisitorWorkAreaDTO = schema(sb -> {
    sb.type("object");
    sb.description("面访招募人员-可工作地区");
    sb.title("OfflineSurveyVisitorWorkAreaDTO");
    sb.properties("serialNo", int32);
    sb.properties("id", int32);
    sb.properties("offlineSurveyVisitorId", int32);
    sb.properties("name", string);
    sb.properties("note", string);
    sb.properties("amount", int32("任务数量"));
    sb.properties("price", doubleNumer);
  });

  public static Schema JianchaDianweiDTO = schema(sb -> {
    sb.type("object");
    sb.description("可工作地区");
    sb.title("JianchaDianweiDTO");
    sb.properties("serialNo", int32);
    sb.properties("id", int32);
    sb.properties("proxyJobId", int32);
    sb.properties("name", string);
    sb.properties("note", string);
    sb.properties("amount", int32("点位数量"));
    sb.properties("price", doubleNumer);
  });

  public static Schema OfflineSurveyInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("OfflineSurveyInfoVO");
    sb.properties("proxyJobId", int32);
    sb.properties("name", string);
    sb.properties("titleAmount", int32("题目数量"));
    sb.properties("endAt", endAtProperty);
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("finishAt", int64("任务到达已结束状态时间"));
    sb.properties("hasUnselectedShop", bool("有店铺报名且雇主并未操作过报名店铺"));
    sb.properties("describe", string);
    sb.properties("settleSpec", string("结算标准"));
    sb.properties("qualitySpec", string("质控标准"));
    sb.properties("workArea", refArray("可工作地区", OfflineSurveyWorkAreaDTO.getTitle()));
  });

  public static Schema CustomerIdentifyRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("CustomerIdentifyRequestVO");
    sb.properties("customerName", string("个人姓名"));
    sb.properties("idcard", string("法人身份证号"));
    sb.properties("idcardFront", string("身份证人像面照"));
    sb.properties("idcardBack", string("身份证国徽面照"));
  });

  public static Schema CustomerIdentifyInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("CustomerIdentifyInfoVO");
    sb.properties("identifyId", int64("认证Id"));
    sb.properties("customerId", int64("用户Id"));
    sb.properties("customerName", string("个人姓名"));
    sb.properties("idcard", string("法人身份证号"));
    sb.properties("idcardFront", string("身份证人像面照"));
    sb.properties("idcardBack", string("身份证国徽面照"));
    sb.properties("identify", int64("认证结果 认证结果 1:认证中2:驳回3:通过"));
  });

  public static Schema InterviewerIdentifyRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("InterviewerIdentifyRequestVO");
    sb.properties("name", string("用户姓名"));
    sb.properties("idCard", int64("身份证号"));
  });

  public static Schema InterviewerIdentifyVO = schema(sb -> {
    sb.type("object");
    sb.title("InterviewerIdentifyVO");
    sb.properties("customerId", string("用户id"));
    sb.properties("name", string("用户姓名"));
    sb.properties("idCard", int64("身份证号"));
  });

  public static Schema CompanyIdentifyRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("CompanyIdentifyRequestVO");
    sb.properties("companyId", int64("企业Id"));
    sb.properties("companyName", string("企业名称"));
    sb.properties("license", string("统一社会信用代码"));
    sb.properties("location", string("营业执照所在地"));
    sb.properties("address", string("常用地址"));
    sb.properties("foundedType", int64("营业期限类型 1:有期限 2:无期限"));
    sb.properties("founded", int64("营业期限"));
    sb.properties("scope", string("经营范围"));
    sb.properties("licenseUrl", string("营业执照副本扫描件"));
    sb.properties("juridical", string("法人姓名"));
    sb.properties("idcard", string("法人身份证号"));
    sb.properties("idcardFront", string("身份证人像面照"));
    sb.properties("idcardBack", string("身份证国徽面照"));
  });

  public static Schema CompanyIdentifyInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("CompanyIdentifyInfoVO");
    sb.properties("identifyId", int64("认证Id"));
    sb.properties("companyId", int64("企业Id"));
    sb.properties("companyName", string("企业名称"));
    sb.properties("license", string("统一社会信用代码"));
    sb.properties("location", string("营业执照所在地"));
    sb.properties("address", string("常用地址"));
    sb.properties("foundedType", int64("营业期限类型 1:有期限 2:无期限"));
    sb.properties("founded", int64("营业期限"));
    sb.properties("scope", string("经营范围"));
    sb.properties("licenseUrl", string("营业执照副本扫描件"));
    sb.properties("juridical", string("法人姓名"));
    sb.properties("idcard", string("法人身份证号"));
    sb.properties("idcardFront", string("身份证人像面照"));
    sb.properties("idcardBack", string("身份证国徽面照"));
    sb.properties("identify", int64("认证结果 1:认证中2:驳回3:通过"));
  });

  public static Schema OfflineSurveyVisitorInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("OfflineSurveyVisitorInfoVO");
    sb.properties("id", int64);
    sb.properties("name", string);
    sb.properties("typeName", string("任务类型"));
    sb.properties("isPublisher", bool("是否是发布者"));
    sb.properties("visitorListUrl", string("查看报名列表url"));
    sb.properties("status", int32("审批状态"));
    sb.properties("titleAmount", int32("题目数量"));
    sb.properties("endAt", endAtProperty);
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("finishAt", int64("任务到达已结束状态时间"));
    sb.properties("hasUnselectedShop", bool("有店铺报名且雇主并未操作过报名店铺"));
    sb.properties("describe", string);
    sb.properties("settleSpec", string("结算标准"));
    sb.properties("qualitySpec", string("质控标准"));
    sb.properties("workArea", refArray("可工作地区", OfflineSurveyVisitorWorkAreaDTO.getTitle()));
  });

  public static Schema JianchaInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("JianchaInfoVO");
    sb.properties("proxyJobId", int32);
    sb.properties("name", string);
    sb.properties("endAt", endAtProperty);
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("finishAt", int64("任务到达已结束状态时间"));
    sb.properties("hasUnselectedShop", bool("有店铺报名且雇主并未操作过报名店铺"));
    sb.properties("describe", string);
    sb.properties("settleSpec", string("结算标准"));
    sb.properties("qualitySpec", string("质控标准"));
    sb.properties("dianwei", refArray("检查点位", JianchaDianweiDTO.getTitle()));
  });

  public static Schema ProxyJobOfflineSurveyInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("ProxyJobOfflineSurveyInfoVO");
    sb.properties("proxyJob", reference(proxyJob -> {
      proxyJob.ref(ProxyJobDTO.getTitle());
    }));
    sb.properties("offlineSurvey", reference(offlineSurvey -> {
      offlineSurvey.ref(OfflineSurveyInfoVO.getTitle());
    }));
  });

  public static Schema ProxyJobJianchaInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("ProxyJobJianchaInfoVO");
    sb.properties("proxyJob", reference(proxyJob -> {
      proxyJob.ref(ProxyJobDTO.getTitle());
    }));
    sb.properties("jiancha", reference(offlineSurvey -> {
      offlineSurvey.ref(JianchaInfoVO.getTitle());
    }));
  });


  public static Schema EventAddDTO = schema(sb -> {
    sb.type("object");
    sb.title("EventAddDTO");
    sb.properties("content", string("新鲜事内容"));
    sb.properties("companyId", int32);
  });

  public static Schema AdminOnlineSurveyRecordVO = schema(sb -> {
    sb.type("object");
    sb.title("AdminOnlineSurveyRecordVO");
    sb.description("网调任务记录");
    sb.properties("renwuName", string("任务名称"));
    sb.properties("id", string("任务id"));
    sb.properties("top", bool("置顶标志"));
    sb.properties("wenjuanName", string("问卷名称"));
    sb.properties("wenjuanId", string("问卷ID"));
    sb.properties("publishBy", string("发布人"));
    sb.properties("publishName", string("发布人"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("status", int32);
  });

  public static Schema AuditRequestVO = schema(sb -> {
    sb.type("object");
    sb.description("审批信息");
    sb.title("AuditRequestVO");
    sb.properties("id", string("任务id"));
    sb.properties("auditNote", string("审批说明"));
    sb.properties("status", int32("审批状态"));
  });

  public static Schema OnlineSurveyAuditRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("OnlineSurveyAuditRequestVO");
    sb.properties("auditList", refArray(AuditRequestVO.getTitle()));
  });

  public static Schema AdminOnlineSurveyAuditVO = schema(sb -> {
    sb.type("object");
    sb.description("网调审核记录");
    sb.title("AdminOnlineSurveyAuditVO");
    sb.properties("id", string("任务ID"));
    sb.properties("wenjuanName", string("问卷名称"));
    sb.properties("wenjuanId", string("问卷ID"));
    sb.properties("createAt", pushAtProperty);
    sb.properties("status", int32);
    sb.properties("wenjuanLink", string("问卷链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
  });

  public static Schema ProxyJobAddRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("ProxyJobAddRequestVO");
    sb.properties("offlineSurvey", reference(offlineSurvey -> {
      offlineSurvey.ref(OfflineSurveyInfoVO.getTitle());
    }));
    sb.properties("jiancha", reference(jiancha -> {
      jiancha.ref(JianchaInfoVO.getTitle());
    }));

  });

  public static Schema NewsVO = schema(sb -> {
    sb.type("object");
    sb.title("NewsVO");
    sb.properties("id", int32);
    sb.properties("title", string("资讯标题"));
    sb.properties("publishAt", publishAtProperty);
    sb.properties("content", string("正文（富文本）"));
  });

  public static Schema FrontOnlineSurveyFoundVO = schema(sb -> {
    sb.type("object");
    sb.title("FrontOnlineSurveyFoundVO");
    sb.description("我发起的网调问卷信息");
    sb.properties("surveyId", string("问卷id"));
    sb.properties("surveyName", string("问卷名称"));
    sb.properties("pushTime", pushAtProperty);
    sb.properties("status", int32("审核状态"));
    sb.properties("link", string("问卷链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
  });

  public static Schema FrontUserInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("FrontUserInfoVO");
    sb.properties("name", string("姓名"));
    sb.properties("sex", int32("100：男，200：女"));
    sb.properties("email", string);
    sb.properties("extraInfo", reference(extraInfo -> {
      extraInfo.ref(UserExtraInfoDTO.getTitle());
    }));
    sb.properties("hasMonthEdit", bool("本月是否已经更新过个人信息"));
  });

  public static Schema ShopPageConditionVO = schema(sb -> {
    sb.type("object");
    sb.title("ShopPageConditionVO");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);

    sb.properties("proxyJobId", int32);
    sb.properties("shopRecommendApplyStatus", int32Array);

    sb.properties("type", int32("0个人 1企业"));
    sb.properties("name", string("名称模糊查询"));
    sb.properties("phone", string("联系方式模糊查询"));
    sb.properties("weixinNickname", string("微信昵称模糊查询"));


    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema ShopDetailVO = schema(sb -> {
    sb.type("object");
    sb.title("ShopDetailVO");
    sb.properties("id", int32);
    sb.properties("logo", string("店铺logo"));
    sb.properties("name", string("店铺名称"));
    sb.properties("info", string("店铺描述"));
    sb.properties("contact", string("店铺联系人"));
    sb.properties("contactName", string("联系人名称"));
    sb.properties("goodAt", string);
    sb.properties("workCity", string);
    sb.properties("userId", int32);
    sb.properties("companyId", int32);
    sb.properties("createAt", publishAtProperty);
    sb.properties("shopHasProxyJobStatus", int32("状态"));
    sb.properties("isEnterprise", bool("是否企业，若非企业，则为个人"));
    sb.properties("workCityList", workCityListProperty);
    sb.properties("goodAtList", goodAtListProperty);

    sb.properties("phone", string("联系方式"));
    sb.properties("weixinNickname", string("微信昵称"));
  });

  public static Schema ShopHasProxyJobOfflineSurveySearchCondition = schema(sb -> {
    sb.type("object");
    sb.title("ShopHasProxyJobOfflineSurveySearchCondition");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("shopId", int32);
    sb.properties("status", int32Array("状态"));
    sb.properties("offlineEndAtEnd", dateTime("截止时间范围结束"));
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema ShopHasProxyJobJianchaSearchConditionVO = schema(sb -> {
    sb.type("object");
    sb.title("ShopHasProxyJobJianchaSearchConditionVO");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("shopId", int32);
    sb.properties("status", int32Array("状态"));
    sb.properties("jianchaEndAtEnd", dateTime("截止时间范围结束"));

    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);

    sb.properties("shopId", int32);
    sb.properties("status", int32Array("选中/淘汰状态"));
    sb.properties("jianchaEndAtEnd", dateTime("截止时间范围结束"));
  });


  public static Schema ProxyJobOfflineSurveyDetailVO = schema(sb -> {
    sb.type("object");
    sb.title("ProxyJobOfflineSurveyDetailVO");
    sb.properties("id", int32);
    sb.properties("top", bool);

    sb.properties("userId", int32);
    sb.properties("companyId", int32);
    sb.properties("version", int32);
    sb.properties("proxyJobId", int32);
    sb.properties("status", int32("状态"));
    sb.properties("createAt", pushAtProperty);
    sb.properties("updateAt", updateAtProperty);
    sb.properties("publishName", publishNameProperty);
    sb.properties("publishLogo", publishLogoProperty);

    sb.properties("name", string("任务名称"));
    sb.properties("titleAmount", int32("题目数量"));
    sb.properties("auditNote", string("审批说明"));
    sb.properties("publisherId", int32("发布人调研工厂的账号id"));
    sb.properties("endAt", endAtProperty);
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("describe", string("说明"));
    sb.properties("finishAt", publishAtProperty);
    sb.properties("joinQuantity", int32("店铺参与数量"));
    sb.properties("selectedQuantity", int32("选中店铺数量"));
    sb.properties("price", doubleNumer);
    sb.properties("jobTotal", int32("任务总数量"));
    sb.properties("userQuantity", int32("报名数量"));
    sb.properties("workAreaNames", stringArray("可工作地区名称数组"));
    sb.properties("workArea", string("可工作地区名称"));
  });

  public static Schema ProxyJobJianchaDetailVO = schema(sb -> {
    sb.type("object");
    sb.title("ProxyJobJianchaDetailVO");
    sb.properties("id", int32);
    sb.properties("top", bool);
    sb.properties("userId", int32);
    sb.properties("companyId", int32);
    sb.properties("version", int32);
    sb.properties("proxyJobId", int32);
    sb.properties("status", int32("状态"));
    sb.properties("createAt", pushAtProperty);
    sb.properties("updateAt", updateAtProperty);
    sb.properties("name", string("任务名称"));
    sb.properties("publishName", publishNameProperty);
    sb.properties("publishLogo", publishLogoProperty);

    sb.properties("auditNote", string("审批说明"));
    sb.properties("publisherId", int32("发布人调研工厂的账号id"));
    sb.properties("endAt", endAtProperty);
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("describe", string("说明"));
    sb.properties("finishAt", publishAtProperty);
    sb.properties("joinQuantity", int32("店铺参与数量"));
    sb.properties("selectedQuantity", int32("选中店铺数量"));
    sb.properties("price", doubleNumer);
    sb.properties("userQuantity", int32("报名数量"));
    sb.properties("dianweiNames", stringArray("可工作地区名称数组"));
    sb.properties("workArea", string("可工作地区名称"));

  });

  public static Schema LatestOrderResultDTO = schema(sb -> {
    sb.type("object");
    sb.title("LatestOrderResultDTO");
    sb.properties("createAt", dateTime);
    sb.properties("name", string);
    sb.properties("price", doubleNumer);
    sb.properties("isOnline", bool);
    sb.properties("isOffline", bool);
    sb.properties("isJiancha", bool);
    sb.properties("onlineSurvey", reference(rb -> {
      rb.ref(HallLatestOrderOnlineSurveyVo.getTitle());
    }));
    sb.properties("offlineSurvey", reference(rb -> {
      rb.ref(ProxyJobOfflineSurveyDetailVO.getTitle());
    }));
    sb.properties("jiancha", reference(rb -> {
      rb.ref(ProxyJobJianchaDetailVO.getTitle());
    }));
  });

  public static Schema ProxyJobCondition = schema(sb -> {
    sb.type("object");
    sb.title("ProxyJobCondition");
    sb.required("sort");
    sb.required("current");
    sb.required("limit");
    sb.required("page");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);

    sb.properties("companyId", int32);
    sb.properties("userId", int32);
    sb.properties("name", string("任务名称（模糊查询）"));
    sb.properties("publishName", string("发布人名称（模糊查询）"));
    sb.properties("status", int32Array("状态"));
    sb.properties("topStatus", topStatusProperty);
    sb.properties("offlineEndAtEnd", offlineEndAtEndProperty);
    sb.properties("jianchaEndAtEnd", jianchaEndAtEndProperty);

    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema FrontUserOnlineSurveyRecordCondition = schema(sb -> {
    sb.type("object");
    sb.title("FrontUserOnlineSurveyRecordCondition");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("status", int32Array);
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema JianchaJoinCondition = schema(sb -> {
    sb.type("object");
    sb.title("JianchaJoinCondition");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("phoneSensitive", bool("联系方式是否需要脱敏"));
    sb.properties("jianchaId", int64("明察暗访任务id"));
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema ApiShopProxySearchConditionVO = schema(sb -> {
    sb.type("object");
    sb.title("ApiShopProxySearchConditionVO");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);

    sb.properties("treeyeeCompanyId", int32("基础模块企业id"));
    sb.properties("treeyeeUserId", int32("基础模块用户id"));
    sb.properties("nameOrPhone", string("店铺名称或手机号（模糊查询）"));
    sb.properties("status", int32Array("淘汰/选中状态"));

    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema OnlineSurveyAuditCondition = schema(sb -> {
    sb.type("object");
    sb.title("OnlineSurveyAuditCondition");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("name", string("问卷名称"));
    sb.properties("status", int32Array);
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema ShopRecommendApplyDetailVO = schema(sb -> {
    sb.type("object");
    sb.title("ShopRecommendApplyDetailVO");
    sb.properties("id", int32);
    sb.properties("shopId", int32);
    sb.properties("userId", int32);
    sb.properties("companyId", int32);
    sb.properties("applyReason", string("申请原因"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("status", int32);
    sb.properties("version", int32);
    sb.properties("shopLogo", string("店铺logo"));
    sb.properties("shopName", string("店铺名称"));
    sb.properties("shopGoodAt", string("店铺擅长"));
    sb.properties("shopWorkCity", string("店铺可工作地区"));
    sb.properties("shopInfo", string("店铺描述"));
    sb.properties("shopUserId", int32("店铺用户id"));
    sb.properties("shopCompanyId", int32("店铺企业id"));
    sb.properties("shopContact", string("店铺联系人"));
    sb.properties("shopContactName", string("店铺联系人名称"));
    sb.properties("shopStatInfo", reference(shopStatInfo -> {
      shopStatInfo.ref(ShopStatInfoDto.getTitle());
    }));
    sb.properties("isEnterprise", bool("是否企业，若非企业，则为个人"));
    sb.properties("shopGoodAtList", goodAtListProperty);
    sb.properties("shopWorkCityList", workCityListProperty);
  });

  public static Schema ShopInfoVO = schema(sb -> {
    sb.type("object");
    sb.description("店铺详情");
    sb.title("ShopInfoVO");
    sb.properties("id", int32);
    sb.properties("logo", string("店铺logo"));
    sb.properties("name", string("店铺名称"));
    sb.properties("goodAt", stringArray("店铺擅长"));
    sb.properties("workCity", stringArray("店铺可工作地区"));
    sb.properties("info", string("店铺描述"));
    sb.properties("contact", string("店铺联系人"));
    sb.properties("contactName", string("联系人名称"));
    sb.properties("statInfo", reference(shopStatInfo -> {
      shopStatInfo.ref(ShopStatInfoDto.getTitle());
    }));
    sb.properties("infoText", string("店铺描述纯文本"));
  });

  public static Schema ShopRecommendApplyInfoDTO = schema(sb -> {
    sb.type("object");
    sb.title("ShopRecommendApplyInfoDTO");
    sb.properties("id", int32);
    sb.properties("shopId", int32);
    sb.properties("applyReason", string("申请原因"));
    sb.properties("auditReason", string("审批原因"));
    sb.properties("status", int32);
    sb.properties("shopInfo", reference(shopStatInfo -> {
      shopStatInfo.ref(ShopInfoVO.getTitle());
    }));
  });

  public static Schema AuditCountUnReadVO = schema(sb -> {
    sb.type("object");
    sb.title("AuditCountUnReadVO");
    sb.properties("waitAudit", int32("待审核未读数量"));
    sb.properties("auditPass", int32("审核通过未读数量"));
    sb.properties("auditReject", int32("审核拒绝未读数量"));
    sb.properties("finish", int32("已结束未读数量"));
  });

  public static Schema JobTotalVO = schema(sb -> {
    sb.type("object");
    sb.title("JobTotalVO");

    sb.properties("foundProxyJobTotal", int32("【弃用】我发起的：代理任务数量"));


    sb.properties("foundOnlineTotal", int32("我发起的：网调任务数量"));
    sb.properties("foundProxyJobOfflineTotal", int32("我发起的：代理任务面访数量"));
    sb.properties("foundProxyJobJianchaTotal", int32("我发起的：代理任务明察暗访数量"));
    sb.properties("foundRecruitTotal", int32("我发起的：招募人员数量"));


    sb.properties("joinOnlineTotal", int32("我参与的：网调任务数量"));
    sb.properties("joinProxyJobOfflineTotal", int32("我参与的：代理任务面访数量"));
    sb.properties("joinProxyJobJianchaTotal", int32("我参与的：代理任务明察暗访数量"));
  });

  public static Schema SurveyAwardResultVO = schema(sb -> {
    sb.type("object");
    sb.title("SurveyAwardResultVO");
    sb.properties("totalShare", doubleNumer("累计分享红包"));
    sb.properties("totalSubmit", doubleNumer("累计答卷红包"));
    sb.properties("total", doubleNumer("总计获得红包"));
  });

  public static Schema SubmitStatResultVO = schema(sb -> {
    sb.type("object");
    sb.title("SubmitStatResultVO");
    sb.properties("submitQuantity", int32("已答问卷"));
    sb.properties("submitPass", int32("质控通过问卷"));
    sb.properties("submitPassPercent", doubleNumer("质控通过率 单位100%"));
  });

  public static Schema HallStatResultDTO = schema(sb -> {
    sb.type("object");
    sb.title("HallStatResultDTO");
    sb.properties("publishQuantity", int64);
    sb.properties("incomeQuantity", doubleNumer);
  });

  public static Schema SampleDictDTO = schema(sb -> {
    sb.type("object");
    sb.title("SampleDictDTO");
    sb.properties("key", int32);
    sb.properties("value", string);
  });

  public static Schema CountryDataVO = schema(sb -> {
    sb.type("object");
    sb.title("CountryDataVO");
    sb.isTree(true);
    sb.properties("value", string);
    sb.properties("label", string);
    sb.properties("children", refArray("CountryDataVO"));
  });

  public static Schema RankVO = schema(sb -> {
    sb.type("object");
    sb.title("RankVO");
    sb.properties("serialNo", int32);
    sb.properties("avatar", string("头像url",
        "https://treeyee-spire.oss-cn-beijing.aliyuncs.com/cddf0ecc-a03e-4c16-8757-92bd7c4800ba1592982748849.jpg"));
    sb.properties("name", string);
    sb.properties("income", doubleNumer("累计收入"));
    sb.properties("quantity", int32("完成任务数量"));
  });

  public static Schema TransactionListItemVO = schema(sb -> {
    sb.type("object");
    sb.description("查询数据列表");
    sb.title("TransactionListItemVO");
    sb.properties("amount", int32("金额(单位分)"));
    sb.properties("billNo", string("付款/提现流水号"));
    sb.properties("cashOut", int32);
    sb.properties("companyId", int32("收入方（企业）"));
    sb.properties("createdAt", int64("日期"));
    sb.properties("customerId", int32("收入方（用户）"));
    sb.properties("freezesId", int32("冻结记录id"));
    sb.properties("fromCompany", int32("支付方（企业）"));
    sb.properties("fromCustomer", int32("支付方（用户）"));
    sb.properties("id", int32("交易记录id"));
    sb.properties("note", string("支付说明"));
    sb.properties("payType", int32("付款方式 0:未知 1:微信 2:支付宝 3:其他"));
    sb.properties("reward", bool("活动奖励同源标记"));
    sb.properties("service", int32("服务模块(渠道) 1:问卷吧 2:检查吧 3:督导吧 4:培训吧 5;充值 6:提现 7:账户间资金转移 8:账户内资金转移"));
    sb.properties("status", int32("状态 0:完成 1:失败 2:待领取"));
    sb.properties("tradeNo", string("交易流水号"));
    sb.properties("type", int32("交易类型 1:充值 2:提现 3:领取奖励 4:活动发奖 5:账户间资金转移 6:账户内资金转移（废） 7：活动冻结 8：活动清算 9：企业专属域名（废） 10：企业专属续费 （废）11：答题分享奖励 12：面访任务广场任务结算 13：线下面访任务结算 14：督导吧执行任务结算"));
  });

  public static Schema PageResultVO = schema(sb -> {
    sb.type("object");
    sb.title("PageResultVO");
    sb.properties("items", ListT);
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("pages", int32("当前分页总页数"));
    sb.properties("offset", offsetProperty);
  });

  public static Schema PageResultVOShopRecommendApplyDetailVO = generic(gb -> {
    gb.generic(PageResultVO, reference(rb -> {
      rb.ref(ShopRecommendApplyDetailVO.getTitle());
    }));
  });


  public static Schema PageResultVOShopDetailVO = generic(gb -> {
    gb.generic(PageResultVO, reference(rb -> {
      rb.ref(ShopDetailVO.getTitle());
    }));
  });

  public static Schema PageResultVOShopHasProxyJobOfflineSurveyVO = generic(gb -> {
    gb.generic(PageResultVO, reference(rb -> {
      rb.ref(ProxyJobOfflineSurveyDetailVO.getTitle());
    }));
  });

  public static Schema PageResultVOShopHasProxyJobJianchaVO = generic(gb -> {
    gb.generic(PageResultVO, reference(rb -> {
      rb.ref(ProxyJobJianchaDetailVO.getTitle());
    }));
  });

  public static Schema PageResultVOFrontOnlineSurveyRecordVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(FrontOnlineSurveyRecordVO.getTitle()))));

  public static Schema PageResultVOFrontOnlineSurveyFoundVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(FrontOnlineSurveyFoundVO.getTitle()))));

  public static Schema PageResultVONewsVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(NewsVO.getTitle()))));

  public static Schema PageResultVOJoinUserVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(JoinUserVO.getTitle()))));

  public static Schema PageResultVOEventVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(EventVO.getTitle()))));

  public static Schema PageResultVOProxyShopVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ProxyShopVO.getTitle()))));

  public static Schema PageResultVOAdminOnlineSurveyRecordVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(AdminOnlineSurveyRecordVO.getTitle()))));

  public static Schema PageResultVOAdminOnlineSurveyAuditVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(AdminOnlineSurveyAuditVO.getTitle()))));

  public static Schema PageResultVOProxyJobOfflineSurveyVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ProxyJobOfflineSurveyDetailVO.getTitle()))));

  public static Schema PageResultVOProxyJobJianchaVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ProxyJobJianchaDetailVO.getTitle()))));

  public static Schema PageResultVOExternalVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ExternalVO.getTitle()))));

  public static Schema PageResultVOTransactionListItemVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(TransactionListItemVO.getTitle()))));

  public static Schema ResultDTOString = generic(gb -> gb.generic(ResultDTO, string));

  public static Schema BaseSearchCondition = schema(sb -> {
    sb.type("object");
    sb.title("BaseSearchCondition");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);
    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);
  });

  public static Schema ResultDTOPageResultVOTransactionListItemVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOTransactionListItemVO.getTitle()))));

  public static Schema SendPhoneCodeResultVO = schema(sb -> {
    sb.type("object");
    sb.title("SendPhoneCodeResultVO");
    sb.properties("success", bool);
    sb.properties("message", string);
  });

  public static Schema ResultDTOSendPhoneCodeResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SendPhoneCodeResultVO.getTitle()))));

  public static Schema BindPhoneRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("BindPhoneRequestVO");
    sb.properties("username", string("手机号"));
    sb.properties("pw", string("密码"));
    sb.properties("code", string("验证码"));
    sb.properties("force", bool("是否强制绑定，一般用false"));
  });

  public static Schema BindingResultVO = schema(sb -> {
    sb.type("object");
    sb.title("BindingResultVO");

    sb.properties("success", bool);
    sb.properties("message", string);
    sb.properties("needForce", bool("是否需要强制绑定"));
  });

  public static Schema ResultDTOBindingResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(BindingResultVO.getTitle()))));

  public static Schema ResultDTOInteger = generic(gb -> gb.generic(ResultDTO, int32));

  public static Schema ResultDTOPageResultVOShopRecommendApplyDetailVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOShopRecommendApplyDetailVO.getTitle()))));

  public static Schema ResultDTOPageResultVOShopDetailVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOShopDetailVO.getTitle()))));

  // TODO
  public static Schema ResultDTOPageResultVOShopHasProxyJobOfflineSurveyVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOShopHasProxyJobOfflineSurveyVO.getTitle()))));

  public static Schema ResultDTOPageResultVOShopHasProxyJobJianchaVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOShopHasProxyJobJianchaVO.getTitle()))));


  public static Schema ResultDTOPageResultVOProxyJobOfflineSurveyDetailVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOShopHasProxyJobOfflineSurveyVO.getTitle()))));

  public static Schema ResultDTOPageResultVOProxyJobJianchaDetailVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOShopHasProxyJobJianchaVO.getTitle()))));

  public static Schema ResultDTOPageResultVOFrontOnlineSurveyRecordVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOFrontOnlineSurveyRecordVO.getTitle()))));

  public static Schema ResultDTOPageResultVOFrontOnlineSurveyFoundVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOFrontOnlineSurveyFoundVO.getTitle()))));

  public static Schema ResultDTOVoid = generic(gb -> gb.generic(ResultDTO, schema(data -> data.type("object"))));

  public static Schema ResultDTOPageResultVOHallOnlineSurveyVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOFrontOnlineSurveyRecordVO.getTitle()))));

  public static Schema ResultDTOPageResultVONewsVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVONewsVO.getTitle()))));

  public static Schema ResultDTOPageResultVOJoinUserVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOJoinUserVO.getTitle()))));

  public static Schema ResultDTOPageResultVOHallExternalVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOExternalVO.getTitle()))));

  public static Schema ResultDTOPageResultVOEventVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOEventVO.getTitle()))));

  public static Schema FeignResultDTOPageResultVOProxyShopVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOProxyShopVO.getTitle()))));

  public static Schema ResultDTOPageResultVOAdminOnlineSurveyRecordVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOAdminOnlineSurveyRecordVO.getTitle()))));

  public static Schema ResultDTOPageResultVOAdminOnlineSurveyAuditVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOAdminOnlineSurveyAuditVO.getTitle()))));

  public static Schema ResultDTOPageResultVOProxyJobOfflineSurveyVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOProxyJobOfflineSurveyVO.getTitle()))));

  public static Schema ResultDTOPageResultVOProxyJobJianchaVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOProxyJobJianchaVO.getTitle()))));

  public static Schema ResultDTOPageResultVOExternalVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOExternalVO.getTitle()))));

  public static Schema ResultDTOOpenOauthAccessTokenVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(OpenOauthAccessTokenVO.getTitle()))));

  public static Schema ResultDTOMiniCode2SessionResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(MiniCode2SessionResultVO.getTitle()))));

  public static Schema ResultDTOUserIdentityVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(UserIdentityVO.getTitle()))));

  public static Schema ResultDTOBoolean = generic(gb -> gb.generic(ResultDTO, bool));

  public static Schema ResultDTOCustomerCaptialResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(CustomerCaptialResultVO.getTitle()))));

  public static Schema ResultDTOCustomerIdentifyStatusResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(CustomerIdentifyStatusResultVO.getTitle()))));

  public static Schema ResultDTOInterviewerIdentifyResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(InterviewerIdentifyVO.getTitle()))));

  public static Schema ResultDTOCustomerIdentifyResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(CustomerIdentifyInfoVO.getTitle()))));

  public static Schema ResultDTOCompanyIdentifyResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(CompanyIdentifyInfoVO.getTitle()))));


  public static Schema TreeyeeCompanyInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("TreeyeeCompanyInfoVO");
    sb.properties("id", int32("id"));
    sb.properties("name", string("公司名称"));
    sb.properties("industry", string);
    sb.properties("logo", string);
    sb.properties("status", int32);
    sb.properties("identify", int32);
  });

  public static Schema ResultDTOTreeyeeCompanyInfoVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(TreeyeeCompanyInfoVO.getTitle()))));

  public static Schema TreeyeeUserInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("TreeyeeUserInfoVO");
    sb.properties("username", string);
    sb.properties("name", string);
    sb.properties("photo", string);
    sb.properties("id", int32);
    sb.properties("loginName", string);
    sb.properties("phone", string);
    sb.properties("phoneBinding", bool);
    sb.properties("weixinBinding", bool);
    sb.properties("weixinLittleAppBinding", bool);
    sb.properties("companySpecialAccount", bool);
    sb.properties("weixinNickName", string);
    sb.properties("weixinHeadimgurl", string);
    sb.properties("superAdmin", bool);
    sb.properties("newRegist", bool);
    sb.properties("identify", int32);

  });

  public static Schema ResultDTOTreeyeeUserInfoVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(TreeyeeUserInfoVO.getTitle()))));

  public static Schema CompanyStaffResultVO = schema(sb -> {
    sb.type("object");
    sb.title("CompanyStaffResultVO");

    sb.properties("staffId", int32);
    sb.properties("staffName", string);
    sb.properties("companyId", int32);
    sb.properties("companyName", string);
    sb.properties("join", bool);
    sb.properties("owner", int32);
    sb.properties("logo", string);
    sb.properties("joinFlag", bool);
    sb.properties("effect", bool);
    sb.properties("identify", int32);

  });

  public static Schema ResultDTOListCompanyStaffResultVO = generic(gb -> gb.generic(ResultDTO, refArray(CompanyStaffResultVO.getTitle())));

  public static Schema ResultDTOPublisherPhoneVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PublisherPhoneVO.getTitle()))));

  public static Schema ResultDTOShopRecommendApplyInfoDTO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(ShopRecommendApplyInfoDTO.getTitle()))));

  public static Schema ResultDTOShopInfoVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(ShopInfoVO.getTitle()))));

  public static Schema ResultDTOAuditCountUnReadVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(AuditCountUnReadVO.getTitle()))));

  public static Schema ResultDTOJobTotalVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(JobTotalVO.getTitle()))));

  public static Schema ResultDTOSurveyAwardResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SurveyAwardResultVO.getTitle()))));

  public static Schema ResultDTOSubmitStatResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SubmitStatResultVO.getTitle()))));

  public static Schema ResultDTOFrontUserInfoVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(FrontUserInfoVO.getTitle()))));

  public static Schema ResultDTOHallStatResultDTO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(HallStatResultDTO.getTitle()))));

  public static Schema ResultDTOListRankAwardResultVO = generic(gb -> gb.generic(ResultDTO, refArray(RankVO.getTitle())));

  public static Schema ResultDTOProxyJobOfflineSurveyInfoVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(ProxyJobOfflineSurveyInfoVO.getTitle()))));

  public static Schema ResultDTOOfflineSurveyVisitorInfoVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(OfflineSurveyVisitorInfoVO.getTitle()))));

  public static Schema ResultDTOProxyJobJianchaInfoVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(ProxyJobJianchaInfoVO.getTitle()))));

  public static Schema ResultDTONewsVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(NewsVO.getTitle()))));

  public static Schema ResultDTOListLatestOrderResultDTO = generic(gb -> gb.generic(ResultDTO, refArray(LatestOrderResultDTO.getTitle())));

  public static Schema ResultDTOExternalVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(ExternalVO.getTitle()))));

  public static Schema ResultDTOMapStringString = generic(gb -> gb.generic(ResultDTO, schema(data -> {
    data.type("object");
    data.additionalProperties(schema(ab -> {
      ab.type("string");
    }));
  })));

  public static Schema ResultDTOListSampleDictDTO = generic(gb -> gb.generic(ResultDTO, refArray(SampleDictDTO.getTitle())));

  public static Schema ResultDTOListCountryDataVO = generic(gb -> gb.generic(ResultDTO, refArray(CountryDataVO.getTitle())));

  public static Schema ResultDTOSetString = generic(gb -> gb.generic(ResultDTO, schema(data -> {
    data.uniqueItems(true);
    data.type("array");
    data.items(schema(items -> {
      items.type("string");
    }));
  })));

  public static Schema ResultDTOListString = generic(gb -> gb.generic(ResultDTO, stringArray));

  public static Schema TreeyeeCustomerCashOutRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("TreeyeeCustomerCashOutRequestVO");
    sb.properties("cash", int64("提现金额（单位分）不指定金额则全额提现"));
    sb.properties("code", string("验证码"));
  });

  public static Schema TreeyeeCustomerRegistRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("TreeyeeCustomerRegistRequestVO");
    sb.properties("username", string);
    sb.properties("password", string);
    sb.properties("code", string);
    sb.properties("name", string("用户昵称，非必填"));
    sb.properties("companyId", int32("邀请企业id，非必填"));

  });

  public static Schema TreeyeeCustomerRegistResultVO = schema(sb -> {
    sb.type("object");
    sb.title("TreeyeeCustomerRegistResultVO");

    sb.properties("success", bool);
    sb.properties("codeMessage", string);
    sb.properties("usernameMessage", string);
    sb.properties("passwordMessage", string);
    sb.properties("token", string);

  });

  public static Schema ResultDTOTreeyeeCustomerRegistResultVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(TreeyeeCustomerRegistResultVO.getTitle()))));


  public static Schema RecruitVO = schema(sb -> {
    sb.type("object");
    sb.title("RecruitVO");
    sb.properties("id", int32("id"));
    sb.properties("title", string("标题"));
    sb.properties("quantity", int32("数量"));
    sb.properties("endAt", dateTime("招募截止时间"));
    sb.properties("info", string("招募内容"));
    sb.properties("auditReason", string("审核原因"));
    sb.properties("status", int32("状态"));
    sb.properties("auditPassAt", dateTime("审核通过时间"));
    sb.properties("publishName", string("发布人名称"));
    sb.properties("createAt", dateTime("创建时间"));

  });

  public static Schema ResultDTORecruitVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(RecruitVO.getTitle()))));

  public static Schema RecruitConditionVO = schema(sb -> {
    sb.type("object");
    sb.title("RecruitConditionVO");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);

    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);

    sb.properties("publishName", string("发布申请人"));
    sb.properties("status", int32Array("状态"));
    sb.properties("userId", int32("发布人id"));
    sb.properties("companyId", int32("发布企业id"));
    sb.properties("topStatus", topStatusProperty);
  });

  public static Schema PageResultVORecruitVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(RecruitVO.getTitle()))));

  public static Schema ResultDTOPageResultVORecruitVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVORecruitVO.getTitle()))));

  public static Schema RecruitAuditVO = schema(sb -> {
    sb.type("object");
    sb.title("RecruitAuditVO");
    sb.properties("id", int32("id"));
    sb.properties("status", int32("状态"));
    sb.properties("auditReason", string("审核原因"));

  });

  public static Schema RecruitAuditRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("RecruitAuditRequestVO");
    sb.properties("audits", refArray(RecruitAuditVO.getTitle()));
  });

  public static Schema RecommendConditionVO = schema(sb -> {
    sb.type("object");
    sb.title("RecommendConditionVO");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);

    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);

    sb.properties("type", int32("0个人 1企业"));
    sb.properties("name", string("名称模糊查询"));
    sb.properties("phone", string("联系方式模糊查询"));
    sb.properties("weixinNickname", string("微信昵称模糊查询"));

  });

  public static Schema RecommendVO = schema(sb -> {
    sb.type("object");
    sb.title("RecommendVO");
    sb.properties("id", int32("id"));
    sb.properties("shopId", int32("shopId"));
    sb.properties("top", bool);

    sb.properties("logo", string("logo"));
    sb.properties("name", string("个人姓名/企业名称"));
    sb.properties("workCity", string("可工作地区"));
    sb.properties("goodAt", string("擅长技能"));
    sb.properties("info", string("描述"));

    sb.properties("shopUserId", int32);
    sb.properties("shopCompanyId", int32);

    sb.properties("finishQuantity", int32("完成代理任务数量"));
    sb.properties("onlineFinishQuantity", int32("完成网络调查问卷数量"));
    sb.properties("phone", string("联系方式"));
    sb.properties("weixinNickname", string("微信昵称"));

    sb.properties("createAt", dateTime("创建时间"));
  });

  public static Schema PageResultVORecommendVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(RecommendVO.getTitle()))));

  public static Schema ResultDTOPageResultVORecommendVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVORecommendVO.getTitle()))));

  public static Schema ShopHasProxyJobSearchConditionVO = schema(sb -> {
    sb.type("object");
    sb.title("ShopHasProxyJobSearchConditionVO");
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("maxPage", maxPageProperty);

    sb.properties("limit", limitProperty);
    sb.properties("page", pageProperty);
    sb.properties("sort", sortProperty);
    sb.properties("offset", offsetProperty);

    sb.properties("shopId", int32);
    sb.properties("status", int32Array("状态"));

  });


  public static Schema ProxyJobVO = schema(sb -> {
    sb.type("object");
    sb.title("ProxyJobVO");
    sb.properties("id", int32);
    sb.properties("userId", int32);
    sb.properties("companyId", int32);
    sb.properties("version", int32);
    sb.properties("proxyJobId", int32);
    sb.properties("status", int32("状态"));
    sb.properties("createAt", pushAtProperty);
    sb.properties("updateAt", updateAtProperty);

    sb.properties("name", string("任务名称"));
    sb.properties("price", doubleNumer);
    sb.properties("describe", string("说明"));

  });

  public static Schema PageResultVOProxyJobVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ProxyJobVO.getTitle()))));

  public static Schema ResultDTOPageResultVOProxyJobVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOProxyJobVO.getTitle()))));


  public static Schema OnlineSurveyRecordConditionVO = schema(sb -> {
    sb.type("object");
    sb.title("OnlineSurveyRecordConditionVO");

    pageCondProperties(sb);

    sb.properties("name", string);
    sb.properties("publishName", string);
  });
}
