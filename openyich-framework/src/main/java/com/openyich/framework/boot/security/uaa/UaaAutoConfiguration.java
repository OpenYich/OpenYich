package com.openyich.framework.boot.security.uaa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import com.openyich.framework.boot.config.OpenYichProperties;

@Configuration
@ConditionalOnClass({ClientCredentialsResourceDetails.class, LoadBalancerClient.class})
@ConditionalOnProperty("jhipster.security.client-authorization.client-id")
public class UaaAutoConfiguration {

  private OpenYichProperties openYichProperties;

  public UaaAutoConfiguration(OpenYichProperties openYichProperties) {
    this.openYichProperties = openYichProperties;
  }

  @Bean
  public LoadBalancedResourceDetails loadBalancedResourceDetails(
      LoadBalancerClient loadBalancerClient) {
    LoadBalancedResourceDetails loadBalancedResourceDetails =
        new LoadBalancedResourceDetails(loadBalancerClient);
    OpenYichProperties.Security.ClientAuthorization clientAuthorization =
        openYichProperties.getSecurity().getClientAuthorization();
    loadBalancedResourceDetails.setAccessTokenUri(clientAuthorization.getAccessTokenUri());
    loadBalancedResourceDetails.setTokenServiceId(clientAuthorization.getTokenServiceId());
    loadBalancedResourceDetails.setClientId(clientAuthorization.getClientId());
    loadBalancedResourceDetails.setClientSecret(clientAuthorization.getClientSecret());
    return loadBalancedResourceDetails;
  }
}
