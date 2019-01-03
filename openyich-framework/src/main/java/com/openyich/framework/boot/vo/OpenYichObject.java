package com.openyich.framework.boot.vo;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Base class for Spring MVC Response types.
 */
public abstract class OpenYichObject implements Serializable {

  private static final long serialVersionUID = 1L;

  private Map<String, Object> extData = Maps.newConcurrentMap();

  public Map<String, Object> getExtData() {
    return extData;
  }

  public void setExtData(Map<String, Object> extData) {
    this.getExtData().putAll(extData);
  }

  protected void add(String key, Object value) {
    this.getExtData().put(key, value);
  }

}
