package com.openyich.framework.boot.aspectj;

import org.aspectj.lang.JoinPoint;
import org.springframework.core.annotation.Order;

/**
 * After throwing adapter to be used in the Spring AOP framework.
 */
@Order(2)
public interface AfterThrowingAdviceAdapter {

  void afterThrowing(JoinPoint joinPoint, Throwable throwable);

}
