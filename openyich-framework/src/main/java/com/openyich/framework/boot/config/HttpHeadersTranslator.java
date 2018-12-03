package com.openyich.framework.boot.config;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.JSON;

@Configuration
@ConditionalOnWebApplication
public class HttpHeadersTranslator implements WebMvcConfigurer {
  
  private static final Logger log = LoggerFactory.getLogger(HttpHeadersTranslator.class);

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
        
        log.debug("httpHeaders: {}", JSON.toJSONString(httpHeaders));
        
        return true;
      }
    });
  }
  
}
