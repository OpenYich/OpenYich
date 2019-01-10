package com.openyich.framework.boot.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

public class OpenYichPropertiesTest {

  private OpenYichProperties properties;

  @Before
  public void setup() {
    properties = new OpenYichProperties();
  }

  @Test
  public void testComplete() throws Exception {
    // Slightly pedantic; this checks if there are tests for each of the properties.
    Set<String> set = new LinkedHashSet<>(64, 1F);
    reflect(properties, set, "test");
    for (String name : set) {
      this.getClass().getDeclaredMethod(name);
    }
  }

  private void reflect(Object obj, Set<String> dst, String prefix) throws Exception {
    Class<?> src = obj.getClass();
    for (Method method : src.getDeclaredMethods()) {
      String name = method.getName();
      if (name.startsWith("get")) {
        Object res = method.invoke(obj, (Object[]) null);
        if (res != null && src.equals(res.getClass().getDeclaringClass())) {
          reflect(res, dst, prefix + name.substring(3));
        }
      } else if (name.startsWith("set")) {
        dst.add(prefix + name.substring(3));
      }
    }
  }

  @Test
  public void testAsyncCorePoolSize() {
    OpenYichProperties.Async obj = properties.getAsync();
    int val = 8;
    assertThat(obj.getCorePoolSize()).isEqualTo(val);
    val++;
    obj.setCorePoolSize(val);
    assertThat(obj.getCorePoolSize()).isEqualTo(val);
  }

  @Test
  public void testAsyncMaxPoolSize() {
    OpenYichProperties.Async obj = properties.getAsync();
    int val = 50;
    assertThat(obj.getMaxPoolSize()).isEqualTo(val);
    val++;
    obj.setMaxPoolSize(val);
    assertThat(obj.getMaxPoolSize()).isEqualTo(val);
  }

  @Test
  public void testAsyncQueueCapacity() {
    OpenYichProperties.Async obj = properties.getAsync();
    int val = 10000;
    assertThat(obj.getQueueCapacity()).isEqualTo(val);
    val++;
    obj.setQueueCapacity(val);
    assertThat(obj.getQueueCapacity()).isEqualTo(val);
  }

  @Test
  public void testHttpVersion() {
    OpenYichProperties.Http.Version[] versions = OpenYichProperties.Http.Version.values();
    OpenYichProperties.Http obj = properties.getHttp();
    String str = "V_1_1";
    OpenYichProperties.Http.Version val = OpenYichProperties.Http.Version.valueOf(str);
    assertThat(obj.getVersion()).isEqualTo(val);
    val = versions[(1 + val.ordinal()) % versions.length];
    obj.setVersion(val);
    assertThat(obj.getVersion()).isEqualTo(val);
  }

  @Test
  public void testHttpUseUndertowUserCipherSuitesOrder() {
    OpenYichProperties.Http obj = properties.getHttp();
    boolean val = true;
    assertThat(obj.isUseUndertowUserCipherSuitesOrder()).isEqualTo(val);
    val = !val;
    obj.setUseUndertowUserCipherSuitesOrder(val);
    assertThat(obj.isUseUndertowUserCipherSuitesOrder()).isEqualTo(val);
  }


  @Test
  public void testSecurityClientAuthorizationAccessTokenUri() {
    OpenYichProperties.Security.ClientAuthorization obj =
        properties.getSecurity().getClientAuthorization();
    String val = null;
    assertThat(obj.getAccessTokenUri()).isEqualTo(val);
    val = "1" + val;
    obj.setAccessTokenUri(val);
    assertThat(obj.getAccessTokenUri()).isEqualTo(val);
  }

  @Test
  public void testSecurityClientAuthorizationTokenServiceId() {
    OpenYichProperties.Security.ClientAuthorization obj =
        properties.getSecurity().getClientAuthorization();
    String val = null;
    assertThat(obj.getTokenServiceId()).isEqualTo(val);
    val = "1" + val;
    obj.setTokenServiceId(val);
    assertThat(obj.getTokenServiceId()).isEqualTo(val);
  }

  @Test
  public void testSecurityClientAuthorizationClientId() {
    OpenYichProperties.Security.ClientAuthorization obj =
        properties.getSecurity().getClientAuthorization();
    String val = null;
    assertThat(obj.getClientId()).isEqualTo(val);
    val = "1" + val;
    obj.setClientId(val);
    assertThat(obj.getClientId()).isEqualTo(val);
  }

  @Test
  public void testSecurityClientAuthorizationClientSecret() {
    OpenYichProperties.Security.ClientAuthorization obj =
        properties.getSecurity().getClientAuthorization();
    String val = null;
    assertThat(obj.getClientSecret()).isEqualTo(val);
    val = "1" + val;
    obj.setClientSecret(val);
    assertThat(obj.getClientSecret()).isEqualTo(val);
  }

  @Test
  public void testSecurityAuthenticationJwtSecret() {
    OpenYichProperties.Security.Authentication.Jwt obj =
        properties.getSecurity().getAuthentication().getJwt();
    String val = null;
    assertThat(obj.getSecret()).isEqualTo(val);
    val = "1" + val;
    obj.setSecret(val);
    assertThat(obj.getSecret()).isEqualTo(val);
  }

  @Test
  public void testSecurityAuthenticationJwtBase64Secret() {
    OpenYichProperties.Security.Authentication.Jwt obj =
        properties.getSecurity().getAuthentication().getJwt();
    String val = null;
    assertThat(obj.getSecret()).isEqualTo(val);
    val = "1" + val;
    obj.setBase64Secret(val);
    assertThat(obj.getBase64Secret()).isEqualTo(val);
  }

  @Test
  public void testSecurityAuthenticationJwtTokenValidityInSeconds() {
    OpenYichProperties.Security.Authentication.Jwt obj =
        properties.getSecurity().getAuthentication().getJwt();
    long val = 1800;
    assertThat(obj.getTokenValidityInSeconds()).isEqualTo(val);
    val++;
    obj.setTokenValidityInSeconds(val);
    assertThat(obj.getTokenValidityInSeconds()).isEqualTo(val);
  }

  @Test
  public void testSecurityAuthenticationJwtTokenValidityInSecondsForRememberMe() {
    OpenYichProperties.Security.Authentication.Jwt obj =
        properties.getSecurity().getAuthentication().getJwt();
    long val = 2592000;
    assertThat(obj.getTokenValidityInSecondsForRememberMe()).isEqualTo(val);
    val++;
    obj.setTokenValidityInSecondsForRememberMe(val);
    assertThat(obj.getTokenValidityInSecondsForRememberMe()).isEqualTo(val);
  }

  @Test
  public void testSecurityRememberMeKey() {
    OpenYichProperties.Security.RememberMe obj = properties.getSecurity().getRememberMe();
    String val = null;
    assertThat(obj.getKey()).isEqualTo(val);
    val = "1" + val;
    obj.setKey(val);
    assertThat(obj.getKey()).isEqualTo(val);
  }

  @Test
  public void testSwaggerTitle() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = "API Documentation";
    assertThat(obj.getTitle()).isEqualTo(val);
    val = "1" + val;
    obj.setTitle(val);
    assertThat(obj.getTitle()).isEqualTo(val);
  }

  @Test
  public void testSwaggerDescription() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = "Generated by Springfox Swagger.";
    assertThat(obj.getDescription()).isEqualTo(val);
    val = "1" + val;
    obj.setDescription(val);
    assertThat(obj.getDescription()).isEqualTo(val);
  }

  @Test
  public void testSwaggerVersion() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = "1.0";
    assertThat(obj.getVersion()).isEqualTo(val);
    val = "1" + val;
    obj.setVersion(val);
    assertThat(obj.getVersion()).isEqualTo(val);
  }

  @Test
  public void testSwaggerTermsOfServiceUrl() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = null;
    assertThat(obj.getTermsOfServiceUrl()).isEqualTo(val);
    val = "1" + val;
    obj.setTermsOfServiceUrl(val);
    assertThat(obj.getTermsOfServiceUrl()).isEqualTo(val);
  }

  @Test
  public void testSwaggerContactName() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = null;
    assertThat(obj.getContactName()).isEqualTo(val);
    val = "1" + val;
    obj.setContactName(val);
    assertThat(obj.getContactName()).isEqualTo(val);
  }

  @Test
  public void testSwaggerContactUrl() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = null;
    assertThat(obj.getContactUrl()).isEqualTo(val);
    val = "1" + val;
    obj.setContactUrl(val);
    assertThat(obj.getContactUrl()).isEqualTo(val);
  }

  @Test
  public void testSwaggerContactEmail() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = null;
    assertThat(obj.getContactEmail()).isEqualTo(val);
    val = "1" + val;
    obj.setContactEmail(val);
    assertThat(obj.getContactEmail()).isEqualTo(val);
  }

  @Test
  public void testSwaggerLicense() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = null;
    assertThat(obj.getLicense()).isEqualTo(val);
    val = "1" + val;
    obj.setLicense(val);
    assertThat(obj.getLicense()).isEqualTo(val);
  }

  @Test
  public void testSwaggerLicenseUrl() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = null;
    assertThat(obj.getLicenseUrl()).isEqualTo(val);
    val = "1" + val;
    obj.setLicenseUrl(val);
    assertThat(obj.getLicenseUrl()).isEqualTo(val);
  }

  @Test
  public void testSwaggerDefaultIncludePattern() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = "/api/.*";
    assertThat(obj.getDefaultIncludePattern()).isEqualTo(val);
    val = "1" + val;
    obj.setDefaultIncludePattern(val);
    assertThat(obj.getDefaultIncludePattern()).isEqualTo(val);
  }

  @Test
  public void testSwaggerHost() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    String val = null;
    assertThat(obj.getHost()).isEqualTo(val);
    val = "1" + val;
    obj.setHost(val);
    assertThat(obj.getHost()).isEqualTo(val);
  }

  @Test
  public void testSwaggerUseDefaultResponseMessages() {
    OpenYichProperties.Swagger obj = properties.getSwagger();
    boolean val = true;
    assertThat(obj.isUseDefaultResponseMessages()).isEqualTo(val);
    val = !val;
    obj.setUseDefaultResponseMessages(val);
    assertThat(obj.isUseDefaultResponseMessages()).isEqualTo(val);
  }

  @Test
  public void testGatewayAuthorizedMicroservicesEndpoints() {
    OpenYichProperties.Gateway obj = properties.getGateway();
    Map<String, List<String>> val = new LinkedHashMap<>();
    assertThat(obj.getAuthorizedMicroservicesEndpoints()).isEqualTo(val);
    val.put("1", null);
    obj.setAuthorizedMicroservicesEndpoints(val);
    assertThat(obj.getAuthorizedMicroservicesEndpoints()).isEqualTo(val);
  }

  @Test
  public void testGatewayRateLimitingEnabled() {
    OpenYichProperties.Gateway.RateLimiting obj = properties.getGateway().getRateLimiting();
    boolean val = false;
    assertThat(obj.isEnabled()).isEqualTo(val);
    val = !val;
    obj.setEnabled(val);
    assertThat(obj.isEnabled()).isEqualTo(val);
  }

  @Test
  public void testGatewayRateLimitingLimit() {
    OpenYichProperties.Gateway.RateLimiting obj = properties.getGateway().getRateLimiting();
    long val = 100_000L;
    assertThat(obj.getLimit()).isEqualTo(val);
    val++;
    obj.setLimit(val);
    assertThat(obj.getLimit()).isEqualTo(val);
  }

  @Test
  public void testGatewayRateLimitingDurationInSeconds() {
    OpenYichProperties.Gateway.RateLimiting obj = properties.getGateway().getRateLimiting();
    int val = 3_600;
    assertThat(obj.getDurationInSeconds()).isEqualTo(val);
    val++;
    obj.setDurationInSeconds(val);
    assertThat(obj.getDurationInSeconds()).isEqualTo(val);
  }

}
