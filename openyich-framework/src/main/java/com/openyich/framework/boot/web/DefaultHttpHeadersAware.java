package com.openyich.framework.boot.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.openyich.framework.boot.aware.HttpHeadersAware;
import com.openyich.framework.boot.utils.ThreadLocalUtils;
import com.openyich.framework.boot.utils.ThreadLocalUtils.RequestHeader;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnMissingBean(HttpHeadersAware.class)
public class DefaultHttpHeadersAware implements HttpHeadersAware {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      HttpHeaders httpHeaders) {
    RequestHeader requestHeader = new RequestHeader();
    requestHeader.setHttpHeaders(httpHeaders);
    requestHeader.setRequest(request);
    requestHeader.setResponse(response);
    requestHeader.setSessionId(request.getRequestedSessionId());
    requestHeader.setRequestId(UUID.randomUUID().toString());
    ThreadLocalUtils.set(requestHeader);
    return true;
  }

}
