package com.openyich.framework.boot.errors;

import java.util.Map;

import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

public class BadRequestAlertException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;
  
  public BadRequestAlertException(String message) {
    super("Bad Request", Status.BAD_REQUEST);
  }
  
  public BadRequestAlertException(String message, Map<String, Object> parameters) {
    super("Bad Request", Status.BAD_REQUEST, message, parameters);
  }

  public BadRequestAlertException(String message, String... params) {
    super("Bad Request", Status.BAD_REQUEST, message, params);
  }

  public BadRequestAlertException(String message, ThrowableProblem cause, Map<String, Object> parameters) {
    super("Bad Request", Status.BAD_REQUEST, message, cause, parameters);
  }

  public BadRequestAlertException(String message, ThrowableProblem cause, String... params) {
    super("Bad Request", Status.BAD_REQUEST, message, cause, params);
  }

}
