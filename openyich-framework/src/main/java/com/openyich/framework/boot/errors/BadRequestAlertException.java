package com.openyich.framework.boot.errors;

import java.util.HashMap;
import java.util.Map;

import org.zalando.problem.StatusType;

import static org.zalando.problem.Status.BAD_REQUEST;

/**
 * Bad Request Alert.
 * 
 * <pre>
 * new BadRequestAlertException("Email is already in use!", "userManagement", "emailexists");
 * </pre>
 *
 */
public class BadRequestAlertException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;
  private final String entityName;
  private final String errorKey;

  public BadRequestAlertException(String title, StatusType status, String message,
      String entityName, String errorKey) {
    super(title, status, message, getAlertParameters(entityName, errorKey));
    this.entityName = entityName;
    this.errorKey = errorKey;
  }

  public BadRequestAlertException(String message, String entityName, String errorKey) {
    this("Bad Request", BAD_REQUEST, message, entityName, errorKey);
  }

  public String getEntityName() {
    return entityName;
  }

  public String getErrorKey() {
    return errorKey;
  }

  private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("message", "error." + errorKey);
    parameters.put("params", entityName);
    return parameters;
  }

}
