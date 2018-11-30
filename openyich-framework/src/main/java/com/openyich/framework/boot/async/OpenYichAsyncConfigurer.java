package com.openyich.framework.boot.async;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

/**
 * Customize the AsyncConfigurer.
 */
public class OpenYichAsyncConfigurer implements AsyncConfigurer {

  private static final Logger log = LoggerFactory.getLogger(OpenYichAsyncConfigurer.class);

  private OpenYichProperties.Async properties;

  public OpenYichAsyncConfigurer(OpenYichProperties openYichProperties) {
    this.properties = openYichProperties.getAsync();
  }

  public AsyncTaskExecutor create() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(properties.getCorePoolSize());
    executor.setMaxPoolSize(properties.getMaxPoolSize());
    executor.setQueueCapacity(properties.getQueueCapacity());
    executor.setAllowCoreThreadTimeOut(properties.isAllowCoreThreadTimeOut());
    executor.setWaitForTasksToCompleteOnShutdown(properties.isAllowCoreThreadTimeOut());
    executor.setAwaitTerminationSeconds(properties.getAwaitTerminationSeconds());
    executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
    executor.setThreadNamePrefix(properties.getThreadNamePrefix());
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }

  @Override
  public Executor getAsyncExecutor() {
    return this.create();
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (ex, method, params) -> log.error("Caught async exception", ex);
  }

}
