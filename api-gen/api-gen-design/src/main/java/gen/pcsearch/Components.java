package gen.pcsearch;

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

  private static Schema sizeProperty = int32("每页条数，默认10，传-1查出全部数据");

  private static Schema currentProperty = int32("当前页，从1开始");

  private static Schema offsetProperty = int32("偏移量");

  private static Schema sortProperty = string("排序条件字符串：排序字段前使用'-'(降序)和'+'(升序)号表示排序规则，多个排序字段用','隔开",
      "+id,-create_at");

  private static Schema totalProperty = int64("总数，入参传入此参数则不再查询count，以此total为准");

  private static Schema publishAtProperty = dateTime("发布时间");

  private static Schema updateAtProperty = dateTime("更新时间");

  private static Schema endAtProperty = dateTime("截止时间");

  private static Schema joinEndAtProperty = dateTime("报名截止时间");

  public static Schema ResultDTO = schema(sb -> {
    sb.type("object");
    sb.title("ResultDTO");
    sb.properties("code", int32);
    sb.properties("msg", string);
    sb.properties("data", T);
  });

  public static Schema PageResult = schema(sb -> {
    sb.type("object");
    sb.title("PageResult");
    sb.dummy("com.treeyee.cloud.community.es.page.PageResult");
    sb.properties("items", ListT);
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("searchCount", bool);
    sb.properties("pages", int32("当前分页总页数"));
    sb.properties("offset", offsetProperty);
  });

  public static Schema FuzzySearchRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("FuzzySearchRequestVO");
    sb.required("size");
    sb.properties("jobID", string("任务ID"));
    sb.properties("keyword", string);
    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("sort", sortProperty);
  });

  public static Schema FuzzySearchResponseVO = schema(sb -> {
    sb.type("object");
    sb.title("FuzzySearchResponseVO");
    sb.description("搜任务模糊匹配结果下拉列表");
    sb.properties("id", string("任务ID"));
    sb.properties("name", string("任务名称"));
    sb.properties("type", enums("任务类型", new String[]{"网络调查", "面对面访问", "明察暗访", "友情分享"}));
  });

  public static Schema JobVO = schema(sb -> {
    sb.type("object");
    sb.description("全部任务VO");
    sb.title("JobVO");
    sb.properties("id", string("任务ID/问卷ID"));
    sb.properties("name", string("任务名称/问卷名称"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("submitAward", bool("答题有否【固定红包】"));
    sb.properties("shareAward", bool("分享有否红包"));
    sb.properties("price", doubleNumer("【固定红包】答题奖励/任务奖励"));
    sb.properties("sharePrice", doubleNumer("分享奖励"));
    sb.properties("randomAward", bool("是否有答题随机红包"));
    sb.properties("randomMinPrice", doubleNumer("随机红包最小值"));
    sb.properties("randomMaxPrice", doubleNumer("随机红包最大值"));
    sb.properties("joinStatus", enums("报名情况", new String[]{"立即答题", "已答题", "报名参加", "已报名", "查看报名", "已结束", "去看看", "已过期"}));
    sb.properties("status", enums("任务状态", new String[]{"待审核", "审批通过", "审批拒绝", "未过期", "已过期", "已结束", "已结算"}));
    sb.properties("link", string("问卷链接/任务链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
    sb.properties("surveyLinkOpenFlag", bool("答题链接开启状态"));

    sb.properties("updateAt", updateAtProperty);

    sb.properties("titleAmount", int32("题目数量"));
    sb.properties("auditNote", string("审批说明"));
    sb.properties("publisherId", int32("发布人调研工厂的账号id"));
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("describe", string("说明"));
    sb.properties("selectedQuantity", int32("选中店铺数量"));
    sb.properties("userQuantity", int32("报名数量"));
    sb.properties("workAreaNames", stringArray("可工作地区名称"));

    sb.properties("from", string("任务来源"));

    sb.properties("type", enums("任务类型", new String[]{"网络调查", "面对面访问", "明察暗访", "友情分享", "面对面访问招募访问员"}));
    sb.properties("deleteAt", dateTime("删除时间"));
    sb.properties("userID", int32("发布人任务广场ID"));
    sb.properties("companyID", int32("发布人单位任务广场ID"));
    sb.properties("joinButtonDisabled", bool("报名按钮是否禁用"));
    sb.properties("top", bool("是否置顶"));
  });

  public static Schema ResultDTOListFuzzySearchResponseVO = generic(gb -> gb.generic(ResultDTO, refArray(FuzzySearchResponseVO.getTitle())));

  public static Schema SearchJobStatResult = schema(sb -> {
    sb.type("object");
    sb.title("SearchJobStatResult");
    sb.properties("onlineSurveyTotal", int64("网络调查任务总数"));
    sb.properties("offlineSurveyTotal", int64("面访任务招募代理总数"));
    sb.properties("offlineSurveyVisitorTotal", int64("面访任务招募访问员总数"));
    sb.properties("jianchaTotal", int64("明察暗访任务总数"));
    sb.properties("externalTotal", int64("友情分享任务总数"));
  });

  public static Schema PageResultVOJobVO = generic(gb -> gb.generic(PageResult, reference(rb -> rb.ref(JobVO.getTitle()))));

  public static Schema SearchJobPageResult = schema(sb -> {
    sb.type("object");
    sb.title("SearchJobPageResult");
    sb.properties("page", ref(PageResultVOJobVO.getTitle()));
    sb.properties("stat", ref(SearchJobStatResult.getTitle()));
  });

  public static Schema ResultDTOPageResultVOJobVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SearchJobPageResult.getTitle()))));

  public static Schema PartyBSearchRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("PartyBSearchRequestVO");
    sb.properties("workArea", stringArray("可工作地区"));
    sb.properties("goodAt", stringArray("擅长技能"));
    sb.properties("keyword", string("企业名称/人才姓名"));

    sb.properties("total", totalProperty);
    sb.properties("size", sizeProperty);
    sb.properties("current", currentProperty);
    sb.properties("sort", sortProperty);
  });

  public static Schema OnlineSurveyJobVO = schema(sb -> {
    sb.type("object");
    sb.description("我参与的网调答卷数据");
    sb.title("OnlineSurveyJobVO");
    sb.properties("id", string("任务ID/问卷ID"));
    sb.properties("name", string("任务名称/问卷名称"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("submitAward", bool("答题有否【固定红包】"));
    sb.properties("shareAward", bool("分享有否红包"));
    sb.properties("price", doubleNumer("【固定红包】答题奖励/任务奖励"));
    sb.properties("sharePrice", doubleNumer("分享奖励"));
    sb.properties("randomAward", bool("是否有答题随机红包"));
    sb.properties("randomMinPrice", doubleNumer("随机红包最小值"));
    sb.properties("randomMaxPrice", doubleNumer("随机红包最大值"));

    sb.properties("joinStatus", enums("报名情况", new String[]{"立即答题", "已答题", "已结束"}));
    sb.properties("status", enums("任务状态", new String[]{"待审核", "审批通过", "审批拒绝", "已结束", "已结算"}));
    sb.properties("link", string("问卷链接/任务链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
    sb.properties("surveyLinkOpenFlag", bool("答题链接开启状态"));

    sb.properties("updateAt", updateAtProperty);

    sb.properties("auditNote", string("审批说明"));
    sb.properties("publisherId", int32("发布人调研工厂的账号id"));
    sb.properties("describe", string("说明"));

    sb.properties("type", enums("任务类型", new String[]{"网络调查"}));
    sb.properties("deleteAt", dateTime("删除时间"));
    sb.properties("userID", int32("发布人任务广场ID"));
    sb.properties("companyID", int32("发布人单位任务广场ID"));
    sb.properties("joinButtonDisabled", bool("报名按钮是否禁用"));
    sb.properties("top", bool("是否置顶"));
  });

  public static Schema OfflineSurveyJobVO = schema(sb -> {
    sb.type("object");
    sb.description("面对面访问招募代理");
    sb.title("OfflineSurveyJobVO");
    sb.properties("id", string("任务ID/问卷ID"));
    sb.properties("name", string("任务名称/问卷名称"));
    sb.properties("price", doubleNumer("任务报酬"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);

    sb.properties("joinStatus", enums("报名情况", new String[]{"报名参加", "已报名", "查看报名", "已结束"}));
    sb.properties("status", enums("任务状态", new String[]{"待审核", "审批通过", "审批拒绝", "已结束", "已结算"}));
    sb.properties("updateAt", updateAtProperty);
    sb.properties("titleAmount", int32("题目数量"));
    sb.properties("auditNote", string("审批说明"));
    sb.properties("publisherId", int32("发布人调研工厂的账号id"));
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("describe", string("说明"));
    sb.properties("selectedQuantity", int32("选中店铺数量"));
    sb.properties("userQuantity", int32("报名数量"));
    sb.properties("workAreaNames", stringArray("可工作地区名称"));

    sb.properties("type", enums("任务类型", new String[]{"面对面访问"}));
    sb.properties("deleteAt", dateTime("删除时间"));
    sb.properties("userID", int32("发布人任务广场ID"));
    sb.properties("companyID", int32("发布人单位任务广场ID"));
    sb.properties("joinButtonDisabled", bool("报名按钮是否禁用"));
    sb.properties("top", bool("是否置顶"));
  });

  public static Schema OfflineSurveyVisitorJobVO = schema(sb -> {
    sb.type("object");
    sb.description("面对面访问招募访问员");
    sb.title("OfflineSurveyVisitorJobVO");
    sb.properties("id", string("任务ID/问卷ID"));
    sb.properties("name", string("任务名称/问卷名称"));
    sb.properties("price", doubleNumer("任务报酬"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);

    sb.properties("joinStatus", enums("报名情况", new String[]{"报名参加", "已报名", "查看报名", "已结束"}));
    sb.properties("status", enums("任务状态", new String[]{"待审核", "审批通过", "审批拒绝", "已结束", "已结算"}));
    sb.properties("updateAt", updateAtProperty);
    sb.properties("titleAmount", int32("题目数量"));
    sb.properties("auditNote", string("审批说明"));
    sb.properties("publisherId", int32("发布人调研工厂的账号id"));
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("describe", string("说明"));
    sb.properties("selectedQuantity", int32("选中店铺数量"));
    sb.properties("userQuantity", int32("报名数量"));
    sb.properties("workAreaNames", stringArray("可工作地区名称"));

    sb.properties("type", enums("任务类型", new String[]{"面对面访问招募访问员"}));
    sb.properties("deleteAt", dateTime("删除时间"));
    sb.properties("userID", int32("发布人任务广场ID"));
    sb.properties("companyID", int32("发布人单位任务广场ID"));
    sb.properties("joinButtonDisabled", bool("报名按钮是否禁用"));
    sb.properties("top", bool("是否置顶"));
  });

  public static Schema JianchaJobVO = schema(sb -> {
    sb.type("object");
    sb.description("明察暗访");
    sb.title("JianchaJobVO");
    sb.properties("id", string("任务ID/问卷ID"));
    sb.properties("name", string("任务名称/问卷名称"));
    sb.properties("price", doubleNumer("任务报酬"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("joinStatus", enums("报名情况", new String[]{"报名参加", "已报名", "查看报名", "已结束"}));
    sb.properties("status", enums("任务状态", new String[]{"待审核", "审批通过", "审批拒绝", "已结束", "已结算"}));
    sb.properties("updateAt", updateAtProperty);
    sb.properties("auditNote", string("审批说明"));
    sb.properties("publisherId", int32("发布人调研工厂的账号id"));
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("describe", string("说明"));
    sb.properties("selectedQuantity", int32("选中店铺数量"));
    sb.properties("userQuantity", int32("报名数量"));
    sb.properties("workAreaNames", stringArray("可工作地区名称"));

    sb.properties("type", enums("任务类型", new String[]{"明察暗访"}));
    sb.properties("deleteAt", dateTime("删除时间"));
    sb.properties("userID", int32("发布人任务广场ID"));
    sb.properties("companyID", int32("发布人单位任务广场ID"));
    sb.properties("joinButtonDisabled", bool("报名按钮是否禁用"));
    sb.properties("top", bool("是否置顶"));
  });

  public static Schema ExternalJobVO = schema(sb -> {
    sb.type("object");
    sb.description("友情分享");
    sb.title("ExternalJobVO");
    sb.properties("id", string("任务ID/问卷ID"));
    sb.properties("name", string("友情分享任务名称"));
    sb.properties("from", string("任务来源"));
    sb.properties("createAt", publishAtProperty);
    sb.properties("endAt", endAtProperty);
    sb.properties("link", string("任务链接",
        "https://wj.treeyee.com/computer/index.html?surveyId=g8PizHDy&answerChannelId=343343818352885760"));
    sb.properties("describe", string("说明"));
    sb.properties("joinStatus", enums("报名情况", new String[]{"去看看", "已过期"}));
    sb.properties("status", enums("任务状态", new String[]{"未过期", "已过期"}));

    sb.properties("type", enums("任务类型", new String[]{"友情分享"}));
    sb.properties("deleteAt", dateTime("删除时间"));
    sb.properties("userID", int32("发布人任务广场ID"));
    sb.properties("companyID", int32("发布人单位任务广场ID"));
    sb.properties("joinButtonDisabled", bool("报名按钮是否禁用"));
  });

  public static Schema PageResultOnlineSurveyJobVO = generic(gb -> gb.generic(PageResult, reference(rb -> rb.ref(OnlineSurveyJobVO.getTitle()))));

  public static Schema SearchOnlineSurveyJobPageResult = schema(sb -> {
    sb.type("object");
    sb.title("SearchOnlineSurveyJobPageResult");
    sb.properties("page", ref(PageResultOnlineSurveyJobVO.getTitle()));
    sb.properties("stat", ref(SearchJobStatResult.getTitle()));
  });

  public static Schema ResultDTOPageResultOnlineSurveyJobVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SearchOnlineSurveyJobPageResult.getTitle()))));

  public static Schema PageResultOfflineSurveyJobVO = generic(gb -> gb.generic(PageResult, reference(rb -> rb.ref(OfflineSurveyJobVO.getTitle()))));

  public static Schema PageResultOfflineSurveyVisitorJobVO = generic(gb -> gb.generic(PageResult, reference(rb -> rb.ref(OfflineSurveyVisitorJobVO.getTitle()))));

  public static Schema SearchOfflineSurveyJobPageResult = schema(sb -> {
    sb.type("object");
    sb.title("SearchOfflineSurveyJobPageResult");
    sb.properties("page", ref(PageResultOfflineSurveyJobVO.getTitle()));
    sb.properties("stat", ref(SearchJobStatResult.getTitle()));
  });

  public static Schema SearchOfflineSurveyVisitorJobPageResult = schema(sb -> {
    sb.type("object");
    sb.title("SearchOfflineSurveyVisitorJobPageResult");
    sb.properties("page", ref(PageResultOfflineSurveyVisitorJobVO.getTitle()));
    sb.properties("stat", ref(SearchJobStatResult.getTitle()));
  });

  public static Schema ResultDTOPageResultOfflineSurveyJobVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SearchOfflineSurveyJobPageResult.getTitle()))));
  public static Schema ResultDTOPageResultOfflineSurveyVisitorJobVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SearchOfflineSurveyVisitorJobPageResult.getTitle()))));

  public static Schema PageResultJianchaJobVO = generic(gb -> gb.generic(PageResult, reference(rb -> rb.ref(JianchaJobVO.getTitle()))));

  public static Schema SearchJianchaJobPageResult = schema(sb -> {
    sb.type("object");
    sb.title("SearchJianchaJobPageResult");
    sb.properties("page", ref(PageResultJianchaJobVO.getTitle()));
    sb.properties("stat", ref(SearchJobStatResult.getTitle()));
  });

  public static Schema ResultDTOPageResultJianchaJobVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SearchJianchaJobPageResult.getTitle()))));

  public static Schema PageResultExternalJobVO = generic(gb -> gb.generic(PageResult, reference(rb -> rb.ref(ExternalJobVO.getTitle()))));

  public static Schema SearchExternalJobPageResult = schema(sb -> {
    sb.type("object");
    sb.title("SearchExternalJobPageResult");
    sb.properties("page", ref(PageResultExternalJobVO.getTitle()));
    sb.properties("stat", ref(SearchJobStatResult.getTitle()));
  });

  public static Schema ResultDTOPageResultExternalJobVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(SearchExternalJobPageResult.getTitle()))));

  public static Schema PartyBVO = schema(sb -> {
    sb.type("object");
    sb.description("乙方：企业或个人");
    sb.title("PartyBVO");
    sb.properties("id", int32);
    sb.properties("logo", string("乙方logo"));
    sb.properties("name", string("乙方名称"));
    sb.properties("goodAt", stringArray("乙方擅长"));
    sb.properties("workCity", stringArray("乙方可工作地区"));
    sb.properties("info", string("乙方描述"));
    sb.properties("userID", int32("乙方个人ID或者企业管理员ID"));
    sb.properties("companyID", int32("乙方企业ID"));
    sb.properties("deleteAt", dateTime("删除时间"));
  });

  public static Schema PageResultPartyBVO = generic(gb -> gb.generic(PageResult, reference(rb -> rb.ref(PartyBVO.getTitle()))));

  public static Schema ResultDTOPageResultPartyBVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultPartyBVO.getTitle()))));

  public static Schema ContactMeRequestVO = schema(sb -> {
    sb.type("object");
    sb.title("ContactMeRequestVO");
    sb.properties("to", int32("调研工厂用户ID"));
    sb.properties("content", string("消息内容"));
  });

  public static Schema ResultDTOString = generic(gb -> gb.generic(ResultDTO, string));

}
