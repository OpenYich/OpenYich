package com.openyich.framework.boot.jpa.mapper;

import org.springframework.data.jpa.domain.Specification;

import com.openyich.framework.boot.jpa.filter.LongFilter;
import com.openyich.framework.boot.jpa.filter.StringFilter;
import com.openyich.framework.boot.jpa.mapper.impl.JpaQueryMapperImpl;

/**
 * This class is just a compile - test.
 */
public class BaseEntityQueryMapperImpl extends JpaQueryMapperImpl<BaseEntity> {

  static class BaseEntityCriteria {
    LongFilter id;
    StringFilter name;

    public LongFilter getId() {
      return id;
    }

    public StringFilter getName() {
      return name;
    }
  }

  public Specification<BaseEntity> createSpecification(BaseEntityCriteria criteria) {
    Specification<BaseEntity> specification = Specification.where(null);
    if (criteria != null) {
      if (criteria.getId() != null) {
        specification = specification.and(buildSpecification(criteria.getId(), "id"));
      }
      if (criteria.getName() != null) {
        specification =
            specification.and(buildStringSpecification(criteria.getName(), "name"));
      }
    }
    return specification;
  }
}
