package com.openyich.framework.boot.aspectj;

import org.aspectj.lang.JoinPoint;
import org.springframework.core.annotation.Order;

/**
 * Before adapter to be used in the Spring AOP framework.
 */
@Order(1)
public interface BeforeAdviceAdapter {

  void before(JoinPoint joinPoint);

}
