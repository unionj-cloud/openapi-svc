package gen;

import cloud.unionj.generator.openapi3.expression.paths.ResponsesBuilder;

import static cloud.unionj.generator.openapi3.dsl.Reference.reference;
import static cloud.unionj.generator.openapi3.dsl.paths.Content.content;
import static cloud.unionj.generator.openapi3.dsl.paths.MediaType.mediaType;
import static cloud.unionj.generator.openapi3.dsl.paths.Response.response;
import static gen.Components.ResultDTOString;

/**
 * @author: created by wubin
 * @version: v0.1
 * @description: gen
 * @date:2020/12/28
 */
public class Common {

  public static void errorResponse(ResponsesBuilder rb) {
    rb.response403(response(rrb -> {
      rrb.description("Forbidden");
      rrb.content(content(cb -> {
        cb.applicationJson(mediaType(mb -> {
          mb.schema(reference(rrrb -> {
            rrrb.ref(ResultDTOString.getTitle());
          }));
        }));
      }));
    }));
    rb.response401(response(rrb -> {
      rrb.description("Unauthorized");
      rrb.content(content(cb -> {
        cb.applicationJson(mediaType(mb -> {
          mb.schema(reference(rrrb -> {
            rrrb.ref(ResultDTOString.getTitle());
          }));
        }));
      }));
    }));
  }

}
