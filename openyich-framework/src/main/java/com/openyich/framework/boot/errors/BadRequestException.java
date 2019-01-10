package com.openyich.framework.boot.errors;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Map;

import org.apiguardian.api.API;
import org.zalando.problem.Status;

@API(status = STABLE)
public class BadRequestException extends OpenYichProblemException {

  private static final long serialVersionUID = 1L;

  public BadRequestException(String code, String message, Map<String, Object> parameters) {
    super(code, Status.BAD_REQUEST, message, parameters);
  }

  public BadRequestException(String code, String message, String... params) {
    super(code, Status.BAD_REQUEST, message, params);
  }

  public BadRequestException(String code, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(code, Status.BAD_REQUEST, message, cause, parameters);
  }

  public BadRequestException(String code, String message, Throwable cause, String... params) {
    super(code, Status.BAD_REQUEST, message, cause, params);
  }

  public BadRequestException(String code, String message) {
    super(code, Status.BAD_REQUEST, message);
  }

  public BadRequestException(String code, Throwable cause) {
    super(code, Status.BAD_REQUEST, cause);
  }

  public BadRequestException(String code) {
    super(code, Status.BAD_REQUEST);
  }

}
