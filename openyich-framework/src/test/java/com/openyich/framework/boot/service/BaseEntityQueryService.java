package com.openyich.framework.boot.service;

import org.springframework.data.jpa.domain.Specification;

import com.openyich.framework.boot.service.filter.LongFilter;
import com.openyich.framework.boot.service.filter.StringFilter;

/**
 * This class is just a compile - test.
 */
public class BaseEntityQueryService extends QueryService<BaseEntity> {

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
        specification = specification.and(buildSpecification(criteria.getId(), BaseEntity_.id));
      }
      if (criteria.getName() != null) {
        specification =
            specification.and(buildStringSpecification(criteria.getName(), BaseEntity_.name));
      }
    }
    return specification;
  }
}