package com.openyich.framework.boot.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

/**
 * Customize the SchedulingConfigurer.
 */
public class OpenYichSchedulingConfigurer implements SchedulingConfigurer {

  private OpenYichProperties.Async properties;

  public OpenYichSchedulingConfigurer(OpenYichProperties openYichProperties) {
    this.properties = openYichProperties.getAsync();
  }

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setScheduler(scheduledTaskExecutor());
  }

  @Bean
  @Qualifier("scheduledTaskExecutor")
  public Executor scheduledTaskExecutor() {
    return Executors.newScheduledThreadPool(properties.getCorePoolSize());
  }

}
