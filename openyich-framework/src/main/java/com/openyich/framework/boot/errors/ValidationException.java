package com.openyich.framework.boot.errors;

import java.util.Map;

import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

public class ValidationException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public ValidationException(String message) {
    super("Not Validated", Status.BAD_REQUEST, message);
  }

  public ValidationException(String message, Map<String, Object> parameters) {
    super("Not Validated", Status.BAD_REQUEST, message, parameters);
  }

  public ValidationException(String message, String... params) {
    super("Not Validated", Status.BAD_REQUEST, message, params);
  }

  public ValidationException(String message, ThrowableProblem cause,
      Map<String, Object> parameters) {
    super("Not Validated", Status.BAD_REQUEST, message, cause, parameters);
  }

  public ValidationException(String message, ThrowableProblem cause, String... params) {
    super("Not Validated", Status.BAD_REQUEST, message, cause, params);
  }

}
