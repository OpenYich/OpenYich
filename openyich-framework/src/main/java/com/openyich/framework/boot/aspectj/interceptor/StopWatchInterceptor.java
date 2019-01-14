package com.openyich.framework.boot.aspectj.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.google.common.base.Throwables;
import com.openyich.framework.boot.aspectj.AbstractAdviceAdapter;
import com.openyich.framework.boot.aspectj.AfterAdviceAdapter;
import com.openyich.framework.boot.aspectj.BeforeAdviceAdapter;
import com.openyich.framework.boot.utils.ThreadLocalUtils;

@Aspect
@Component
public class StopWatchInterceptor extends AbstractAdviceAdapter
    implements
      BeforeAdviceAdapter,
      AfterAdviceAdapter {

  private static final Logger log = LoggerFactory.getLogger(StopWatchInterceptor.class);
  private static final ThreadLocal<StopWatch> TL = new ThreadLocal<>();

  @Override
  @Pointcut("@annotation(com.openyich.framework.boot.aspectj.lang.StopWatch)")
  public void setPointcut() {}

  @Override
  @After("setPointcut()")
  public void after(JoinPoint joinPoint) {
    Method method = getMethod(joinPoint);
    String requestURI = getRequest().getRequestURI();
    try {
      com.openyich.framework.boot.aspectj.lang.StopWatch sw =
          method.getAnnotation(com.openyich.framework.boot.aspectj.lang.StopWatch.class);
      if (sw != null) {
        String value = sw.value();
        StopWatch stopWatch = TL.get();
        stopWatch.stop();
        log.info("{}[URI={}]: {}", value, requestURI, stopWatch.shortSummary());
      }
    } catch (Exception e) {
      log.warn("StopWatch after exception: URI={}, Cause={}", requestURI,
          Throwables.getStackTraceAsString(e));
    }
  }

  @Override
  @Before("setPointcut()")
  public void before(JoinPoint joinPoint) {
    Method method = getMethod(joinPoint);
    String requestURI = getRequest().getRequestURI();
    try {
      com.openyich.framework.boot.aspectj.lang.StopWatch sw =
          method.getAnnotation(com.openyich.framework.boot.aspectj.lang.StopWatch.class);
      if (sw != null) {
        String id = ThreadLocalUtils.get().getRequestId();
        StopWatch stopWatch = new StopWatch(id);
        stopWatch.start();
        TL.set(stopWatch);
      }
    } catch (IllegalStateException e) {
      log.warn("StopWatch before exception: URI={}, Cause={}", requestURI,
          Throwables.getStackTraceAsString(e));
    }
  }

}
