package com.openyich.framework.boot.id.annotations;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.openyich.framework.boot.id.autoconfigure.SegmentProperties;
import com.openyich.framework.boot.id.factory.SegmentFactory;
import com.openyich.framework.boot.id.repository.SegmentRepository;
import com.openyich.framework.boot.id.service.SegmentID;

/**
 * ID JPA Configuration
 */
@Configuration
@EnableConfigurationProperties(SegmentProperties.class)
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
@EnableJpaRepositories({"com.openyich.framework.boot.id.repository"})
@EntityScan("com.openyich.framework.boot.id.domain")
@EnableJpaAuditing
public class SegmentConfigurationSelector implements InitializingBean {

  @Autowired
  private SegmentProperties properties;

  @Autowired
  private SegmentRepository repository;

  @Bean
  @ConditionalOnMissingBean
  public SegmentID createSegmentFactory() {
    boolean asynLoading = properties.getAsynLoading();
    return new SegmentFactory(repository, asynLoading);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    properties.getEndpoints().forEach((k, v) -> {
      createSegmentFactory().init(v.getBizTag(), v.getStartId(), v.getStep(),
          v.getDescription());
    });
  }

}
