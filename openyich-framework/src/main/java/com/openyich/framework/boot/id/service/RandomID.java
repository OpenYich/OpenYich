package com.openyich.framework.boot.id.service;

/**
 * 随机生成字符串接口
 */
public interface RandomID {

  /**
   * 随机生成一个UUID字符串
   * 
   * @return UUID 字符串
   */
  String uuid();

  /**
   * 根据指定字符生成一个UUID字符串
   * 
   * @param name 指定字符
   * @return UUID 字符串
   */
  String uuid(String name);

  /**
   * 随机生成一个UUID字符串，并转换为大写字母
   * 
   * @return UUID 字符串
   */
  String uuidUpperCase();

  /**
   * 根据指定字符生成一个UUID字符串，并转换为大写字母
   * 
   * @param name 指定字符
   * @return UUID 字符串
   */
  String uuidUpperCase(String name);

  /**
   * 随机生成一个GUID字符串
   * 
   * @return GUID 字符串
   */
  String guid();

  /**
   * 根据指定字符生成一个GUID字符串
   * 
   * @param name 指定字符
   * @return GUID 字符串
   */
  String guid(String name);

  /**
   * 随机生成一个GUID字符串，并转换为大写字母
   * 
   * @return GUID 字符串
   */
  String guidUpperCase();

  /**
   * 根据指定字符生成一个GUID字符串，并转换为大写字母
   * 
   * @param name 指定字符
   * @return GUID 字符串
   */
  String guidUpperCase(String name);

  /**
   * 随机生成指定长度的字符串，由大小写字母（数字）组成
   * 
   * @param count 指定长度
   * @return 随机字符串
   */
  String random(int count);

  /**
   * 随机生成指定长度的字符串，由大小写字母（数字）组成
   * 
   * @param count 指定长度
   * @param onlyNumbers 是否为纯数字
   * @return 随机字符串
   */
  String random(int count, boolean onlyNumbers);

  /**
   * 自定义字符集生成指定长度的字符串
   * 
   * @param count 指定长度
   * @param chars 指定字符集
   * @return 随机字符串
   */
  String random(int count, char... chars);

  /**
   * 自定义字符集生成指定长度的字符串
   * 
   * @param count 指定长度
   * @param chars 指定字符集
   * @return 随机字符串
   */
  String random(int count, String chars);

}
