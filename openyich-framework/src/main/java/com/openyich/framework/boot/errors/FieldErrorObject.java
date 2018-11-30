package com.openyich.framework.boot.errors;

import java.io.Serializable;

public class FieldErrorObject implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String objectName;

  private final String field;

  private final String message;

  public FieldErrorObject(String dto, String field, String message) {
    this.objectName = dto;
    this.field = field;
    this.message = message;
  }

  public String getObjectName() {
    return objectName;
  }

  public String getField() {
    return field;
  }

  public String getMessage() {
    return message;
  }

}
