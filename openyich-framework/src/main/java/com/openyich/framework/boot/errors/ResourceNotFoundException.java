package com.openyich.framework.boot.errors;

import org.zalando.problem.Status;

/**
 * Request Resource Not Found.
 * 
 * <pre>
 * new ResourceNotFoundException("Email address not registered");
 * </pre>
 *
 */
public class ResourceNotFoundException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String message) {
    super("Resource Not Found", Status.NOT_FOUND, message);
  }

}
