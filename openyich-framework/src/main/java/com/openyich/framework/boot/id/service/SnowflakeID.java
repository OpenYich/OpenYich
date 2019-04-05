package com.openyich.framework.boot.id.service;

/**
 * 基于Twitter-snowflake 方案的实现接口
 */
public interface SnowflakeID {

  /**
   * 获取一个由 Twitter-snowflake 算法生成的18位长度的ID
   * 
   * @return 18位长度的ID
   */
  Long getId();

}
