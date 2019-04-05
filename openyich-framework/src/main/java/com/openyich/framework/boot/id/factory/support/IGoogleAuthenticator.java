package com.openyich.framework.boot.id.factory.support;

public interface IGoogleAuthenticator {

  boolean authorize(String secret, int verificationCode) throws GoogleAuthenticatorException;

  boolean authorize(String secret, int verificationCode, long time)
      throws GoogleAuthenticatorException;

  GoogleAuthenticatorKey createCredentials();

  String getTotpPassword(String secret);

  String getTotpPassword(String secret, long time);

}
