package cloud.unionj.svc.client.java.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(
    prefix = "api-clients"
)
public class ApiClientsProperties {
  private Map<String, ApiClientProperties> service = new LinkedHashMap<>();

  public Map<String, ApiClientProperties> getService() {
    return service;
  }

  public void setService(Map<String, ApiClientProperties> service) {
    this.service = service;
  }

  public boolean isValid() {
    return service.size() > 0;
  }
}
