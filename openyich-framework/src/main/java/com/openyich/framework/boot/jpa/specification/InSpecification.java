package com.openyich.framework.boot.jpa.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class InSpecification<T> extends AbstractSpecification<T> {

  private static final long serialVersionUID = 1L;

  private String property;
  private List<Object> values;

  public InSpecification(String property, List<Object> values) {
    this(property, values, JoinType.INNER);
  }

  public InSpecification(String property, List<Object> values, JoinType joinType) {
    super(joinType);
    this.property = property;
    this.values = values;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    From<?, ?> from = getRoot(property, root);
    String field = getProperty(property);
    return from.get(field).in(values);
  }

}
