package com.openyich.framework.boot.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class OpenYichExecutor {

  private OpenYichAsyncTaskExecutor taskExecutor;
  private ScheduledExecutorService scheduledExecutor;

  public OpenYichExecutor(OpenYichAsyncTaskExecutor taskExecutor,
      ScheduledExecutorService scheduledExecutor) {
    this.taskExecutor = taskExecutor;
    this.scheduledExecutor = scheduledExecutor;
  }

  public void execute(Runnable task) {
    taskExecutor.execute(task);
  }

  public void execute(Runnable task, long startTimeout) {
    taskExecutor.execute(task, startTimeout);
  }

  public Future<?> submit(Runnable task) {
    return taskExecutor.submit(task);
  }

  public <T> Future<T> submit(Callable<T> task) {
    return taskExecutor.submit(task);
  }

  public ScheduledFuture<?> schedule(Runnable task, long delay, TimeUnit unit) {
    return scheduledExecutor.schedule(task, delay, unit);
  }

  public <T> ScheduledFuture<T> schedule(Callable<T> task, long delay, TimeUnit unit) {
    return scheduledExecutor.schedule(task, delay, unit);
  }

  public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initialDelay, long period,
      TimeUnit unit) {
    return scheduledExecutor.scheduleAtFixedRate(task, initialDelay, period, unit);
  }

  public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long initialDelay, long delay,
      TimeUnit unit) {
    return scheduledExecutor.scheduleWithFixedDelay(task, initialDelay, delay, unit);
  }

}
