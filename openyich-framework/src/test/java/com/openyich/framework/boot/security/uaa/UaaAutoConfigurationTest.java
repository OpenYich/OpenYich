package com.openyich.framework.boot.security.uaa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.openyich.framework.boot.config.OpenYichProperties;
import com.openyich.framework.boot.config.OpenYichProperties.Security.ClientAuthorization;

public class UaaAutoConfigurationTest {

  private static final String ACCESS_TOKEN_URI = "http://access.token.uri/";
  private static final String TOKEN_SERVICE_ID = "tokkie";
  private static final String CLIENT_ID = "abacadabra";
  private static final String CLIENT_SECRET = "hush";

  private OpenYichProperties properties;
  private ClientAuthorization authorization;
  private UaaAutoConfiguration config;

  @Before
  public void setup() {
    properties = new OpenYichProperties();
    authorization = properties.getSecurity().getClientAuthorization();
    authorization.setAccessTokenUri(ACCESS_TOKEN_URI);
    authorization.setTokenServiceId(TOKEN_SERVICE_ID);
    authorization.setClientId(CLIENT_ID);
    authorization.setClientSecret(CLIENT_SECRET);
    config = new UaaAutoConfiguration(properties);
  }

  @Test
  public void testLoadBalancedResourceDetails() {
    LoadBalancedResourceDetails details = config.loadBalancedResourceDetails(null);
    assertThat(details.getAccessTokenUri()).isEqualTo(authorization.getAccessTokenUri());
    assertThat(details.getTokenServiceId()).isEqualTo(authorization.getTokenServiceId());
    assertThat(details.getClientId()).isEqualTo(authorization.getClientId());
    assertThat(details.getClientSecret()).isEqualTo(authorization.getClientSecret());
  }
}
