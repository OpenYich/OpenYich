package com.openyich.framework.boot.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

public class ThreadLocalUtils {

  private static final ThreadLocal<RequestHeader> TL = new ThreadLocal<>();

  public static RequestHeader get() {
    return TL.get();
  }

  public static void set(RequestHeader requestHeader) {
    TL.set(requestHeader);
  }

  public static class RequestHeader {

    private String code;

    private String requestId;

    private String sessionId;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpHeaders httpHeaders;

    public String getCode() {
      return code;
    }

    public String getRequestId() {
      return requestId;
    }

    public String getSessionId() {
      return sessionId;
    }

    public HttpServletRequest getRequest() {
      return request;
    }

    public HttpServletResponse getResponse() {
      return response;
    }

    public HttpHeaders getHttpHeaders() {
      return httpHeaders;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public void setRequestId(String requestId) {
      this.requestId = requestId;
    }

    public void setSessionId(String sessionId) {
      this.sessionId = sessionId;
    }

    public void setRequest(HttpServletRequest request) {
      this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
      this.response = response;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
      this.httpHeaders = httpHeaders;
    }
    
    public boolean hasCode() {
      return StringUtils.hasText(code);
    }

  }
}
