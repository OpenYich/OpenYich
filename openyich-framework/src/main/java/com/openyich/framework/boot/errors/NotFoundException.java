package com.openyich.framework.boot.errors;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Map;

import org.apiguardian.api.API;
import org.zalando.problem.Status;

@API(status = STABLE)
public class NotFoundException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public NotFoundException(String code, String message, Map<String, Object> parameters) {
    super(code, Status.NOT_FOUND, message, parameters);
  }

  public NotFoundException(String code, String message, String... params) {
    super(code, Status.NOT_FOUND, message, params);
  }

  public NotFoundException(String code, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(code, Status.NOT_FOUND, message, cause, parameters);
  }

  public NotFoundException(String code, String message, Throwable cause, String... params) {
    super(code, Status.NOT_FOUND, message, cause, params);
  }

  public NotFoundException(String code, String message) {
    super(code, Status.NOT_FOUND, message);
  }

  public NotFoundException(String code, Throwable cause) {
    super(code, Status.NOT_FOUND, cause);
  }

  public NotFoundException(String code) {
    super(code, Status.NOT_FOUND);
  }

}
