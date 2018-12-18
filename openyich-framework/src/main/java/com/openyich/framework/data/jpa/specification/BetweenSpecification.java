package com.openyich.framework.data.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BetweenSpecification<T> extends AbstractSpecification<T> {

  private static final long serialVersionUID = 1L;

  private final String property;
  private final Comparable<Object> lower;
  private final Comparable<Object> upper;

  public BetweenSpecification(String property, Object lower, Object upper) {
    this(property, lower, upper, JoinType.INNER);
  }

  @SuppressWarnings("unchecked")
  public BetweenSpecification(String property, Object lower, Object upper, JoinType joinType) {
    super(joinType);
    this.property = property;
    this.lower = (Comparable<Object>) lower;
    this.upper = (Comparable<Object>) upper;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    From<?, ?> from = getRoot(property, root);
    String field = getProperty(property);
    return criteriaBuilder.between(from.get(field), lower, upper);
  }

}
