package com.openyich.framework.boot.aware;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

public interface ResponseBodyAware {

  Object handle(Object body, ServerHttpRequest request, ServerHttpResponse response);

}
