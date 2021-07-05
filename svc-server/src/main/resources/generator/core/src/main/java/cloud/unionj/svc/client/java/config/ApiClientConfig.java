package cloud.unionj.svc.client.java.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiClientConfig {
  private final Logger log = LoggerFactory.getLogger(ApiClientConfig.class);

  @Bean
  public ApiClientsProperties apiClientsProperties() {
    return new ApiClientsProperties();
  }

  @Bean
  public ApiClientProperties apiClientProperties() {
    return new ApiClientProperties();
  }

  @Bean
  public ApiClientPool apiClientPool() {
    ApiClientsProperties apiClientsProperties = apiClientsProperties();
    try {
      if (apiClientsProperties != null && apiClientsProperties.isValid()) {
        return new ApiClientPool(apiClientsProperties);
      }
    } catch (Exception e) {
      log.error("初始化ApiClientPool失败", e);
    }
    return null;
  }

  @Bean
  public SimpleApiClientPool simpleApiClientPool() {
    ApiClientProperties apiClientProperties = apiClientProperties();
    try {
      if (apiClientProperties != null && apiClientProperties.isValid()) {
        return new SimpleApiClientPool(apiClientProperties);
      }
    } catch (Exception e) {
      log.error("初始化SimpleApiClientPool失败", e);
    }
    return null;
  }

  @Bean
  public ApiClientPostProcessor apiClientPostProcessor() {
    return new ApiClientPostProcessor(apiClientPool(), simpleApiClientPool());
  }
}
