package com.openyich.framework.boot.json;

import java.io.Serializable;

import javax.validation.Valid;

import com.alibaba.fastjson.JSON;

/**
 * Base class for Spring MVC Request types.
 */
public class JSONRequest<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private T args;

  public T getArgs() {
    return args;
  }

  public void setArgs(T args) {
    this.args = args;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }

}
