package ${invokerPackage}.config;

import ${invokerPackage}.ApiClient;
import okhttp3.Interceptor;
import retrofit2.Retrofit;

import java.util.*;

public class SimpleApiClientPool {

  private ApiClientProperties apiClientProperties;

  private ApiClient apiClient;

  public SimpleApiClientPool(ApiClientProperties apiClientProperties) throws Exception {
    this.apiClientProperties = apiClientProperties;
    init();
  }

  private void init() throws Exception {
    if (apiClientProperties == null) {
      return;
    }
    apiClient = new ApiClient();
    apiClient.createDefaultAdapter();

    Retrofit.Builder adapterBuilder = apiClient.getAdapterBuilder();

    String baseUrl = apiClientProperties.getBaseUrl();
    if (baseUrl != null && baseUrl.trim().length() > 0) {
      adapterBuilder.baseUrl(baseUrl);
    }

    Map<String, ApiClientAuthProperties> authMap = apiClientProperties.getAuth();
    if (authMap.size() > 0) {
      //按照配置的order升序排序
      List<Map.Entry<String, ApiClientAuthProperties>> authList = new ArrayList<>(authMap.entrySet());
      Collections.sort(authList, Comparator.comparing(o -> o.getValue().getOrder()));

      Map<String, Interceptor> apiAuthorizations = apiClient.getApiAuthorizations();
      for (Map.Entry<String, ApiClientAuthProperties> authEntry : authList) {
        ApiClientAuthProperties apiClientAuthProperties = authEntry.getValue();

        String key;
        String name = apiClientAuthProperties.getName();
        if (name == null || name.trim().length() == 0) {
          key = authEntry.getKey();
        } else {
          key = name;
        }

        Class<? extends Interceptor> type = apiClientAuthProperties.getType();
        Class[] constructorParamTypes = apiClientAuthProperties.getConstructorParamTypes();
        Object[] constructorParamValues = apiClientAuthProperties.getConstructorParamValues();
        Interceptor interceptor;
        interceptor = type.getConstructor(constructorParamTypes).newInstance(constructorParamValues);
        apiAuthorizations.put(key, interceptor);
      }
    }
    adapterBuilder.build();
  }

  public <T> T createService(Class<T> serviceClass) {
    if (apiClient != null) {
      return apiClient.createService(serviceClass);
    }
    return null;
  }

}
