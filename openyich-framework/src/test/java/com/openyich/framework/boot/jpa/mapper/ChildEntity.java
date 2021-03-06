package com.openyich.framework.boot.jpa.mapper;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ChildEntity extends BaseEntity {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private ParentEntity parent;
}
