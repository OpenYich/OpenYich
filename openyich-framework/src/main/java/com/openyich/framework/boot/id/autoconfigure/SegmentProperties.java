package com.openyich.framework.boot.id.autoconfigure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Leaf-segment Configuration Properties
 */
@ConfigurationProperties(prefix = "openyich.id.segment")
public class SegmentProperties {

  private boolean asynLoading = true;
  private Map<String, SegmentData> endpoints = new HashMap<>();

  public boolean getAsynLoading() {
    return asynLoading;
  }

  public void setAsynLoading(boolean asynLoading) {
    this.asynLoading = asynLoading;
  }

  public Map<String, SegmentData> getEndpoints() {
    return endpoints;
  }

  public void setEndpoints(Map<String, SegmentData> endpoints) {
    this.endpoints = endpoints;
  }

  public static class SegmentData {

    private String bizTag;
    private Long startId = 1L;
    private Long step = 200L;
    private String description = "No Description";

    public String getBizTag() {
      return bizTag;
    }

    public void setBizTag(String bizTag) {
      this.bizTag = bizTag;
    }

    public Long getStartId() {
      return startId;
    }

    public void setStartId(Long startId) {
      this.startId = startId;
    }

    public Long getStep() {
      return step;
    }

    public void setStep(Long step) {
      this.step = step;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
  }

}
