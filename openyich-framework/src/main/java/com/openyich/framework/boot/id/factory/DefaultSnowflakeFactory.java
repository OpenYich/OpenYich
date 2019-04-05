package com.openyich.framework.boot.id.factory;

import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;

import com.openyich.framework.boot.id.service.SnowflakeID;

/**
 * 基于美团 Leaf-snowflake 方案的实现工厂
 */
public class DefaultSnowflakeFactory implements SnowflakeID {

  /** 开始时间戳 */
  private static final long EPOCH;

  /** 机器id所占的位数 */
  private static final long WORKER_ID_BITS = 10L;

  /** 支持的最大机器id，结果是1023 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
  private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);

  /** 序列在id中占的位数 */
  private static final long SEQUENCE_BITS = 12L;

  /** 机器ID向左移12位 */
  private static final long WORKER_ID_SHIFT_BITS = SEQUENCE_BITS;

  /** 时间截向左移22位(10+12) */
  private static final long TIMESTAMP_LEFT_SHIFT_BITS = SEQUENCE_BITS + WORKER_ID_BITS;

  /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
  private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

  /** 工作机器ID(0~1023) */
  private long workerId;

  /** 毫秒内序列(0~4095) */
  private long sequence = 0L;

  /** 上次生成ID的时间截 */
  private long lastTimestamp = -1L;

  private static ReentrantLock lock = new ReentrantLock();

  static {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2016, Calendar.NOVEMBER, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    EPOCH = calendar.getTimeInMillis();
  }

  /**
   * 构造函数
   * 
   * @param workerId 工作ID (0~1023)
   */
  public DefaultSnowflakeFactory(long workerId) {
    if (workerId > MAX_WORKER_ID || workerId < 0) {
      throw new IllegalArgumentException(
          String.format("WorkerId can't be greater than %d or less than 0", MAX_WORKER_ID));
    }
    this.workerId = workerId;
  }

  /**
   * 获得下一个ID (该方法是线程安全的)
   * 
   * @return SnowflakeId
   */
  @Override
  public Long getId() {
    try {
      lock.lock();
      long timestamp = timeGen();
      // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
      if (timestamp < lastTimestamp) {
        throw new RuntimeException(
            String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                lastTimestamp - timestamp));
      }
      // 如果是同一时间生成的，则进行毫秒内序列
      if (lastTimestamp == timestamp) {
        sequence = (sequence + 1) & SEQUENCE_MASK;
        // 毫秒内序列溢出
        if (sequence == 0) {
          // 阻塞到下一个毫秒，获得新的时间戳
          timestamp = tilNextMillis(lastTimestamp);
        }
      } else { // 时间戳改变，毫秒内序列重置
        sequence = 0L;
      }
      // 上次生成ID的时间截
      lastTimestamp = timestamp;
      // 移位并通过或运算拼到一起组成64位的ID
      return ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_SHIFT_BITS)
          | sequence;
    } finally {
      lock.unlock();
    }
  }

  /**
   * 阻塞到下一个毫秒，直到获得新的时间戳
   * 
   * @param lastTimestamp 上次生成ID的时间截
   * @return 当前时间戳
   */
  private long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  /**
   * 返回以毫秒为单位的当前时间
   * 
   * @return 当前时间(毫秒)
   */
  private long timeGen() {
    return System.currentTimeMillis();
  }

}
