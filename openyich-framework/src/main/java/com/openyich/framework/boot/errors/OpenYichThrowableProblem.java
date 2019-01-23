package com.openyich.framework.boot.errors;

import static org.apiguardian.api.API.Status.STABLE;

import java.util.Map;
import java.util.Objects;

import org.apiguardian.api.API;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

@API(status = STABLE)
public class OpenYichThrowableProblem extends AbstractThrowableProblem {

  private static final long serialVersionUID = 1L;
  protected static final String PARAM = "param";
  private final String code;

  public OpenYichThrowableProblem(String code) {
    this(code, "");
  }

  public OpenYichThrowableProblem(String code, String message) {
    this(code, message, null, Maps.newHashMap());
  }

  public OpenYichThrowableProblem(String code, Throwable cause) {
    this(code, null, cause, Maps.newHashMap());
  }

  public OpenYichThrowableProblem(String code, String message, String... params) {
    this(code, message, null, toParamMap(params));
  }

  public OpenYichThrowableProblem(String code, String message, Map<String, Object> parameters) {
    this(code, message, null, parameters);
  }

  public OpenYichThrowableProblem(String code, String message, Throwable cause, String... params) {
    this(code, message, cause, toParamMap(params));
  }

  public OpenYichThrowableProblem(String code, String message, Throwable cause,
      Map<String, Object> parameters) {
    this(code, Status.INTERNAL_SERVER_ERROR, message, cause, parameters);
  }

  public OpenYichThrowableProblem(String code, Status status) {
    this(code, status, "");
  }

  public OpenYichThrowableProblem(String code, Status status, String message) {
    this(code, status, message, null, Maps.newHashMap());
  }

  public OpenYichThrowableProblem(String code, Status status, Throwable cause) {
    this(code, status, null, cause, Maps.newHashMap());
  }

  public OpenYichThrowableProblem(String code, Status status, String message, String... params) {
    this(code, status, message, null, toParamMap(params));
  }

  public OpenYichThrowableProblem(String code, Status status, String message,
      Map<String, Object> parameters) {
    this(code, status, message, null, parameters);
  }

  public OpenYichThrowableProblem(String code, Status status, String message, Throwable cause,
      String... params) {
    this(code, status, message, cause, toParamMap(params));
  }

  public OpenYichThrowableProblem(String code, Status status, String message, Throwable cause,
      Map<String, Object> parameters) {
    super(null, null, status, toMessage(message, status), null, toProblem(cause, status),
        toProblemParameters(toMessage(message, status), parameters));
    ProblemUtils.setCode(code); // ThreadLocal
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return super.getDetail();
  }

  @Override
  public ThrowableProblem getCause() {
    return super.getCause();
  }

  public String getStackTraceAsString() {
    return Objects.isNull(getCause()) ? null : Throwables.getStackTraceAsString(getCause());
  }

  public String getParametersAsString() {
    return CollectionUtils.isEmpty(getParameters()) ? null : JSON.toJSONString(getParameters());
  }

  protected static String toMessage(String message, Status status) {
    return (StringUtils.hasText(message) ? message : status.getReasonPhrase());
  }

  protected static Map<String, Object> toParamMap(String... params) {
    Map<String, Object> paramMap = Maps.newConcurrentMap();
    if (params != null && params.length > 0) {
      for (int i = 0; i < params.length; i++) {
        paramMap.put(PARAM + i, params[i]);
      }
    }
    return paramMap;
  }

  protected static Map<String, Object> toProblemParameters(String message,
      Map<String, Object> paramMap) {
    Map<String, Object> parameters = Maps.newConcurrentMap();
    if (!CollectionUtils.isEmpty(paramMap)) {
      parameters.put("message", message);
      parameters.put("params", paramMap);
    }
    return parameters;
  }

  protected static ThrowableProblem toProblem(final Throwable throwable, final Status status) {
    if (Objects.isNull(throwable) || Objects.isNull(status)) {
      return null;
    }
    final ThrowableProblem problem = prepare(throwable, status).build();
    problem.setStackTrace(throwable.getStackTrace());
    return problem;
  }

  protected static ProblemBuilder prepare(final Throwable throwable, final Status status) {
    return Problem.builder().withTitle(status.getReasonPhrase()).withStatus(status)
        .withDetail(throwable.getMessage());
  }

}
