package com.openyich.framework.boot.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;

/**
 * Around adapter to be used in the Spring AOP framework.
 */
@Order(1)
public interface AroundAdviceAdapter {

  Object around(ProceedingJoinPoint joinPoint);

}
