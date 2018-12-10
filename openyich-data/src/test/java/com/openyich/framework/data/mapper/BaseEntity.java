package com.openyich.framework.data.mapper;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue
  Long id;

  @Column(nullable = false)
  String name;
}
