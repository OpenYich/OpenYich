package com.openyich.framework.boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.openyich.framework.boot.aware.ResponseBodyAware;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnBean(ResponseBodyAware.class)
@ConditionalOnMissingBean(name = "problemResponseBodyAdvice")
@RestControllerAdvice
public class ProblemResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  private ResponseBodyAware responseBodyAware;

  private ProblemResponseBodyAdvice(ResponseBodyAware responseBodyAware) {
    this.responseBodyAware = responseBodyAware;
  }

  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {
    return responseBodyAware.convert(body);
  }

}
