package com.openyich.framework.boot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.openyich.framework.boot.aware.HttpHeadersAware;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnMissingBean(HttpHeadersAware.class)
public class DefaultHttpHeadersAware implements HttpHeadersAware {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      HttpHeaders httpHeaders) {
    return true;
  }

}
