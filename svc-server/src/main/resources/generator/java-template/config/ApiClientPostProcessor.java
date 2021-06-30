package ${invokerPackage}.config;


import ${invokerPackage}.annotation.ApiClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ApiClientPostProcessor implements BeanPostProcessor {


  @Autowired(required = false)
  private ApiClientPool apiClientPool;

  @Autowired(required = false)
  private SimpleApiClientPool simpleApiClientPool;

  //bean初始化之前要调用的方法，这里直接返回
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  //bean初始化之后要调用的方法，这里判断如果有自定义注解@ApiClient时，做进一步处理
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (simpleApiClientPool == null && (apiClientPool == null || apiClientPool.isEmpty())) {
      return bean;
    }
    Class<?> targetClass = bean.getClass();
    Field[] fields = targetClass.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(ApiClient.class)) {  //判断属性是否是自定义注解@MyAnnotation
        if (!field.getType().isInterface()) {  //加自定义注解的属性必须是接口类型（这样才可能出现多个不同的实例bean)
          throw new BeanCreationException("@ApiClient field must be declared an interface");
        } else {
          try {
            //为属性赋值
            this.handleApiClient(field, bean);
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return bean;
  }

  private void handleApiClient(Field field, Object bean) throws IllegalAccessException {
    //设置该域可设置修改
    field.setAccessible(true);
    //将找到的实例赋值给属性域
    field.set(bean,
        simpleApiClientPool != null
            ? simpleApiClientPool.createService(field.getType())
            : apiClientPool.createService(field.getType(),  field.getAnnotation(ApiClient.class).value()));
  }
}
