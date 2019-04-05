package com.openyich.framework.boot.id.factory.support;

public interface SegmentAware {

  /**
   * 从数据库加载数据并更新
   * 
   * @return buffer 管理对象
   */
  Segment loadAndUpdate();

}
