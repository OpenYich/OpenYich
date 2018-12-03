package com.openyich.framework.boot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

public interface HttpHeadersConfigurer {

  void handle(HttpServletRequest request, HttpServletResponse response, Object handler,
      HttpHeaders httpHeaders);

}
