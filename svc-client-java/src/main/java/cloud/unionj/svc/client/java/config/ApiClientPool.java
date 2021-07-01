package ${invokerPackage}.config;

import ${invokerPackage}.ApiClient;
import okhttp3.Interceptor;
import retrofit2.Retrofit;

import java.util.*;

public class ApiClientPool extends LinkedHashMap<String, ApiClient> implements Map<String, ApiClient> {

  private ApiClientsProperties apiClientsProperties;

  public ApiClientPool(ApiClientsProperties apiClientsProperties) throws Exception {
    this.apiClientsProperties = apiClientsProperties;
    init();
  }

  private void init() throws Exception {
    if (apiClientsProperties == null || apiClientsProperties.getService() == null) {
      return;
    }
    Map<String, ApiClientProperties> apiClientMap = apiClientsProperties.getService();
    for (Entry<String, ApiClientProperties> apiClientEntry : apiClientMap.entrySet()) {
      ApiClientProperties apiClientProperties = apiClientEntry.getValue();

      ApiClient apiClient = new ApiClient();
      apiClient.createDefaultAdapter();

      Retrofit.Builder adapterBuilder = apiClient.getAdapterBuilder();

      String baseUrl = apiClientProperties.getBaseUrl();
      if (baseUrl != null && baseUrl.trim().length() > 0) {
        adapterBuilder.baseUrl(baseUrl);
      }

      Map<String, ApiClientAuthProperties> authMap = apiClientProperties.getAuth();
      if (authMap.size() > 0) {
        //按照配置的order升序排序
        List<Entry<String, ApiClientAuthProperties>> authList = new ArrayList<>(authMap.entrySet());
        Collections.sort(authList, Comparator.comparing(o -> o.getValue().getOrder()));

        Map<String, Interceptor> apiAuthorizations = apiClient.getApiAuthorizations();
        for (Entry<String, ApiClientAuthProperties> authEntry : authList) {
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

      putIfAbsent(apiClientEntry.getKey(), apiClient);
    }

  }

  public <T> T createService(Class<T> serviceClass) {
    for (Entry<String, ApiClient> entry : entrySet()) {
      if (entry != null && entry.getValue() != null) {
        return entry.getValue().createService(serviceClass);
      }
    }
    return null;
  }

  public <T> T createService(Class<T> serviceClass, String name) {
    if (name == null || name.trim().length() == 0) {
      return createService(serviceClass);
    }
    ApiClient apiClient = get(name);
    if (apiClient != null) {
      return apiClient.createService(serviceClass);
    }
    return null;
  }

}
