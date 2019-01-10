package com.openyich.framework.boot.errors;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Map;

import org.apiguardian.api.API;
import org.zalando.problem.Status;

@API(status = STABLE)
public class UnauthorizedException extends OpenYichProblemException {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException(String code, String message, Map<String, Object> parameters) {
    super(code, Status.UNAUTHORIZED, message, parameters);
  }

  public UnauthorizedException(String code, String message, String... params) {
    super(code, Status.UNAUTHORIZED, message, params);
  }

  public UnauthorizedException(String code, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(code, Status.UNAUTHORIZED, message, cause, parameters);
  }

  public UnauthorizedException(String code, String message, Throwable cause, String... params) {
    super(code, Status.UNAUTHORIZED, message, cause, params);
  }

  public UnauthorizedException(String code, String message) {
    super(code, Status.UNAUTHORIZED, message);
  }

  public UnauthorizedException(String code, Throwable cause) {
    super(code, Status.UNAUTHORIZED, cause);
  }

  public UnauthorizedException(String code) {
    super(code, Status.UNAUTHORIZED);
  }

}
