package com.openyich.framework.boot.id.service;

/**
 * 生成短链接接口
 */
public interface UrlShortenerID {

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @return 返回生成短网址数组，选其一作为短网址（默认6位长度）
   */
  String[] create(String url);

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param length 指定生成短链接的长度，取值范围：1~8
   * @return 返回生成短网址数组，选其一作为短网址
   */
  String[] create(String url, int length);

  /**
   * 短链接生成算法
   *
   * @param url 实际网址
   * @param secret 和实际网址混合生成短网址的掩码
   * @param length 指定生成短链接的长度，取值范围：1~8
   * @return 返回生成短网址数组，选其一作为短网址
   */
  String[] create(String url, String secret, int length);

}
