package com.openyich.framework.boot.hazelcast;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.openyich.framework.boot.autoconfigure.OpenYichConstants;

@Configuration
@ConditionalOnClass(HazelcastInstance.class)
@EnableConfigurationProperties(HazelcastProperties.class)
@EnableCaching(proxyTargetClass = true, mode = AdviceMode.ASPECTJ)
public class HazelcastCacheConfiguration extends HazelcastConfigurer {

  private final Logger log = LoggerFactory.getLogger(HazelcastCacheConfiguration.class);

  private final Environment env;

  public HazelcastCacheConfiguration(HazelcastProperties hazelcastProperties, Environment env) {
    super(hazelcastProperties);
    this.env = env;
  }

  @PreDestroy
  public void destroy() {
    log.info("Closing HazelcastCacheManager");
    Hazelcast.shutdownAll();
  }

  @Bean
  public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
    log.debug("Starting HazelcastCacheManager");
    return new HazelcastCacheManager(hazelcastInstance);
  }

  @Bean
  public HazelcastInstance hazelcastInstance() {
    log.debug("Configuring Hazelcast");
    return Hazelcast.getOrCreateHazelcastInstance(getConfig());
  }

  @Override
  public void customize(Config config) {
    // In development, remove multicast auto-configuration
    if (env.acceptsProfiles(Profiles.of(OpenYichConstants.SPRING_PROFILE_DEVELOPMENT))) {
      System.setProperty("hazelcast.local.localAddress", "127.0.0.1");

      config.getNetworkConfig().getJoin().getAwsConfig().setEnabled(false);
      config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
      config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);
    }
  }

}
