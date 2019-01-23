package com.openyich.framework.boot.errors;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Map;

import org.apiguardian.api.API;
import org.zalando.problem.Status;

@API(status = STABLE)
public class OpenYichException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public OpenYichException(String code, Status status, String message,
      Map<String, Object> parameters) {
    super(code, status, message, parameters);
  }

  public OpenYichException(String code, Status status, String message, String... params) {
    super(code, status, message, params);
  }

  public OpenYichException(String code, Status status, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(code, status, message, cause, parameters);
  }

  public OpenYichException(String code, Status status, String message, Throwable cause,
      String... params) {
    super(code, status, message, cause, params);
  }

  public OpenYichException(String code, Status status, String message) {
    super(code, status, message);
  }

  public OpenYichException(String code, Status status, Throwable cause) {
    super(code, status, cause);
  }

  public OpenYichException(String code, Status status) {
    super(code, status);
  }

  public OpenYichException(String code, String message, Map<String, Object> parameters) {
    super(code, message, parameters);
  }

  public OpenYichException(String code, String message, String... params) {
    super(code, message, params);
  }

  public OpenYichException(String code, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(code, message, cause, parameters);
  }

  public OpenYichException(String code, String message, Throwable cause, String... params) {
    super(code, message, cause, params);
  }

  public OpenYichException(String code, String message) {
    super(code, message);
  }

  public OpenYichException(String code, Throwable cause) {
    super(code, cause);
  }

  public OpenYichException(String code) {
    super(code);
  }

}
