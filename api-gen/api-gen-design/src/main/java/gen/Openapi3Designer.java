package gen;

import cloud.unionj.generator.openapi3.dsl.PathHelper;
import cloud.unionj.generator.openapi3.model.Openapi3;
import gen.admin.AdminProto;
import gen.pcsearch.PcSearchProto;
import gen.proto.*;

import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;
import static cloud.unionj.generator.openapi3.dsl.info.Info.info;
import static cloud.unionj.generator.openapi3.dsl.servers.Server.server;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen
 * @date:2020/12/28
 */
public class Openapi3Designer {

  public static Openapi3 design() {
    Openapi3 openapi3 = openapi3(ob -> {
      info(ib -> {
        ib.title("调研工厂任务广场");
        ib.version("v3.0.0");
      });

      server(sb -> {
        sb.url("http://gateway.cac7d791610d6444899e9153f4f3a74d7.cn-beijing.alicontainer.com");
      });

      PathHelper.doImport(TreeyeeProto.class);
      PathHelper.doImport(ShopRecommendApplyProto.class);
      PathHelper.doImport(ShopProto.class);
      PathHelper.doImport(ProxyJobProto.class);
      PathHelper.doImport(OssProto.class);
      PathHelper.doImport(WxProto.class);
      PathHelper.doImport(ApiProto.class);
      PathHelper.doImport(HallProto.class);
      PathHelper.doImport(AdminProto.class);
      PathHelper.doImport(AdminRecommendProto.class);
      PathHelper.doImport(EnumProto.class);
      PathHelper.doImport(PcProto.class);
      PathHelper.doImport(WhoProto.class);
      PathHelper.doImport(AppProto.class);
      PathHelper.doImport(PcSearchProto.class);
      PathHelper.doImport(PcJobProto.class);
      PathHelper.doImport(PcRecommendProto.class);
      PathHelper.doImport(PcShopProto.class);
      PathHelper.doImport(PcJianchaProto.class);
      PathHelper.doImport(PcVisitorProto.class);
      PathHelper.doImport(CustomerIdentifyProto.class);
    });
    return openapi3;
  }

}
