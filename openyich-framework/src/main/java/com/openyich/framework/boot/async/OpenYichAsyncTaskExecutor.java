package com.openyich.framework.boot.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.AsyncTaskExecutor;

/**
 * Customize the AsyncTaskExecutor.
 */
public class OpenYichAsyncTaskExecutor
    implements
      AsyncTaskExecutor,
      InitializingBean,
      DisposableBean {

  private static final Logger log = LoggerFactory.getLogger(OpenYichAsyncTaskExecutor.class);

  public static final String EXCEPTION_MESSAGE = "Caught async exception";

  private final AsyncTaskExecutor taskExecutor;

  public OpenYichAsyncTaskExecutor(AsyncTaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  @Override
  public void execute(Runnable task) {
    taskExecutor.execute(createWrappedRunnable(task));
  }

  @Override
  public void execute(Runnable task, long startTimeout) {
    taskExecutor.execute(createWrappedRunnable(task), startTimeout);
  }

  @Override
  public Future<?> submit(Runnable task) {
    return taskExecutor.submit(createWrappedRunnable(task));
  }

  @Override
  public <T> Future<T> submit(Callable<T> task) {
    return taskExecutor.submit(createCallable(task));
  }

  @Override
  public void destroy() throws Exception {
    if (taskExecutor instanceof DisposableBean) {
      DisposableBean bean = (DisposableBean) taskExecutor;
      bean.destroy();
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if (taskExecutor instanceof InitializingBean) {
      InitializingBean bean = (InitializingBean) taskExecutor;
      bean.afterPropertiesSet();
    }
  }

  private <T> Callable<T> createCallable(final Callable<T> task) {
    return () -> {
      try {
        return task.call();
      } catch (Exception e) {
        handle(e);
        throw e;
      }
    };
  }

  private Runnable createWrappedRunnable(final Runnable task) {
    return () -> {
      try {
        task.run();
      } catch (Exception e) {
        handle(e);
      }
    };
  }

  protected void handle(Throwable e) {
    log.error(EXCEPTION_MESSAGE, e);
  }

}
