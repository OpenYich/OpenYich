package com.openyich.framework.boot.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.springframework.util.Base64Utils;

import com.google.common.base.Strings;

/**
 * Utility class for HMAC.
 */
public final class HmacUtils {

  private final static String[] hexDigits =
      {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

  private static String byteArrayToHexString(final byte[] bytes) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      sb.append(byteToHexString(bytes[i]));
    }
    return sb.toString();
  }

  private static String byteToHexString(final byte b) {
    int ret = b;
    if (ret < 0) {
      ret += 256;
    }
    int m = ret / 16;
    int n = ret % 16;
    return hexDigits[m] + hexDigits[n];
  }

  private static byte[] decryptBase64(String key) {
    return Base64Utils.decodeFromString(key);
  }

  private static String encryptBase64(byte[] key) {
    return Base64Utils.encodeToString(key);
  }

  public static byte[] encryptHMAC(HmacAlgorithms alg, String key, byte[] data)
      throws NoSuchAlgorithmException, InvalidKeyException {
    SecretKey secretKey = new SecretKeySpec(decryptBase64(key), alg.getName());
    Mac mac = Mac.getInstance(secretKey.getAlgorithm());
    mac.init(secretKey);
    return mac.doFinal(data);
  }

  public static String encryptHMAC(HmacAlgorithms alg, String key, String data)
      throws InvalidKeyException, NoSuchAlgorithmException {
    byte[] bytes = encryptHMAC(alg, key, Strings.nullToEmpty(data).getBytes());
    return byteArrayToHexString(bytes);
  }

  public static String generateKey(HmacAlgorithms alg) throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance(alg.getName());
    SecretKey secretKey = keyGenerator.generateKey();
    return encryptBase64(secretKey.getEncoded());
  }

  private HmacUtils() {}

}
