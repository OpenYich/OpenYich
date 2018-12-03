package com.openyich.framework.boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

@Configuration
@ConditionalOnWebApplication
public class JacksonConfiguration {

  /**
   * Support for Java date and time API.
   * 
   * @return the corresponding Jackson module.
   */
  @Bean
  public JavaTimeModule javaTimeModule() {
    return new JavaTimeModule();
  }

  @Bean
  public Jdk8Module jdk8TimeModule() {
    return new Jdk8Module();
  }

  /**
   * Jackson Afterburner module to speed up serialization/deserialization.
   */
  @Bean
  public AfterburnerModule afterburnerModule() {
    return new AfterburnerModule();
  }

  /**
   * Module for serialization/deserialization of RFC7807 Problem.
   */
  @Bean
  ProblemModule problemModule() {
    return new ProblemModule();
  }

  /**
   * Module for serialization/deserialization of ConstraintViolationProblem.
   */
  @Bean
  ConstraintViolationProblemModule constraintViolationProblemModule() {
    return new ConstraintViolationProblemModule();
  }
}
