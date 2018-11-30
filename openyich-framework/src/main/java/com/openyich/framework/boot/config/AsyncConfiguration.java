package com.openyich.framework.boot.config;

import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.openyich.framework.boot.async.OpenYichAsyncConfigurer;
import com.openyich.framework.boot.async.OpenYichAsyncTaskExecutor;
import com.openyich.framework.boot.async.OpenYichExecutor;
import com.openyich.framework.boot.async.OpenYichSchedulingConfigurer;
import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

/**
 * Async supports to OpenYich.
 */
@Configuration
@AutoConfigureAfter(OpenYichProperties.class)
@EnableAsync
@EnableScheduling
public class AsyncConfiguration {

  private OpenYichProperties openYichProperties;

  public AsyncConfiguration(OpenYichProperties openYichProperties) {
    this.openYichProperties = openYichProperties;
  }

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

  @Bean
  @ConditionalOnMissingBean
  public OpenYichExecutor openYichExecutor() {
    return new OpenYichExecutor(asyncTaskExecutor(),
        Executors.newScheduledThreadPool(openYichProperties.getAsync().getCorePoolSize()));
  }

}
