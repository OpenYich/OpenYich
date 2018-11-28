package com.openyich.framework.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.openyich.framework.boot.async.OpenYichAsyncConfigurer;
import com.openyich.framework.boot.async.OpenYichAsyncTaskExecutor;
import com.openyich.framework.boot.async.OpenYichSchedulingConfigurer;

/**
 * Async supports to OpenYich.
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-09
 */
@Configuration
@AutoConfigureAfter(OpenYichProperties.class)
@EnableAsync
@EnableScheduling
public class AsyncConfiguration {

  @Autowired
  private OpenYichProperties openYichProperties;

  @Bean
  @ConditionalOnMissingBean
  public OpenYichAsyncConfigurer asyncConfigurer() {
    return new OpenYichAsyncConfigurer(openYichProperties);
  }

  @Bean
  @ConditionalOnMissingBean
  public OpenYichAsyncTaskExecutor asyncTaskExecutor() {
    return new OpenYichAsyncTaskExecutor(asyncConfigurer().create());
  }

  @Bean
  @ConditionalOnMissingBean
  public OpenYichSchedulingConfigurer schedulingConfigurer() {
    return new OpenYichSchedulingConfigurer(openYichProperties);
  }

}
