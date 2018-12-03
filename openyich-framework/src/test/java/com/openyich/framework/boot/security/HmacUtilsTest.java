package com.openyich.framework.boot.security;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

public class HmacUtilsTest {

  private static final Logger log = LoggerFactory.getLogger(HmacUtilsTest.class);

  private String testData;

  @Before
  public void setup() {
    testData = UUID.randomUUID().toString();
  }

  @Test
  public void testHmacMD5() throws NoSuchAlgorithmException, InvalidKeyException {
    String secretKey = HmacUtils.generateKey(HmacAlgorithms.HMAC_MD5);
    String hmac = HmacUtils.encryptHMAC(HmacAlgorithms.HMAC_MD5, secretKey, testData);
    log.info("Key: {}", secretKey);
    log.info("HmacMD5: {}", hmac);
    log.info("Base64 HmacMD5: {}", Base64Utils.encodeToString(hmac.getBytes()));
    assertThat(hmac).isNotBlank();
  }

  @Test
  public void testHmacSHA1() throws NoSuchAlgorithmException, InvalidKeyException {
    String secretKey = HmacUtils.generateKey(HmacAlgorithms.HMAC_SHA_1);
    String hmac = HmacUtils.encryptHMAC(HmacAlgorithms.HMAC_SHA_1, secretKey, testData);
    log.info("Key: {}", secretKey);
    log.info("HmacSHA1: {}", hmac);
    log.info("Base64 HmacSHA1: {}", Base64Utils.encodeToString(hmac.getBytes()));
    assertThat(hmac).isNotBlank();
  }

  @Test
  public void testHmacSHA224() throws NoSuchAlgorithmException, InvalidKeyException {
    String secretKey = HmacUtils.generateKey(HmacAlgorithms.HMAC_SHA_224);
    String hmac = HmacUtils.encryptHMAC(HmacAlgorithms.HMAC_SHA_224, secretKey, testData);
    log.info("Key: {}", secretKey);
    log.info("HmacSHA224: {}", hmac);
    log.info("Base64 HmacSHA224: {}", Base64Utils.encodeToString(hmac.getBytes()));
    assertThat(hmac).isNotBlank();
  }

  @Test
  public void testHmacSHA256() throws NoSuchAlgorithmException, InvalidKeyException {
    String secretKey = HmacUtils.generateKey(HmacAlgorithms.HMAC_SHA_256);
    String hmac = HmacUtils.encryptHMAC(HmacAlgorithms.HMAC_SHA_256, secretKey, testData);
    log.info("Key: {}", secretKey);
    log.info("HmacSHA256: {}", hmac);
    log.info("Base64 HmacSHA256: {}", Base64Utils.encodeToString(hmac.getBytes()));
    assertThat(hmac).isNotBlank();
  }

  @Test
  public void testHmacSHA384() throws NoSuchAlgorithmException, InvalidKeyException {
    String secretKey = HmacUtils.generateKey(HmacAlgorithms.HMAC_SHA_384);
    String hmac = HmacUtils.encryptHMAC(HmacAlgorithms.HMAC_SHA_384, secretKey, testData);
    log.info("Key: {}", secretKey);
    log.info("HmacSHA384: {}", hmac);
    log.info("Base64 HmacSHA384: {}", Base64Utils.encodeToString(hmac.getBytes()));
    assertThat(hmac).isNotBlank();
  }

  @Test
  public void testHmacSHA512() throws NoSuchAlgorithmException, InvalidKeyException {
    String secretKey = HmacUtils.generateKey(HmacAlgorithms.HMAC_SHA_512);
    String hmac = HmacUtils.encryptHMAC(HmacAlgorithms.HMAC_SHA_512, secretKey, testData);
    log.info("Key: {}", secretKey);
    log.info("HmacSHA512: {}", hmac);
    log.info("Base64 HmacSHA512: {}", Base64Utils.encodeToString(hmac.getBytes()));
    assertThat(hmac).isNotBlank();
  }

}
