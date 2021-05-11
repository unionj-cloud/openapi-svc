package gen.admin;

import cloud.unionj.generator.openapi3.model.Schema;

import static cloud.unionj.generator.openapi3.dsl.Generic.generic;
import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.Schema.schema;
import static cloud.unionj.generator.openapi3.dsl.SchemaHelper.*;
import static gen.Components.*;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: cloud.unionj.generator.openapi3.dsl.paths
 * @date:2020/12/19
 */
public class Components {

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

  public static Schema NewsVO = schema(sb -> {
    sb.type("object");
    sb.title("NewsVO");
    sb.properties("id", int32);
    sb.properties("title", string("资讯标题"));
    sb.properties("publishAt", publishAtProperty);
    sb.properties("content", string("正文（富文本）"));
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

  public static Schema PageResultVONewsVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(NewsVO.getTitle()))));

  public static Schema PageResultVOAdminOnlineSurveyRecordVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(AdminOnlineSurveyRecordVO.getTitle()))));

  public static Schema PageResultVOAdminOnlineSurveyAuditVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(AdminOnlineSurveyAuditVO.getTitle()))));

  public static Schema PageResultVOProxyJobOfflineSurveyVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ProxyJobOfflineSurveyDetailVO.getTitle()))));

  public static Schema PageResultVOProxyJobJianchaVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ProxyJobJianchaDetailVO.getTitle()))));

  public static Schema PageResultVOExternalVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(ExternalVO.getTitle()))));

  public static Schema ResultDTOInteger = generic(gb -> gb.generic(ResultDTO, int32));

  public static Schema ResultDTOVoid = generic(gb -> gb.generic(ResultDTO, schema(data -> data.type("object"))));

  public static Schema ResultDTOPageResultVONewsVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVONewsVO.getTitle()))));

  public static Schema ResultDTOPageResultVOAdminOnlineSurveyRecordVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOAdminOnlineSurveyRecordVO.getTitle()))));

  public static Schema ResultDTOPageResultVOAdminOnlineSurveyAuditVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOAdminOnlineSurveyAuditVO.getTitle()))));

  public static Schema ResultDTOPageResultVOProxyJobOfflineSurveyVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOProxyJobOfflineSurveyVO.getTitle()))));

  public static Schema ResultDTOPageResultVOProxyJobJianchaVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOProxyJobJianchaVO.getTitle()))));

  public static Schema ResultDTOPageResultVOExternalVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOExternalVO.getTitle()))));

  public static Schema ResultDTONewsVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(NewsVO.getTitle()))));

  public static Schema ResultDTOExternalVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(ExternalVO.getTitle()))));

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

  public static Schema OnlineSurveyRecordConditionVO = schema(sb -> {
    sb.type("object");
    sb.title("OnlineSurveyRecordConditionVO");

    pageCondProperties(sb);

    sb.properties("name", string);
    sb.properties("publishName", string);
  });

  public static Schema OfflineSurveyVisitorDetailVO = schema(sb -> {
    sb.type("object");
    sb.title("OfflineSurveyVisitorDetailVO");
    sb.properties("id", int64);
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

  public static Schema PageResultVOOfflineSurveyVisitorVO = generic(gb -> gb.generic(PageResultVO, reference(rb -> rb.ref(OfflineSurveyVisitorDetailVO.getTitle()))));

  public static Schema ResultDTOPageResultVOOfflineSurveyVisitorVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(PageResultVOOfflineSurveyVisitorVO.getTitle()))));

  public static Schema OfflineSurveyVisitorWorkAreaVO = schema(sb -> {
    sb.type("object");
    sb.description("可工作地区");
    sb.title("OfflineSurveyVisitorWorkAreaVO");
    sb.properties("serialNo", int32);
    sb.properties("id", int32);
    sb.properties("offlineSurveyVisitorId", int32);
    sb.properties("name", string);
    sb.properties("note", string);
    sb.properties("amount", int32("任务数量"));
    sb.properties("price", doubleNumer);
  });

  public static Schema AdminOfflineSurveyVisitorInfoVO = schema(sb -> {
    sb.type("object");
    sb.title("AdminOfflineSurveyVisitorInfoVO");
    sb.properties("id", int64);
    sb.properties("userId", int32("雇主id"));
    sb.properties("companyId", int32("雇主companyId"));
    sb.properties("publishName", string("个人/企业名称"));
    sb.properties("publishLogo", string("个人/企业头像"));
    sb.properties("isEnterprise", bool("是否企业"));
    sb.properties("isPublisher", bool("是否是发布者"));
    sb.properties("visitorListUrl", string("查看报名列表url"));
    sb.properties("auditNote", string);
    sb.properties("name", string);
    sb.properties("status", int32);
    sb.properties("version", int32);
    sb.properties("isRead", bool);
    sb.properties("titleAmount", int32("题目数量"));
    sb.properties("endAt", endAtProperty);
    sb.properties("joinEndAt", joinEndAtProperty);
    sb.properties("finishAt", int64("任务到达已结束状态时间"));
    sb.properties("hasUnselectedShop", bool("有店铺报名且雇主并未操作过报名店铺"));
    sb.properties("describe", string);
    sb.properties("settleSpec", string("结算标准"));
    sb.properties("qualitySpec", string("质控标准"));
    sb.properties("workArea", refArray("可工作地区", OfflineSurveyVisitorWorkAreaVO.getTitle()));
  });

  public static Schema ResultDTOOfflineSurveyVisitorInfoVO = generic(gb -> gb.generic(ResultDTO, reference(rb -> rb.ref(AdminOfflineSurveyVisitorInfoVO.getTitle()))));
}
