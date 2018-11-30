package com.openyich.framework.boot.service;

import org.springframework.data.jpa.domain.Specification;

import com.openyich.framework.boot.service.filter.LongFilter;

/**
 * This class is just a compile - test.
 */
public class ChildEntityQueryService extends QueryService<ChildEntity> {

  static class ChildEntityCriteria extends BaseEntityQueryService.BaseEntityCriteria {
    LongFilter parentId;

    public LongFilter getParentId() {
      return id;
    }
  }

  public Specification<ChildEntity> createSpecification(ChildEntityCriteria criteria) {
    Specification<ChildEntity> specification = Specification.where(null);
    if (criteria.getParentId() != null) {
      specification = specification.and(buildReferringEntitySpecification(criteria.getParentId(),
          ChildEntity_.parent, ParentEntity_.id));
    }
    return specification;
  }

}
