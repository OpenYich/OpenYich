package com.openyich.framework.boot.errors;

import org.zalando.problem.Status;

/**
 * Request Resource already used.
 * 
 * <pre>
 * new ResourceAlreadyUsedException("Email is already in use!", "userManagement", "emailexists");
 * </pre>
 */
public class ResourceAlreadyUsedException extends BadRequestAlertException {

  private static final long serialVersionUID = 1L;

  public ResourceAlreadyUsedException(String message, String entityName, String errorKey) {
    super("Resource Already Used", Status.BAD_REQUEST, message, entityName, errorKey);
  }

}
