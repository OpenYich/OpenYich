package com.openyich.framework.boot.result;

import java.io.Serializable;

public class ErrorResult implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  private String error;

  public ErrorResult() {
    super();
  }

  public ErrorResult(String code, String error) {
    this.code = code;
    this.error = error;
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

}
