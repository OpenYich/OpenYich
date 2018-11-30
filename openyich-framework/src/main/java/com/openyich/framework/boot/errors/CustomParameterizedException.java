package com.openyich.framework.boot.errors;

import static org.zalando.problem.Status.BAD_REQUEST;

import java.util.Map;

import org.zalando.problem.ThrowableProblem;

/**
 * Custom, parameterized exception, which can be translated on the client side. For example:
 *
 * <pre>
 * throw new CustomParameterizedException(&quot;myCustomError&quot;, &quot;hello&quot;, &quot;world&quot;);
 * </pre>
 *
 * Can be translated with:
 *
 * <pre>
 * "error.myCustomError" :  "The server says {{param0}} to {{param1}}"
 * </pre>
 */
public class CustomParameterizedException extends OpenYichThrowableProblem {

  private static final long serialVersionUID = 1L;

  private static final String DEFAULT_TITLE = "Parameterized Exception";

  public CustomParameterizedException(String message, Map<String, Object> parameters) {
    super(DEFAULT_TITLE, BAD_REQUEST, message, parameters);
  }

  public CustomParameterizedException(String message, String... params) {
    super(DEFAULT_TITLE, BAD_REQUEST, message, params);
  }

  public CustomParameterizedException(String message, ThrowableProblem cause,
      Map<String, Object> parameters) {
    super(DEFAULT_TITLE, BAD_REQUEST, message, cause, parameters);
  }

  public CustomParameterizedException(String message, ThrowableProblem cause, String... params) {
    super(DEFAULT_TITLE, BAD_REQUEST, message, cause, params);
  }

}
