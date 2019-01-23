package com.openyich.framework.boot.aware;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

public interface ResponseBodyAware {

  default Object handle(Object body, ServerHttpRequest request, ServerHttpResponse response) {
    return body;
  }

  default String handleMessageConverter(String code, String message) {
    return message;
  }

}
