package ${invokerPackage}.config;

import okhttp3.Interceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(
    prefix = "api-client.auth"
)
public class ApiClientAuthProperties {
  /**
   * auth拦截器名称，缺省以api-client.auth中的键作为名称
   */
  private String name;
  /**
   * 当前auth拦截器类，须实现okhttp3的Interceptor接口
   */
  private Class<? extends Interceptor> type;
  /**
   * 当前auth拦截器构造方法入参的参数类型列表
   */
  private Class[] constructorParamTypes;
  /**
   * 当前auth拦截器构造方法入参的参数值列表，若constructorParamTypes为空，则所有参数默认为String类型
   */
  private Object[] constructorParamValues;
  /**
   * 指定顺序，正浮点数，auth的顺序值一样时按照配置顺序从上到下排序
   */
  private Double order = 0.0;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Class<? extends Interceptor> getType() {
    return type;
  }

  public void setType(Class<? extends Interceptor> type) {
    this.type = type;
  }

  public Class[] getConstructorParamTypes() {
    return constructorParamTypes;
  }

  public void setConstructorParamTypes(Class[] constructorParamTypes) {
    this.constructorParamTypes = constructorParamTypes;
  }

  public Object[] getConstructorParamValues() {
    return constructorParamValues;
  }

  public void setConstructorParamValues(Object[] constructorParamValues) {
    this.constructorParamValues = constructorParamValues;
  }

  public Double getOrder() {
    return order;
  }

  public void setOrder(Double order) {
    if (order == null || order < 0) {
      this.order = 0.0;
    } else {
      this.order = order;
    }
  }
}
