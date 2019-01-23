package com.openyich.framework.boot.aware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

public interface RequestBodyAware {

  default boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler, HttpHeaders httpHeaders) {
    return true;
  }

}
