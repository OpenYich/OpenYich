package com.openyich.framework.boot.jpa.specification;

import static javax.persistence.criteria.Predicate.BooleanOperator.AND;
import static javax.persistence.criteria.Predicate.BooleanOperator.OR;

public class Specifications {

  public static <T> PredicateBuilder<T> and(Class<T> clazz) {
    return new PredicateBuilder<>(AND);
  }

  public static <T> PredicateBuilder<T> or(Class<T> clazz) {
    return new PredicateBuilder<>(OR);
  }
  
}
