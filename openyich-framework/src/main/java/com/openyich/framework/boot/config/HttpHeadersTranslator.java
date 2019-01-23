package com.openyich.framework.boot.config;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.openyich.framework.boot.aware.RequestBodyAware;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnBean(RequestBodyAware.class)
public class HttpHeadersTranslator implements WebMvcConfigurer {

  private RequestBodyAware httpHeadersAware;

  public HttpHeadersTranslator(RequestBodyAware httpHeadersAware) {
    this.httpHeadersAware = httpHeadersAware;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HandlerInterceptor() {

      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
          Object handler) throws Exception {
        Enumeration<String> headerNames = request.getHeaderNames();
        HttpHeaders httpHeaders = new HttpHeaders();
        while (headerNames.hasMoreElements()) {
          String headerName = headerNames.nextElement();
          String headerValue = request.getHeader(headerName);
          httpHeaders.add(headerName, headerValue);
        }
        return httpHeadersAware.preHandle(request, response, handler, httpHeaders);
      }
    });
  }

}
