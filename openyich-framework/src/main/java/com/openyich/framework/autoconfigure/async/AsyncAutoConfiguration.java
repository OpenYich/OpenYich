package com.openyich.framework.autoconfigure.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.AdviceMode;
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
@EnableAsync(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
public class AsyncAutoConfiguration {

  @Autowired
  private OpenYichProperties properties;

  @Bean
  public OpenYichAsyncConfigurer openYichAsyncConfigurer() {
    return new OpenYichAsyncConfigurer(properties.getAsync());
  }

  @Bean
  public OpenYichAsyncTaskExecutor openYichAsyncTaskExecutor() {
    return new OpenYichAsyncTaskExecutor(openYichAsyncTaskExecutor());
  }

}
