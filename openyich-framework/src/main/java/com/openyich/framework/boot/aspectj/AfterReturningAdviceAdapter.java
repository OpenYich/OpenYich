package com.openyich.framework.boot.aspectj;

import org.aspectj.lang.JoinPoint;
import org.springframework.core.annotation.Order;

/**
 * After returning adapter to be used in the Spring AOP framework.
 */
@Order(1)
public interface AfterReturningAdviceAdapter<T> {

  void afterReturning(JoinPoint joinPoint, T result);

}
