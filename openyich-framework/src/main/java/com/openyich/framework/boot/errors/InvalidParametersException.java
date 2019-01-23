package com.openyich.framework.boot.errors;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Map;

import org.apiguardian.api.API;
import org.zalando.problem.Status;

@API(status = STABLE)
public class InvalidParametersException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public InvalidParametersException(String code, String message, Map<String, Object> parameters) {
    super(code, Status.BAD_REQUEST, message, parameters);
  }

  public InvalidParametersException(String code, String message, String... params) {
    super(code, Status.BAD_REQUEST, message, params);
  }

  public InvalidParametersException(String code, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(code, Status.BAD_REQUEST, message, cause, parameters);
  }

  public InvalidParametersException(String code, String message, Throwable cause,
      String... params) {
    super(code, Status.BAD_REQUEST, message, cause, params);
  }

  public InvalidParametersException(String code, String message) {
    super(code, Status.BAD_REQUEST, message);
  }

  public InvalidParametersException(String code, Throwable cause) {
    super(code, Status.BAD_REQUEST, cause);
  }

  public InvalidParametersException(String code) {
    super(code, Status.BAD_REQUEST);
  }

}
