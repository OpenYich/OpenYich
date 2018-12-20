package com.openyich.framework.boot.result;

import java.io.Serializable;
import java.util.Objects;

import com.google.common.collect.Maps;

public class Result implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  private String error;

  private Object result;

  public Result() {
    super();
  }

  public Result(String code, String error, Object result) {
    this.code = code;
    this.error = error;
    this.result = result;
  }

  public String getCode() {
    return code;
  }

  public String getError() {
    return error;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Object getResult() {
    return Objects.nonNull(result) ? result : Maps.newConcurrentMap();
  }

  public void setResult(Object result) {
    this.result = result;
  }

}
