package com.openyich.framework.boot.id.service;

import com.openyich.framework.boot.id.factory.support.GoogleAuthenticatorException;
import com.openyich.framework.boot.id.factory.support.TOTP;

/**
 * the Time-based One-time Password (TOTP) from Google Authenticator.
 */
public interface OneTimePasswordID {

  /**
   * 验证码是否有效
   * 
   * @param secret 安全码
   * @param code 验证码
   * @param timestamp 生成码的时间
   * @return true for success
   * @throws GoogleAuthenticatorException
   */
  boolean authorize(String secret, int code, long timestamp) throws GoogleAuthenticatorException;

  /**
   * 验证码是否有效
   * 
   * @param totp
   * @return true for success
   * @throws GoogleAuthenticatorException
   */
  boolean authorize(TOTP totp) throws GoogleAuthenticatorException;

  /**
   * 获取一个验证码（系统当前时间）
   * 
   * @return 验证码
   */
  TOTP getTotpPassword();

  /**
   * 获取一个特定时间的验证码
   * 
   * @return 验证码
   */
  TOTP getTotpPassword(long timestamp);

}
