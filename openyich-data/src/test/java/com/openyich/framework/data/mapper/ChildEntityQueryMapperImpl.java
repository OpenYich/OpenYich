package com.openyich.framework.data.mapper;

import org.springframework.data.jpa.domain.Specification;

import com.openyich.framework.data.filter.LongFilter;
import com.openyich.framework.data.mapper.impl.AbastractQueryMapperImpl;

/**
 * This class is just a compile - test.
 */
public class ChildEntityQueryMapperImpl extends AbastractQueryMapperImpl<ChildEntity> {

  static class ChildEntityCriteria extends BaseEntityQueryMapperImpl.BaseEntityCriteria {
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
