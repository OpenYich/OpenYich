package com.openyich.framework.boot.web;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * Represents an HTTP response entity.
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-14
 */
public class ResponseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  private String message;

  private Object result = Maps.newConcurrentMap();

  private String sign;

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Object getResult() {
    return result;
  }

  public String getSign() {
    return sign;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }

}
