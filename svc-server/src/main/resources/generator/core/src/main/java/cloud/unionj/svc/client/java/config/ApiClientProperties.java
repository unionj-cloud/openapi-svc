package cloud.unionj.svc.client.java.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@ConfigurationProperties(
    prefix = "api-client"
)
public class ApiClientProperties {
  private String baseUrl;
  private Boolean enableAuth;
  private Map<String, ApiClientAuthProperties> auth = new LinkedHashMap<>();

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public Map<String, ApiClientAuthProperties> getAuth() {
    return auth;
  }

  public void setAuth(Map<String, ApiClientAuthProperties> auth) {
    this.auth = auth;
  }

  public Boolean getEnableAuth() {
    return enableAuth;
  }

  public void setEnableAuth(Boolean enableAuth) {
    this.enableAuth = enableAuth;
  }

  public boolean isValid() {
    return (baseUrl != null && baseUrl.trim().length() > 0)
        || enableAuth != null
        || auth.size() > 0;
  }
}
