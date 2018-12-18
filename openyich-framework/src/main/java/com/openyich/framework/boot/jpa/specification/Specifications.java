package com.openyich.framework.boot.jpa.specification;

import static javax.persistence.criteria.Predicate.BooleanOperator.AND;
import static javax.persistence.criteria.Predicate.BooleanOperator.OR;

public class Specifications {

  public static <T> PredicateBuilder<T> and() {
    return new PredicateBuilder<>(AND);
  }

  public static <T> PredicateBuilder<T> or() {
    return new PredicateBuilder<>(OR);
  }
  
}
