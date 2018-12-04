package com.openyich.framework.data.jpa.query;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class ParentEntity extends BaseEntity {
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<ChildEntity> child1s = new HashSet<>();
}
