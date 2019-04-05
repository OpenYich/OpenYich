package com.openyich.framework.boot.id.factory.support;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * buffer 管理对象
 */
public class Segment implements InitializingBean {

  private Long minId;
  private Long maxId;
  private Long middleId;
  private Long step;

  public Segment(Long maxId, Long step) {
    Assert.notNull(maxId, "maxId must be not null.");
    Assert.notNull(step, "step must be not null.");
    this.maxId = maxId;
    this.step = step;
    try {
      afterPropertiesSet();
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Long getMaxId() {
    return maxId;
  }

  public Long getMiddleId() {
    return middleId;
  }

  public Long getMinId() {
    return minId;
  }

  public Long getStep() {
    return step;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    this.middleId = this.maxId - (long) Math.round(this.step / 2);
    this.minId = this.maxId - this.step;
  }

  @Override
  public String toString() {
    return "Segment [minId=" + minId + ", maxId=" + maxId + ", middleId=" + middleId + ", step="
        + step + "]";
  }

}
