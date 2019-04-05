package com.openyich.framework.boot.id.factory.support;

import java.util.ArrayList;
import java.util.List;

public final class GoogleAuthenticatorKey {

  private final GoogleAuthenticatorConfig config;
  private final String key;
  private final int verificationCode;
  private final List<Integer> scratchCodes;

  private GoogleAuthenticatorKey(GoogleAuthenticatorConfig config, String key, int verificationCode,
      List<Integer> scratchCodes) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }

    if (config == null) {
      throw new IllegalArgumentException("Configuration cannot be null");
    }

    if (scratchCodes == null) {
      throw new IllegalArgumentException("Scratch codes cannot be null");
    }

    this.config = config;
    this.key = key;
    this.verificationCode = verificationCode;
    this.scratchCodes = new ArrayList<>(scratchCodes);
  }

  public List<Integer> getScratchCodes() {
    return scratchCodes;
  }

  public GoogleAuthenticatorConfig getConfig() {
    return config;
  }

  public String getKey() {
    return key;
  }

  public int getVerificationCode() {
    return verificationCode;
  }

  public static class Builder {
    private GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig();
    private String key;
    private int verificationCode;
    private List<Integer> scratchCodes = new ArrayList<>();

    public Builder(String key) {
      this.key = key;
    }

    public GoogleAuthenticatorKey build() {
      return new GoogleAuthenticatorKey(config, key, verificationCode, scratchCodes);
    }

    public Builder setConfig(GoogleAuthenticatorConfig config) {
      this.config = config;
      return this;
    }

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder setVerificationCode(int verificationCode) {
      this.verificationCode = verificationCode;
      return this;
    }

    public Builder setScratchCodes(List<Integer> scratchCodes) {
      this.scratchCodes = scratchCodes;
      return this;
    }
  }
}
