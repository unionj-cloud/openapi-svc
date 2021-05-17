package gen;

import cloud.unionj.generator.openapi3.dsl.PathHelper;
import cloud.unionj.generator.openapi3.model.Openapi3;
import gen.ts.TsProto;

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
        ib.title("代码生成接口");
        ib.version("v0.0.1");
      });

      server(sb -> {
        sb.url("REPLACE WITH YOUR SERVER ADDRESS");
      });

      PathHelper.doImport(TsProto.class);
    });
    return openapi3;
  }

}
