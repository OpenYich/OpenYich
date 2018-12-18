package com.openyich.framework.boot.jpa.specification;

import java.io.Serializable;

import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractSpecification<T> implements Specification<T>, Serializable {

  private static final long serialVersionUID = 1L;

  private final JoinType joinType;

  public AbstractSpecification(JoinType joinType) {
    this.joinType = joinType;
  }

  public String getProperty(String property) {
    if (StringUtils.contains(property, ".")) {
      return StringUtils.split(property, ".")[1];
    }
    return property;
  }

  public From<?, ?> getRoot(String property, Root<T> root) {
    if (StringUtils.contains(property, ".")) {
      String joinProperty = StringUtils.split(property, ".")[0];
      return root.join(joinProperty, joinType);
    }
    return root;
  }

  public JoinType getJoinType() {
    return joinType;
  }

}
