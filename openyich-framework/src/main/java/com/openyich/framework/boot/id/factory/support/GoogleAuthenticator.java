package com.openyich.framework.boot.id.factory.support;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Assert;

public final class GoogleAuthenticator implements IGoogleAuthenticator {

  public static final String RNG_ALGORITHM = "openyich.id.rng.algorithm";
  public static final String RNG_ALGORITHM_PROVIDER = "openyich.id.rng.algorithmProvider";
  private static final int SECRET_BITS = 80;
  private static final int SCRATCH_CODES = 5;
  private static final int SCRATCH_CODE_LENGTH = 8;
  public static final int SCRATCH_CODE_MODULUS = (int) Math.pow(10, SCRATCH_CODE_LENGTH);
  private static final int SCRATCH_CODE_INVALID = -1;
  private static final int BYTES_PER_SCRATCH_CODE = 4;
  private static final String DEFAULT_RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
  private static final String DEFAULT_RANDOM_NUMBER_ALGORITHM_PROVIDER = "SUN";
  private final GoogleAuthenticatorConfig config;
  private ReseedingSecureRandom secureRandom =
      new ReseedingSecureRandom(getRandomNumberAlgorithm(), getRandomNumberAlgorithmProvider());

  public GoogleAuthenticator() {
    config = new GoogleAuthenticatorConfig();
  }

  public GoogleAuthenticator(GoogleAuthenticatorConfig config) {
    if (config == null) {
      throw new IllegalArgumentException("Configuration cannot be null.");
    }

    this.config = config;
  }

  private String getRandomNumberAlgorithm() {
    return System.getProperty(RNG_ALGORITHM, DEFAULT_RANDOM_NUMBER_ALGORITHM);
  }

  private String getRandomNumberAlgorithmProvider() {
    return System.getProperty(RNG_ALGORITHM_PROVIDER, DEFAULT_RANDOM_NUMBER_ALGORITHM_PROVIDER);
  }

  int calculateCode(byte[] key, long tm) {
    byte[] data = new byte[8];
    long value = tm;
    for (int i = 8; i-- > 0; value >>>= 8) {
      data[i] = (byte) value;
    }
    SecretKeySpec signKey = new SecretKeySpec(key, config.getHmacHashFunction().toString());
    try {
      Mac mac = Mac.getInstance(config.getHmacHashFunction().toString());
      mac.init(signKey);
      byte[] hash = mac.doFinal(data);
      int offset = hash[hash.length - 1] & 0xF;
      long truncatedHash = 0;

      for (int i = 0; i < 4; ++i) {
        truncatedHash <<= 8;
        truncatedHash |= (hash[offset + i] & 0xFF);
      }
      truncatedHash &= 0x7FFFFFFF;
      truncatedHash %= config.getKeyModulus();
      return (int) truncatedHash;
    } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
      throw new GoogleAuthenticatorException("The operation cannot be performed now.", ex);
    }
  }

  private long getTimeWindowFromTime(long time) {
    return time / this.config.getTimeStepSizeInMillis();
  }

  private boolean checkCode(String secret, long code, long timestamp, int window) {
    byte[] decodedKey = decodeSecret(secret);
    final long timeWindow = getTimeWindowFromTime(timestamp);
    for (int i = -((window - 1) / 2); i <= window / 2; ++i) {
      long hash = calculateCode(decodedKey, timeWindow + i);
      if (hash == code) {
        return true;
      }
    }
    return false;
  }

  private byte[] decodeSecret(String secret) {
    switch (config.getKeyRepresentation()) {
      case BASE32:
        Base32 codec32 = new Base32();
        return codec32.decode(secret.toUpperCase());
      case BASE64:
        Base64 codec64 = new Base64();
        return codec64.decode(secret);
      default:
        throw new IllegalArgumentException("Unknown key representation type.");
    }
  }

  @Override
  public GoogleAuthenticatorKey createCredentials() {
    byte[] buffer = new byte[SECRET_BITS / 8 + SCRATCH_CODES * BYTES_PER_SCRATCH_CODE];
    secureRandom.nextBytes(buffer);
    byte[] secretKey = Arrays.copyOf(buffer, SECRET_BITS / 8);
    String generatedKey = calculateSecretKey(secretKey);
    int validationCode = calculateValidationCode(secretKey);
    List<Integer> scratchCodes = calculateScratchCodes(buffer);
    return new GoogleAuthenticatorKey.Builder(generatedKey).setConfig(config)
        .setVerificationCode(validationCode).setScratchCodes(scratchCodes).build();
  }

  private List<Integer> calculateScratchCodes(byte[] buffer) {
    List<Integer> scratchCodes = new ArrayList<>();
    while (scratchCodes.size() < SCRATCH_CODES) {
      byte[] scratchCodeBuffer = Arrays.copyOfRange(buffer,
          SECRET_BITS / 8 + BYTES_PER_SCRATCH_CODE * scratchCodes.size(),
          SECRET_BITS / 8 + BYTES_PER_SCRATCH_CODE * scratchCodes.size() + BYTES_PER_SCRATCH_CODE);
      int scratchCode = calculateScratchCode(scratchCodeBuffer);
      if (scratchCode != SCRATCH_CODE_INVALID) {
        scratchCodes.add(scratchCode);
      } else {
        scratchCodes.add(generateScratchCode());
      }
    }
    return scratchCodes;
  }

  private int calculateScratchCode(byte[] scratchCodeBuffer) {
    if (scratchCodeBuffer.length < BYTES_PER_SCRATCH_CODE) {
      throw new IllegalArgumentException(String
          .format("The provided random byte buffer is too small: %d.", scratchCodeBuffer.length));
    }
    int scratchCode = 0;
    for (int i = 0; i < BYTES_PER_SCRATCH_CODE; ++i) {
      scratchCode = (scratchCode << 8) + (scratchCodeBuffer[i] & 0xff);
    }
    scratchCode = (scratchCode & 0x7FFFFFFF) % SCRATCH_CODE_MODULUS;
    return (validateScratchCode(scratchCode)) ? scratchCode : SCRATCH_CODE_INVALID;
  }

  boolean validateScratchCode(int scratchCode) {
    return (scratchCode >= SCRATCH_CODE_MODULUS / 10);
  }

  private int generateScratchCode() {
    while (true) {
      byte[] scratchCodeBuffer = new byte[BYTES_PER_SCRATCH_CODE];
      secureRandom.nextBytes(scratchCodeBuffer);
      int scratchCode = calculateScratchCode(scratchCodeBuffer);
      if (scratchCode != SCRATCH_CODE_INVALID) {
        return scratchCode;
      }
    }
  }

  private int calculateValidationCode(byte[] secretKey) {
    return calculateCode(secretKey, 0);
  }

  @Override
  public String getTotpPassword(String secret) {
    return getTotpPassword(secret, System.currentTimeMillis());
  }

  @Override
  public String getTotpPassword(String secret, long time) {
    int code = calculateCode(decodeSecret(secret), getTimeWindowFromTime(time));
    return lpad(Integer.toString(code), config.getCodeDigits(), '0');
  }

  private String calculateSecretKey(byte[] secretKey) {
    switch (config.getKeyRepresentation()) {
      case BASE32:
        return new Base32().encodeToString(secretKey);
      case BASE64:
        return new Base64().encodeToString(secretKey);
      default:
        throw new IllegalArgumentException("Unknown key representation type.");
    }
  }

  @Override
  public boolean authorize(String secret, int verificationCode)
      throws GoogleAuthenticatorException {
    return authorize(secret, verificationCode, System.currentTimeMillis());
  }

  @Override
  public boolean authorize(String secret, int verificationCode, long time)
      throws GoogleAuthenticatorException {
    if (secret == null) {
      throw new IllegalArgumentException("Secret cannot be null.");
    }
    if (verificationCode <= 0 || verificationCode >= this.config.getKeyModulus()) {
      return false;
    }
    return checkCode(secret, verificationCode, time, this.config.getWindowSize());
  }

  private static String lpad(String str, int length, char ch) {
    Assert.notNull(str, "string must be not null.");
    for (int i = str.length(); i < length; i++) {
      str = ch + str;
    }
    return str;
  }

}
