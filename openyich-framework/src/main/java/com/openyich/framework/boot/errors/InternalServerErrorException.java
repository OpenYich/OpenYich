package com.openyich.framework.boot.errors;

import org.zalando.problem.Status;

/**
 * Simple exception with a message, that returns an Internal Server Error code.
 */
public class InternalServerErrorException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public InternalServerErrorException(String message) {
    super(message, Status.INTERNAL_SERVER_ERROR);
  }
  
}
