package com.openyich.framework.boot.errors;

import java.util.Map;

import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

public class SecurityException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public SecurityException(String message) {
    super("Security Forbidden", Status.FORBIDDEN, message);
  }
  
  public SecurityException(String message, Map<String, Object> parameters) {
    super("Security Forbidden", Status.FORBIDDEN, message, parameters);
  }

  public SecurityException(String message, String... params) {
    super("Security Forbidden", Status.FORBIDDEN, message, params);
  }

  public SecurityException(String message, ThrowableProblem cause, Map<String, Object> parameters) {
    super("Security Forbidden", Status.FORBIDDEN, message, cause, parameters);
  }

  public SecurityException(String message, ThrowableProblem cause, String... params) {
    super("Security Forbidden", Status.FORBIDDEN, message, cause, params);
  }

}
