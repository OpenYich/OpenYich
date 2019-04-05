package com.openyich.framework.boot.id.factory;

import org.springframework.util.Assert;

import com.openyich.framework.boot.id.factory.support.GoogleAuthenticatorException;
import com.openyich.framework.boot.id.factory.support.GoogleAuthenticatorKey;
import com.openyich.framework.boot.id.factory.support.IGoogleAuthenticator;
import com.openyich.framework.boot.id.factory.support.TOTP;
import com.openyich.framework.boot.id.service.OneTimePasswordID;

/**
 * TOTP 实现工厂
 */
public class OneTimePasswordFactory implements OneTimePasswordID {

  private IGoogleAuthenticator googleAuthenticator;

  public OneTimePasswordFactory(IGoogleAuthenticator googleAuthenticator) {
    this.googleAuthenticator = googleAuthenticator;
  }

  @Override
  public boolean authorize(String secret, int code, long timestamp)
      throws GoogleAuthenticatorException {
    return googleAuthenticator.authorize(secret, code, timestamp);
  }

  @Override
  public boolean authorize(TOTP totp) throws GoogleAuthenticatorException {
    Assert.notNull(totp, "TOTP cannot be null.");
    return authorize(totp.getSecret(), totp.getCodeAsInt(), totp.getTimestamp());
  }

  @Override
  public TOTP getTotpPassword() {
    return getTotpPassword(System.currentTimeMillis());
  }

  @Override
  public TOTP getTotpPassword(long timestamp) {
    GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
    String code = googleAuthenticator.getTotpPassword(key.getKey(), timestamp);
    return new TOTP(key.getKey(), code, key.getScratchCodes(), timestamp);
  }

}
