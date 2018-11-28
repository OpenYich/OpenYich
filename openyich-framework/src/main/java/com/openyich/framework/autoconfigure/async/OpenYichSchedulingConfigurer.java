package com.openyich.framework.autoconfigure.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.openyich.framework.autoconfigure.OpenYichProperties;

/**
 * Customize the SchedulingConfigurer.
 * 
 * @author zhycn
 * @since 2.1.2 2018-11-28
 */
public class OpenYichSchedulingConfigurer implements SchedulingConfigurer {

  private OpenYichProperties openYichProperties;

  public OpenYichSchedulingConfigurer(OpenYichProperties openYichProperties) {
    this.openYichProperties = openYichProperties;
  }

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setScheduler(scheduledTaskExecutor());
  }

  @Bean
  public Executor scheduledTaskExecutor() {
    return Executors.newScheduledThreadPool(openYichProperties.getAsync().getCorePoolSize());
  }

}
