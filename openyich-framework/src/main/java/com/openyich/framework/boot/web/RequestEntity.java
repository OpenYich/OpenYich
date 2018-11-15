package com.openyich.framework.boot.web;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * Represents an HTTP request entity.
 * 
 * @author zhycn
 * @since 2.1.1 2018-11-14
 */
public class RequestEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  private String appId;
  
  private String method;
  
  private String signType;
  
  private String sign;
  
  private String timestamp;
  
  private String version;
  
  private String logId;
  
  private Map<String, Object> params = Maps.newConcurrentMap();

  public String getAppId() {
    return appId;
  }

  public String getMethod() {
    return method;
  }

  public String getSignType() {
    return signType;
  }

  public String getSign() {
    return sign;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getVersion() {
    return version;
  }

  public String getLogId() {
    return logId;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setLogId(String logId) {
    this.logId = logId;
  }

  public void setParams(Map<String, Object> params) {
    if (params != null) {
      this.params = params;
    }
  }
  
  public void addParam(String key, String value) {
    this.params.put(key, value);
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
  
}
