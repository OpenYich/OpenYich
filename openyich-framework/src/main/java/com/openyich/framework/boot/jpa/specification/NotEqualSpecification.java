package com.openyich.framework.boot.jpa.specification;

import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.CollectionUtils;

public class NotEqualSpecification<T> extends AbstractSpecification<T> {

  private static final long serialVersionUID = 1L;

  private final String property;
  private final List<Object> values;

  public NotEqualSpecification(String property, List<Object> values) {
    this(property, values, JoinType.INNER);
  }

  public NotEqualSpecification(String property, List<Object> values, JoinType joinType) {
    super(joinType);
    this.property = property;
    this.values = values;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    From<?, ?> from = getRoot(property, root);
    String field = getProperty(property);

    if (CollectionUtils.isEmpty(values)) {
      return criteriaBuilder.isNotNull(from.get(field));
    }

    if (values.size() == 1) {
      return getPredicate(from, criteriaBuilder, field, values.get(0));
    }

    Predicate[] predicates = new Predicate[values.size()];
    for (int i = 0; i < values.size(); i++) {
      predicates[i] = getPredicate(from, criteriaBuilder, field, values.get(i));
    }
    return criteriaBuilder.or(predicates);
  }

  private Predicate getPredicate(From<?, ?> from, CriteriaBuilder criteriaBuilder, String field,
      Object value) {
    return Objects.isNull(value)
        ? criteriaBuilder.isNotNull(from.get(field))
        : criteriaBuilder.notEqual(from.get(field), value);
  }

}
