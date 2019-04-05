package com.openyich.framework.boot.id.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Leaf-snowflake Configuration Properties
 */
@ConfigurationProperties(prefix = "openyich.id.snowflake")
public class SnowflakeProperties {

  public enum Strategy {
    twitter, leaf;
  }

  /**
   * Strategy: Twitter, Leaf
   */
  private Strategy strategy = Strategy.leaf;

  /**
   * workerId: 0~31
   */
  private Integer workerId = 0;

  /**
   * dataCenterId: 0~31
   */
  private Integer dataCenterId = 0;

  public Integer getDataCenterId() {
    return dataCenterId;
  }

  public Strategy getStrategy() {
    return strategy;
  }

  public Integer getWorkerId() {
    return workerId;
  }

  public void setDataCenterId(Integer dataCenterId) {
    this.dataCenterId = dataCenterId;
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  public void setWorkerId(Integer workerId) {
    this.workerId = workerId;
  }

}
