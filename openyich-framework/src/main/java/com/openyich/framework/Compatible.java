package com.openyich.framework;

/**
 * 兼容性处理方案接口
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-08
 * 
 * @param <T>
 */
public interface Compatible<T> {

  T first();

  T second();

}
