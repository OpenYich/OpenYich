package com.openyich.framework.boot.errors;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Map;

import org.apiguardian.api.API;
import org.zalando.problem.Status;

@API(status = STABLE)
public class SecurityException extends OpenYichProblemException {

  private static final long serialVersionUID = 1L;

  public SecurityException(String code, String message, Map<String, Object> parameters) {
    super(code, Status.UNAUTHORIZED, message, parameters);
  }

  public SecurityException(String code, String message, String... params) {
    super(code, Status.UNAUTHORIZED, message, params);
  }

  public SecurityException(String code, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(code, Status.UNAUTHORIZED, message, cause, parameters);
  }

  public SecurityException(String code, String message, Throwable cause, String... params) {
    super(code, Status.UNAUTHORIZED, message, cause, params);
  }

  public SecurityException(String code, String message) {
    super(code, Status.UNAUTHORIZED, message);
  }

  public SecurityException(String code, Throwable cause) {
    super(code, Status.UNAUTHORIZED, cause);
  }

  public SecurityException(String code) {
    super(code, Status.UNAUTHORIZED);
  }

}
