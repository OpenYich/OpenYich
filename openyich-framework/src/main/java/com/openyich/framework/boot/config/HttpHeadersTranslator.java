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

import com.openyich.framework.boot.web.HttpHeadersConfigurer;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnBean(HttpHeadersConfigurer.class)
public class HttpHeadersTranslator implements WebMvcConfigurer {

  private HttpHeadersConfigurer httpHeadersConfigurer;

  public HttpHeadersTranslator(HttpHeadersConfigurer httpHeadersConfigurer) {
    this.httpHeadersConfigurer = httpHeadersConfigurer;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HandlerInterceptor() {

      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
          Object handler) throws Exception {
        Enumeration<String> parameterNames = request.getParameterNames();
        HttpHeaders httpHeaders = new HttpHeaders();

        while (parameterNames.hasMoreElements()) {
          String headerName = parameterNames.nextElement();
          String headerValue = request.getParameter(headerName);
          httpHeaders.add(headerName, headerValue);
        }

        httpHeadersConfigurer.handle(request, response, handler, httpHeaders);
        return true;
      }
    });
  }

}
