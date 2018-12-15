package com.openyich.framework.data.jpa.mapper;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "ParentEntity_")
@StaticMetamodel(ParentEntity.class)
public class ParentEntity_ extends BaseEntity_ {
  public static volatile SingularAttribute<ParentEntity, Set<ChildEntity>> child1s;
}
