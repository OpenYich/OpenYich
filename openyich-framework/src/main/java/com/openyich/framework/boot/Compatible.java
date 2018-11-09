package com.openyich.framework.boot;

/**
 * 容错处理保障接口
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-08
 * 
 * @param <T>
 */
public interface Compatible<T> {

  /**
   * 在容错处理机制中，会优先执行该方法。
   */
  T run();

  /**
   * 在容错处理机制中，如果优先执行的方法失败或异常，将执行失效备援的方法以最大限度的保障服务高可用性。
   */
  T failover();

}
