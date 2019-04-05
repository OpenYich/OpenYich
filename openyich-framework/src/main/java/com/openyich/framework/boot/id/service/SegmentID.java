package com.openyich.framework.boot.id.service;

/**
 * 基于美团 Leaf-segment 数据库方案的实现接口：https://tech.meituan.com/MT_Leaf.html
 */
public interface SegmentID {

  /**
   * 初始化一个业务配置
   * 
   * @param bizTag 业务标签
   * @param step 步长
   */
  void init(String bizTag, long step);

  /**
   * 初始化一个业务配置
   * 
   * @param bizTag 业务标签
   * @param startId 起始值
   * @param step 步长
   */
  void init(String bizTag, long startId, long step);

  /**
   * 初始化一个业务配置
   * 
   * @param bizTag 业务标签
   * @param startId 起始值
   * @param step 步长
   * @param desc 业务描述信息
   */
  void init(String bizTag, long startId, long step, String desc);

  /**
   * 获取一个由 Leaf-segment 算法生成的ID
   * 
   * @param bizTag 业务标签
   * @return ID
   */
  Long getId(String bizTag);

  /**
   * 获取一个由 Leaf-segment 算法生成的ID。为了更好的支持分库分表的应用场景，增加了用户基因算法的实现。
   * 
   * @param bizTag 业务标签
   * @param userId 用户标识
   * @param dbSize 数据库大小
   * @return
   */
  Long getId(String bizTag, long userId, int dbSize);

}
