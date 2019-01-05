package com.openyich.framework.boot.vo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;

import com.alibaba.fastjson.JSON;

public class ResponseVO<T> extends OpenYichObject {

  private static final long serialVersionUID = 1L;

  private String code;

  private String message;

  @Nullable
  private T data;
  
  private HttpHeaders headers;

  public String getCode() {
    return code;
  }

  public T getData() {
    return data;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }

  public String getMessage() {
    return message;
  }

  public boolean isSuccess() {
    return StringUtils.equals(code, "200");
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setHeaders(HttpHeaders headers) {
    this.headers = headers;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }

}
