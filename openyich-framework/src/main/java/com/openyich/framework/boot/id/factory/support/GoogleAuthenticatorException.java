package com.openyich.framework.boot.id.factory.support;

public class GoogleAuthenticatorException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public GoogleAuthenticatorException(String message) {
    super(message);
  }

  public GoogleAuthenticatorException(String message, Throwable cause) {
    super(message, cause);
  }
}
