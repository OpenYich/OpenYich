package com.openyich.framework.boot.errors;

import java.util.Map;

import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

/**
 * Request Resource Not Found.
 * 
 * <pre>
 * new NotFoundException("Email address not registered");
 * </pre>
 */
public class NotFoundException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public NotFoundException(String message) {
    super("Not Found", Status.NOT_FOUND);
  }
  
  public NotFoundException(String message, Map<String, Object> parameters) {
    super("Not Found", Status.NOT_FOUND, message, parameters);
  }

  public NotFoundException(String message, String... params) {
    super("Not Found", Status.NOT_FOUND, message, params);
  }

  public NotFoundException(String message, ThrowableProblem cause, Map<String, Object> parameters) {
    super("Not Found", Status.NOT_FOUND, message, cause, parameters);
  }

  public NotFoundException(String message, ThrowableProblem cause, String... params) {
    super("Not Found", Status.NOT_FOUND, message, cause, params);
  }

}
