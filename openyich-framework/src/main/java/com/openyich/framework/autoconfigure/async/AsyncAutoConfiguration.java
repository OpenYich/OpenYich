package com.openyich.framework.autoconfigure.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.openyich.framework.autoconfigure.OpenYichProperties;

/**
 * Async supports to OpenYich.
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-09
 */
@Configuration
@AutoConfigureAfter(OpenYichProperties.class)
@EnableAsync
public class AsyncAutoConfiguration {

  @Autowired
  private OpenYichProperties properties;

  @Bean
  @ConditionalOnMissingBean
  public OpenYichAsyncConfigurer asyncConfigurer() {
    return new OpenYichAsyncConfigurer(properties.getAsync());
  }

  @Bean
  @ConditionalOnMissingBean
  public OpenYichAsyncTaskExecutor asyncTaskExecutor() {
    return new OpenYichAsyncTaskExecutor(asyncConfigurer().create());
  }

}
