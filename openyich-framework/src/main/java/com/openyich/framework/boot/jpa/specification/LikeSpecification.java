package com.openyich.framework.boot.jpa.specification;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LikeSpecification<T> extends AbstractSpecification<T> {

  private static final long serialVersionUID = 1L;

  private final String property;
  private final List<String> patterns;

  public LikeSpecification(String property, List<String> patterns) {
    this(property, patterns, JoinType.INNER);
  }

  public LikeSpecification(String property, List<String> patterns, JoinType joinType) {
    super(joinType);
    this.property = property;
    this.patterns = patterns;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    From<?, ?> from = getRoot(property, root);
    String field = getProperty(property);
    
    Predicate[] predicates = new Predicate[patterns.size()];
    for (int i = 0; i < patterns.size(); i++) {
      predicates[i] = criteriaBuilder.like(from.get(field), patterns.get(i));
    }
    
    return criteriaBuilder.or(predicates);
  }
  
}
