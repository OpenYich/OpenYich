package com.openyich.framework.boot.errors;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures. The
 * error response follows RFC7807 - Problem Details for HTTP APIs
 * (https://tools.ietf.org/html/rfc7807)
 */
@RestControllerAdvice
public class ExceptionTranslator implements ProblemHandling {

  @ExceptionHandler
  public ResponseEntity<Problem> handleNoSuchElementException(NoSuchElementException ex,
      NativeWebRequest request) {
    Problem problem = Problem.builder().withStatus(Status.NOT_FOUND)
        .with("message", "error.entity-not-found").build();
    return create(ex, problem, request);
  }

}
