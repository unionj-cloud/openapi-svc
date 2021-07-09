package cloud.unionj.svc.client.java;

import cloud.unionj.svc.client.java.auth.HttpBearerAuth;
import okhttp3.Interceptor;

import java.util.LinkedHashMap;

public class SimpleApiClientHelper {
  private static ApiClient apiClient;

  static {
    apiClient = new ApiClient();
    apiClient.createDefaultAdapter();
  }

  public static <T> T createService(Class<T> cls) {
    return apiClient.createService(cls);
  }

  public static void setBaseUrl(String baseUrl) {
    apiClient.getAdapterBuilder().baseUrl(baseUrl).build();
  }

  public static void addBearerAuth(boolean needToAddBearer) {
    addAuth("httpBearerAuth", new HttpBearerAuth(needToAddBearer ? "Bearer" : null));
  }

  public static void addAuth(String authName, Interceptor authorization) {
    if (apiClient.getApiAuthorizations() == null) {
      apiClient.setApiAuthorizations(new LinkedHashMap<>());
    }
    apiClient.addAuthorization(authName, authorization);
  }

}
