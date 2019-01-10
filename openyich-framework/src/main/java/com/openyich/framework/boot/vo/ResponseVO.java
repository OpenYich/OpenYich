package com.openyich.framework.boot.vo;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * Base class for Spring MVC Response types.
 */
public class ResponseVO<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  private String message;

  @Nullable
  private T data;
  
  private HttpHeaders headers;
  
  private Map<String, Object> extData = Maps.newHashMap();

  public void add(String key, Object value) {
    this.getExtData().put(key, value);
  }

  public String getCode() {
    return code;
  }

  public T getData() {
    return data;
  }

  public Map<String, Object> getExtData() {
    return extData;
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

  public void setExtData(Map<String, Object> extData) {
    this.getExtData().putAll(extData);
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
