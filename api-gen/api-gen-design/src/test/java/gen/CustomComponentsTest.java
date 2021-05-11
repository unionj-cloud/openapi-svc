package gen;

import cloud.unionj.generator.openapi3.dsl.SchemaHelper;
import cloud.unionj.generator.openapi3.model.Openapi3;
import org.junit.jupiter.api.Test;

import static cloud.unionj.generator.openapi3.dsl.Openapi3.openapi3;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen
 * @date:2020/12/26
 */
class CustomComponentsTest {

  @Test
  void importComponents() {
    Openapi3 openapi3 = openapi3(ob -> {
//      SchemaHelper.batchImport(ComponentsDesigner.class);
    });
    System.out.println(openapi3.getComponents().getSchemas().size());
  }
}
