package cloud.unionj.svc.client.java;

import cloud.unionj.svc.client.java.auth.HttpBearerAuth;
import okhttp3.Interceptor;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApiClientHelper {
  private static Map<String, ApiClient> apiClientPool = new LinkedHashMap<>();

  public static void newApiClient() {
    ApiClient apiClient = new ApiClient();
    apiClient.createDefaultAdapter();
    apiClientPool.put(null, apiClient);
  }

  public static void newApiClient(String name) {
    ApiClient apiClient = new ApiClient();
    apiClient.createDefaultAdapter();
    apiClientPool.put(name, apiClient);
  }

  public static <T> T createService(Class<T> cls) {
    return createService(null, cls);
  }

  public static <T> T createService(String name, Class<T> cls) {
    ApiClient apiClient = getApiClient(name);
    if (apiClient != null) {
      return apiClient.createService(cls);
    }
    return null;
  }

  public static void setBaseUrl(String baseUrl) {
    setBaseUrl(null, baseUrl);
  }

  public static void setBaseUrl(String name, String baseUrl) {
    ApiClient apiClient = getApiClient(name);
    if (apiClient != null) {
      apiClient.getAdapterBuilder().baseUrl(baseUrl).build();
    }
  }

  public static void addBearerAuth(boolean needToAddBearer) {
    addBearerAuth(null, needToAddBearer);
  }

  public static void addBearerAuth(String name, boolean needToAddBearer) {
    addAuth(name, "httpBearerAuth", new HttpBearerAuth(needToAddBearer ? "Bearer" : null));
  }

  public static void addAuth(String authName, Interceptor authorization) {
    ApiClient apiClient = getApiClient();
    if (apiClient != null) {
      if (apiClient.getApiAuthorizations() == null) {
        apiClient.setApiAuthorizations(new LinkedHashMap<>());
      }
      apiClient.addAuthorization(authName, authorization);
    }
  }

  public static void addAuth(String name, String authName, Interceptor authorization) {
    ApiClient apiClient = getApiClient(name);
    if (apiClient != null) {
      if (apiClient.getApiAuthorizations() == null) {
        apiClient.setApiAuthorizations(new LinkedHashMap<>());
      }
      apiClient.addAuthorization(authName, authorization);
    }
  }

  public static ApiClient getApiClient() {
    return getApiClient(null);
  }

  public static ApiClient getApiClient(String name) {
    return apiClientPool.get(name);
  }

}
