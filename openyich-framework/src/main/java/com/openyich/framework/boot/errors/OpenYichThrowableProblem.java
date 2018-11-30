package com.openyich.framework.boot.errors;

import java.net.URI;
import java.util.Map;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;

import com.google.common.collect.Maps;

public abstract class OpenYichThrowableProblem extends AbstractThrowableProblem {

  private static final long serialVersionUID = 1L;
  private static final String PARAM = "param";

  public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
  public static final String ERR_VALIDATION = "error.validation";
  public static final URI DEFAULT_TYPE = URI.create("/problem-with-message");

  public OpenYichThrowableProblem(String title, StatusType status) {
    this(title, status, null);
  }

  public OpenYichThrowableProblem(String title, StatusType status, ThrowableProblem cause) {
    super(DEFAULT_TYPE, title, status, null, null, cause);
  }

  public OpenYichThrowableProblem(String title, StatusType status, String message,
      String... params) {
    this(title, status, message, null, toParamMap(params));
  }

  public OpenYichThrowableProblem(String title, StatusType status, String message,
      Map<String, Object> parameters) {
    this(title, status, message, null, parameters);
  }

  public OpenYichThrowableProblem(String title, StatusType status, String message,
      ThrowableProblem cause, String... params) {
    this(title, status, message, cause, toParamMap(params));
  }

  public OpenYichThrowableProblem(String title, StatusType status, String message,
      ThrowableProblem cause, Map<String, Object> parameters) {
    super(DEFAULT_TYPE, title, status, null, null, cause, toProblemParameters(message, parameters));
  }

  public static Map<String, Object> toParamMap(String... params) {
    Map<String, Object> paramMap = Maps.newConcurrentMap();
    if (params != null && params.length > 0) {
      for (int i = 0; i < params.length; i++) {
        paramMap.put(PARAM + i, params[i]);
      }
    }
    return paramMap;
  }

  public static Map<String, Object> toProblemParameters(String message,
      Map<String, Object> paramMap) {
    Map<String, Object> parameters = Maps.newConcurrentMap();
    parameters.put("message", message);
    parameters.put("params", paramMap);
    return parameters;
  }

}
