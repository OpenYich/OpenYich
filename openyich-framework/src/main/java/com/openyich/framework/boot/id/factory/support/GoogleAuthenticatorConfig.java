package com.openyich.framework.boot.id.factory.support;

import java.util.concurrent.TimeUnit;

public class GoogleAuthenticatorConfig {

  private long timeStepSizeInMillis = TimeUnit.SECONDS.toMillis(30);
  private int windowSize = 3;
  private int codeDigits = 6;
  private int keyModulus = (int) Math.pow(10, codeDigits);
  private KeyRepresentation keyRepresentation = KeyRepresentation.BASE32;
  private HmacHashFunction hmacHashFunction = HmacHashFunction.HmacSHA1;

  public int getKeyModulus() {
    return keyModulus;
  }

  public KeyRepresentation getKeyRepresentation() {
    return keyRepresentation;
  }

  public int getCodeDigits() {
    return codeDigits;
  }

  public long getTimeStepSizeInMillis() {
    return timeStepSizeInMillis;
  }

  public int getWindowSize() {
    return windowSize;
  }

  public HmacHashFunction getHmacHashFunction() {
    return hmacHashFunction;
  }

  public static class GoogleAuthenticatorConfigBuilder {
    private GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig();

    public GoogleAuthenticatorConfig build() {
      return config;
    }

    public GoogleAuthenticatorConfigBuilder setCodeDigits(int codeDigits) {
      if (codeDigits <= 0) {
        throw new IllegalArgumentException("Code digits must be positive.");
      }

      if (codeDigits < 6) {
        throw new IllegalArgumentException("The minimum number of digits is 6.");
      }

      if (codeDigits > 8) {
        throw new IllegalArgumentException("The maximum number of digits is 8.");
      }

      config.codeDigits = codeDigits;
      config.keyModulus = (int) Math.pow(10, codeDigits);
      return this;
    }

    public GoogleAuthenticatorConfigBuilder setTimeStepSizeInMillis(long timeStepSizeInMillis) {
      if (timeStepSizeInMillis <= 0) {
        throw new IllegalArgumentException("Time step size must be positive.");
      }

      config.timeStepSizeInMillis = timeStepSizeInMillis;
      return this;
    }

    public GoogleAuthenticatorConfigBuilder setWindowSize(int windowSize) {
      if (windowSize <= 0) {
        throw new IllegalArgumentException("Window number must be positive.");
      }

      config.windowSize = windowSize;
      return this;
    }

    public GoogleAuthenticatorConfigBuilder setKeyRepresentation(
        KeyRepresentation keyRepresentation) {
      if (keyRepresentation == null) {
        throw new IllegalArgumentException("Key representation cannot be null.");
      }

      config.keyRepresentation = keyRepresentation;
      return this;
    }

    public GoogleAuthenticatorConfigBuilder setHmacHashFunction(HmacHashFunction hmacHashFunction) {
      if (hmacHashFunction == null) {
        throw new IllegalArgumentException("HMAC Hash Function cannot be null.");
      }

      config.hmacHashFunction = hmacHashFunction;
      return this;
    }
  }
}
