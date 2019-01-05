package com.openyich.framework.boot.errors;

import org.zalando.problem.Status;

public class UnauthorizedException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException() {
    this(null);
  }

  public UnauthorizedException(String message) {
    super("Unauthorized", Status.UNAUTHORIZED, message);
  }

}
