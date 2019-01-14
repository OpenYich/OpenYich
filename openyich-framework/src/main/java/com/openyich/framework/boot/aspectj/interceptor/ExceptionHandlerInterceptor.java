package com.openyich.framework.boot.aspectj.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Throwables;
import com.openyich.framework.boot.aspectj.AbstractAdviceAdapter;
import com.openyich.framework.boot.aspectj.AfterThrowingAdviceAdapter;
import com.openyich.framework.boot.errors.OpenYichProblemException;
import com.openyich.framework.boot.utils.ThreadLocalUtils;

@Aspect
@Component
public class ExceptionHandlerInterceptor extends AbstractAdviceAdapter
    implements
      AfterThrowingAdviceAdapter {

  private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerInterceptor.class);

  @Override
  @AfterThrowing(value = "setPointcut()", throwing = "throwable")
  public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
    if (throwable instanceof OpenYichProblemException) {
      OpenYichProblemException e = (OpenYichProblemException) throwable;
      log.warn("Code={}, Cause={}", e.getCode(), Throwables.getStackTraceAsString(e));
      ThreadLocalUtils.get().setCode(e.getCode());
    }
  }

  @Override
  @Pointcut("@annotation(com.openyich.framework.boot.aspectj.lang.ExceptionHandler)")
  public void setPointcut() {

  }

}
