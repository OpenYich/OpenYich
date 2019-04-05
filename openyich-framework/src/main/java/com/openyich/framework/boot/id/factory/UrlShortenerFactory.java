package com.openyich.framework.boot.id.factory;

import org.springframework.util.DigestUtils;

import com.openyich.framework.boot.id.service.RandomID;
import com.openyich.framework.boot.id.service.UrlShortenerID;

/**
 * 生成短链接实现工厂
 */
public class UrlShortenerFactory implements UrlShortenerID {

  /**
   * 默认用于生成短链的字符串
   */
  private static String ALPHABET =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static RandomID randomID = new RandomFactory();

  @Override
  public String[] create(String url) {
    return create(url, 6);
  }

  @Override
  public String[] create(String url, int length) {
    return create(url, randomID.random(8), length);
  }

  @Override
  public String[] create(String url, String secret, int length) {
    return create(url, secret, length, ALPHABET.toCharArray());
  }

  private String[] create(String url, String secret, int length, char[] chars) {
    if (length > 8 || length < 1) {
      throw new IllegalArgumentException(String.format("Length must be between %d and %d.", 1, 8));
    }

    // 1. 通过MD5混合加密实际网址
    String md5EncryptResult = DigestUtils.md5DigestAsHex((secret + url).getBytes());
    final int factors = length;
    if (length < 1 || length > 6) {
      length = 6;
    }

    // 2. 定义返回结果数组，经过换算后会生成4组短链接字符串
    String[] result = new String[4];
    for (int i = 0; i < result.length; i++) {
      // 把加密字符按照8位一组16进制与 0x3FFFFFFF进行位与运算
      String substring = md5EncryptResult.substring(i * 8, i * 8 + 8);
      // 转换为Long类型（Integer可能会因长度不够而出错）
      long hex = 0x3FFFFFFF & Long.parseLong(substring, 16);
      String outChars = "";
      for (int j = 0; j < length; j++) {
        // 把得到的值与 0x0000003D进行位与运算，取得字符数组 chars索引
        long index = 0x0000003D & hex;
        // 把取得的字符相加
        outChars += chars[(int) index];
        // 每次循环按位右移 5 位
        hex = hex >> 5;
      }

      // 添加生产因子
      if (factors > 6) {
        String suffix = randomID.random(factors - 6);
        outChars += suffix;
      }

      result[i] = outChars;
    }

    // 3. 返回结果数组
    return result;
  }

}
