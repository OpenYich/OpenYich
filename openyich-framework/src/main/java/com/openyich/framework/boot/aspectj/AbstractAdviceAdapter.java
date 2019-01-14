package com.openyich.framework.boot.aspectj;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Base class adapter to be used in the Spring AOP framework.
 */
public abstract class AbstractAdviceAdapter {

  public abstract void setPointcut();

  protected ServletRequestAttributes getRequestAttributes() {
    return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
  }

  protected HttpServletRequest getRequest() {
    return getRequestAttributes().getRequest();
  }

  protected HttpServletResponse getResponse() {
    return getRequestAttributes().getResponse();
  }

  protected HttpHeaders getHttpHeaders() {
    Enumeration<String> headerNames = getRequest().getHeaderNames();
    HttpHeaders httpHeaders = new HttpHeaders();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      String headerValue = getRequest().getHeader(headerName);
      httpHeaders.add(headerName, headerValue);
    }
    return httpHeaders;
  }

  protected Method getMethod(JoinPoint joinPoint) {
    return ((MethodSignature) joinPoint.getSignature()).getMethod();
  }

}
