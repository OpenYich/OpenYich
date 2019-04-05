package com.openyich.framework.boot.id.factory.support;

import java.util.List;

public class TOTP {

  private String secret;
  private String code;
  private List<Integer> scratchCodes;
  private long timestamp;

  public TOTP() {}

  public TOTP(String secret, String code, List<Integer> scratchCodes, long timestamp) {
    this.secret = secret;
    this.code = code;
    this.scratchCodes = scratchCodes;
    this.timestamp = timestamp;
  }

  public String getCode() {
    return code;
  }
  
  public int getCodeAsInt() {
    return Integer.parseInt(code);
  }

  public List<Integer> getScratchCodes() {
    return scratchCodes;
  }

  public String getSecret() {
    return secret;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setScratchCodes(List<Integer> scratchCodes) {
    this.scratchCodes = scratchCodes;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "TOTP [secret=" + secret + ", code=" + code + ", scratchCodes=" + scratchCodes
        + ", timestamp=" + timestamp + "]";
  }

}
