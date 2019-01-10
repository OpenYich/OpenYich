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

  private static final String EXCEPTION_MESSAGE = "Caught async exception";
  
  private OpenYichProperties.Async asyncProperties;

  public OpenYichAsyncConfigurer(OpenYichProperties openYichProperties) {
    this.asyncProperties = openYichProperties.getAsync();
  }

  public AsyncTaskExecutor create() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(asyncProperties.getCorePoolSize());
    executor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
    executor.setQueueCapacity(asyncProperties.getQueueCapacity());
    executor.setAllowCoreThreadTimeOut(asyncProperties.isAllowCoreThreadTimeOut());
    executor.setWaitForTasksToCompleteOnShutdown(asyncProperties.isWaitForJobsToCompleteOnShutdown());
    executor.setAwaitTerminationSeconds(asyncProperties.getAwaitTerminationSeconds());
    executor.setKeepAliveSeconds(asyncProperties.getKeepAliveSeconds());
    executor.setThreadNamePrefix(asyncProperties.getThreadNamePrefix());
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
    return (e, method, params) -> handle(e);
  }

  protected void handle(Throwable e) {
    log.error(EXCEPTION_MESSAGE, e);
  }

}
