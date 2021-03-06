package cloud.unionj.svc.client.java.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ApiClient {
  /**
   * @return 指定ApiClient
   */
  String value() default "";
}
