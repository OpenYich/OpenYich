package com.openyich.framework.boot.errors;

import java.util.Map;

import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

public class AlreadyUsedException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public AlreadyUsedException(String message) {
    super("Already Used", Status.CONFLICT, message);
  }

  public AlreadyUsedException(String message, Map<String, Object> parameters) {
    super("Already Used", Status.CONFLICT, message, parameters);
  }

  public AlreadyUsedException(String message, String... params) {
    super("Already Used", Status.CONFLICT, message, params);
  }

  public AlreadyUsedException(String message, ThrowableProblem cause,
      Map<String, Object> parameters) {
    super("Already Used", Status.CONFLICT, message, cause, parameters);
  }

  public AlreadyUsedException(String message, ThrowableProblem cause, String... params) {
    super("Already Used", Status.CONFLICT, message, cause, params);
  }

}
