package com.openyich.framework.boot;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.openyich.framework.autoconfigure.async.OpenYichAsyncTaskExecutor;

/**
 * 提供一系列静态方法
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-08
 */
@Component
public class OpenYich {

  private static final Logger log = LoggerFactory.getLogger(OpenYich.class);

  private OpenYichAsyncTaskExecutor executor;

  public OpenYich(OpenYichAsyncTaskExecutor executor) {
    this.executor = executor;
  }

  /**
   * 执行容错处理方案，保障服务的高可用性。
   * 
   * @param compatible
   * @return <T> T
   */
  public <T> Optional<T> execute(Compatible<T> compatible) {
    try {
      return Optional.of(compatible.run());
    } catch (Exception e) {
      log.warn("execute Compatible.run() error", e);
      try {
        return Optional.ofNullable(compatible.failover());
      } catch (Exception ex) {
        log.error("execute Compatible.failover() error", ex);
        return Optional.empty();
      }
    }
  }

  /**
   * 执行一个异步线程
   * 
   * @param task 异步任务
   */
  public void execute(Runnable task) {
    executor.execute(task);
  }

  /**
   * 执行一个异步线程
   * 
   * @param task 异步任务
   * @param startTimeout
   */
  public void execute(Runnable task, long startTimeout) {
    executor.execute(task, startTimeout);
  }

  /**
   * 提交一个异步线程，可返回异步结果。
   * 
   * @param task 异步任务
   * @return 异步结果
   */
  public Future<?> submit(Runnable task) {
    return executor.submit(task);
  }

  /**
   * 提交一个异步线程，可返回异步结果。
   * 
   * @param task 异步任务
   * @return 异步结果
   */
  public <T> Future<T> submit(Callable<T> task) {
    return executor.submit(task);
  }

}
