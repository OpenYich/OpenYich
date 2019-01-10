package com.openyich.framework.boot.errors;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Map;

import org.apiguardian.api.API;
import org.zalando.problem.Status;

@API(status = STABLE)
public class AlreadyUsedException extends OpenYichProblemException {

  private static final long serialVersionUID = 1L;

  public AlreadyUsedException(String code, String message, Map<String, Object> parameters) {
    super(code, Status.CONFLICT, message, parameters);
  }

  public AlreadyUsedException(String code, String message, String... params) {
    super(code, Status.CONFLICT, message, params);
  }

  public AlreadyUsedException(String code, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(code, Status.CONFLICT, message, cause, parameters);
  }

  public AlreadyUsedException(String code, String message, Throwable cause, String... params) {
    super(code, Status.CONFLICT, message, cause, params);
  }

  public AlreadyUsedException(String code, String message) {
    super(code, Status.CONFLICT, message);
  }

  public AlreadyUsedException(String code, Throwable cause) {
    super(code, Status.CONFLICT, cause);
  }

  public AlreadyUsedException(String code) {
    super(code, Status.CONFLICT);
  }

}
