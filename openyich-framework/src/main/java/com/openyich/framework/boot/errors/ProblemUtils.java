package com.openyich.framework.boot.errors;

public final class ProblemUtils {

  private static final ThreadLocal<String> TL = new ThreadLocal<>();

  public static String getCode() {
    return TL.get();
  }

  public static void setCode(String code) {
    TL.set(code);
  }

}
