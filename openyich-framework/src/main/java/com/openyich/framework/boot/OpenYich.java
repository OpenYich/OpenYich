package com.openyich.framework.boot;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供一系列静态方法
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-08
 */
public abstract class OpenYich {

  private static final Logger log = LoggerFactory.getLogger(OpenYich.class);

  /**
   * 执行兼容性处理方案，保障服务的高可用性。
   * 
   * @param compatible
   * @return <T> T
   */
  public static <T> Optional<T> execute(Compatible<T> compatible) {
    try {
      return Optional.of(compatible.first());
    } catch (Exception e) {
      log.warn("execute Compatible.first() error", e);
      try {
        return Optional.ofNullable(compatible.second());
      } catch (Exception ex) {
        log.error("execute Compatible.second() error", ex);
        return Optional.empty();
      }
    }
  }

}
