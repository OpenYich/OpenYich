package com.openyich.framework.boot.errors;

import java.util.Map;

import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

public class ParameterizedException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public ParameterizedException(String message) {
    super("Parameterized Exception", Status.BAD_REQUEST, message);
  }
  
  public ParameterizedException(String message, Map<String, Object> parameters) {
    super("Parameterized Exception", Status.BAD_REQUEST, message, parameters);
  }

  public ParameterizedException(String message, String... params) {
    super("Parameterized Exception", Status.BAD_REQUEST, message, params);
  }

  public ParameterizedException(String message, ThrowableProblem cause,
      Map<String, Object> parameters) {
    super("Parameterized Exception", Status.BAD_REQUEST, message, cause, parameters);
  }

  public ParameterizedException(String message, ThrowableProblem cause, String... params) {
    super("Parameterized Exception", Status.BAD_REQUEST, message, cause, params);
  }

}
