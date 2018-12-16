package com.openyich.framework.data.jpa.mapper;

import org.springframework.data.jpa.domain.Specification;

import com.openyich.framework.data.commons.filter.LongFilter;
import com.openyich.framework.data.jpa.mapper.impl.JpaQueryMapperImpl;

/**
 * This class is just a compile - test.
 */
public class ChildEntityQueryMapperImpl extends JpaQueryMapperImpl<ChildEntity> {

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
          "parent", "id"));
    }
    return specification;
  }

}
