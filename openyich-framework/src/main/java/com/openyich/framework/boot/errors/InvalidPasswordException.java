package com.openyich.framework.boot.errors;

import org.zalando.problem.Status;

public class InvalidPasswordException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public InvalidPasswordException() {
    this(null);
  }
  
  public InvalidPasswordException(String message) {
    super("Incorrect password", Status.BAD_REQUEST, message);
  }

}
