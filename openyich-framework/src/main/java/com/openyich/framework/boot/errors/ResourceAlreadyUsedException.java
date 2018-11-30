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

  public ResourceAlreadyUsedException(String defaultMessage, String entityName, String errorKey) {
    super("Already Used Exception", Status.BAD_REQUEST, defaultMessage, entityName, errorKey);
  }

}
