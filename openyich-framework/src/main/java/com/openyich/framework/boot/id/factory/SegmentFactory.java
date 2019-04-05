package com.openyich.framework.boot.id.factory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.openyich.framework.boot.id.domain.SegmentEntity;
import com.openyich.framework.boot.id.factory.support.Segment;
import com.openyich.framework.boot.id.factory.support.SegmentAware;
import com.openyich.framework.boot.id.factory.support.SegmentPostProccessor;
import com.openyich.framework.boot.id.repository.SegmentRepository;
import com.openyich.framework.boot.id.service.SegmentID;

/**
 * 基于美团 Leaf-segment 数据库方案的实现工厂
 */
public class SegmentFactory implements SegmentID {

  private static Logger logger = LoggerFactory.getLogger(SegmentFactory.class);
  private static final String REGEXP = "^[a-zA-Z0-9_-]{3,64}$";
  private static ReentrantLock lock = new ReentrantLock();
  private static ConcurrentMap<String, SegmentPostProccessor> bizTagMap =
      new ConcurrentHashMap<>();
  private SegmentRepository repository;
  private boolean asynLoading;

  public SegmentFactory(SegmentRepository repository, boolean asynLoading) {
    this.repository = repository;
    this.asynLoading = asynLoading;
  }

  private void check(String bizTag) {
    if (!Pattern.matches(REGEXP, bizTag)) {
      throw new IllegalArgumentException("Unsupported format: bizTag=" + bizTag);
    }
  }

  @Override
  public Long getId(String bizTag) {
    this.check(bizTag);

    if (bizTagMap.containsKey(bizTag)) {
      return bizTagMap.get(bizTag).getId();
    }

    try {
      lock.lock();
      SegmentPostProccessor proccessor = new SegmentPostProccessor(new SegmentAware() {

        @Override
        public Segment loadAndUpdate() {
          if (!repository.existsById(bizTag)) {
            throw new IllegalArgumentException("bizTag[" + bizTag + "] doesn't exists.");
          }
          SegmentEntity entity = repository.findById(bizTag).get();
          final Segment currentSegment = new Segment(entity.getMaxId(), entity.getStep());
          final Long newMaxId = currentSegment.getMaxId() + currentSegment.getStep();
          entity.setMaxId(newMaxId);
          repository.save(entity);
          return new Segment(newMaxId, currentSegment.getStep());
        }
      }, asynLoading);
      bizTagMap.putIfAbsent(bizTag, proccessor);
    } finally {
      lock.unlock();
    }

    return bizTagMap.get(bizTag).getId();
  }

  @Override
  public Long getId(String bizTag, long userId, int dbSize) {
    Long prefixOrderId = this.getId(bizTag);
    int leftMoveBit = this.leftMoveBit(dbSize);
    Long lastOrderId = ((prefixOrderId << leftMoveBit) | (userId % dbSize)); // 加入userId基因
    return lastOrderId;
  }

  @Override
  public void init(String bizTag, long step) {
    this.init(bizTag, 1, step);
  }

  @Override
  public void init(String bizTag, long startId, long step) {
    this.init(bizTag, startId, step, null);
  }

  @Override
  public void init(String bizTag, long startId, long step, String desc) {
    Assert.isTrue(startId > 0, "StartId must be gt 0.");
    Assert.isTrue(startId > 0, "Step must be gt 0.");
    this.check(bizTag);

    if (!repository.existsById(bizTag)) {
      SegmentEntity entity = new SegmentEntity();
      entity.setBizTag(bizTag);
      entity.setDescription(desc);
      entity.setMaxId(startId);
      entity.setStep(step);
      repository.save(entity);
      logger.info("Initial bizTag success: {}", entity.toString());
    }
  }

  private int leftMoveBit(Integer dbSize) {
    return Integer.toBinaryString(dbSize).length() - 1;
  }

}
