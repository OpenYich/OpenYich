package com.openyich.framework.boot.jpa.mapper.impl;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.openyich.framework.boot.jpa.filter.Filter;
import com.openyich.framework.boot.jpa.filter.RangeFilter;
import com.openyich.framework.boot.jpa.filter.StringFilter;

/**
 * Base service for constructing and executing complex queries.
 *
 * @param <T> the type of the entity which is queried.
 */
@Transactional(readOnly = true)
public abstract class JpaQueryMapperImpl<T> {

  /**
   * Helper function to return a specification for filtering on a single field, where equality, and
   * null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param field the JPA static metamodel representing the field.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <X> Specification<T> buildSpecification(final Filter<X> filter,
      final SingularAttribute<? super T, X> field) {
    return buildSpecification(filter, root -> root.get(field));
  }

  /**
   * Helper function to return a specification for filtering on a single field, where equality, and
   * null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param field the JPA static metamodel representing the field.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <X> Specification<T> buildSpecification(final Filter<X> filter, final String field) {
    return buildSpecification(filter, root -> root.get(field));
  }

  /**
   * Helper function to return a specification for filtering on a single field, where equality, and
   * null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param metaclassFunction the function, which navigates from the current entity to a column, for
   *        which the filter applies.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <X> Specification<T> buildSpecification(final Filter<X> filter,
      Function<Root<T>, Expression<X>> metaclassFunction) {
    if (filter.getEquals() != null) {
      return equalsSpecification(metaclassFunction, filter.getEquals());
    } else if (filter.getIn() != null) {
      return valueIn(metaclassFunction, filter.getIn());
    } else if (filter.getSpecified() != null) {
      return byFieldSpecified(metaclassFunction, filter.getSpecified());
    }
    return null;
  }

  /**
   * Helper function to return a specification for filtering on a {@link String} field, where
   * equality, containment, and null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param field the JPA static metamodel representing the field.
   * @return a Specification
   */
  protected Specification<T> buildStringSpecification(final StringFilter filter,
      final SingularAttribute<? super T, String> field) {
    return buildSpecification(filter, root -> root.get(field));
  }

  /**
   * Helper function to return a specification for filtering on a {@link String} field, where
   * equality, containment, and null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param field the JPA static metamodel representing the field.
   * @return a Specification
   */
  protected Specification<T> buildStringSpecification(final StringFilter filter,
      final String field) {
    return buildSpecification(filter, root -> root.get(field));
  }

  /**
   * Helper function to return a specification for filtering on a {@link String} field, where
   * equality, containment, and null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param metaclassFunction lambda, which based on a Root&lt;ENTITY&gt; returns Expression -
   *        basicaly picks a column
   * @return a Specification
   */
  protected Specification<T> buildSpecification(final StringFilter filter,
      Function<Root<T>, Expression<String>> metaclassFunction) {
    if (filter.getEquals() != null) {
      return equalsSpecification(metaclassFunction, filter.getEquals());
    } else if (filter.getIn() != null) {
      return valueIn(metaclassFunction, filter.getIn());
    } else if (filter.getContains() != null) {
      return likeSpecification(metaclassFunction, filter.getContains());
    } else if (filter.getSpecified() != null) {
      return byFieldSpecified(metaclassFunction, filter.getSpecified());
    }
    return null;
  }

  /**
   * Helper function to return a specification for filtering on a single {@link Comparable}, where
   * equality, less than, greater than and less-than-or-equal-to and greater-than-or-equal-to and
   * null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param field the JPA static metamodel representing the field.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <X extends Comparable<? super X>> Specification<T> buildRangeSpecification(
      final RangeFilter<X> filter, final SingularAttribute<? super T, X> field) {
    return buildSpecification(filter, root -> root.get(field));
  }

  /**
   * Helper function to return a specification for filtering on a single {@link Comparable}, where
   * equality, less than, greater than and less-than-or-equal-to and greater-than-or-equal-to and
   * null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param field the JPA static metamodel representing the field.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <X extends Comparable<? super X>> Specification<T> buildRangeSpecification(
      final RangeFilter<X> filter, final String field) {
    return buildSpecification(filter, root -> root.get(field));
  }

  /**
   * Helper function to return a specification for filtering on a single {@link Comparable}, where
   * equality, less than, greater than and less-than-or-equal-to and greater-than-or-equal-to and
   * null/non-null conditions are supported.
   *
   * @param filter the individual attribute filter coming from the frontend.
   * @param metaclassFunction lambda, which based on a Root&lt;ENTITY&gt; returns Expression -
   *        basicaly picks a column
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <X extends Comparable<? super X>> Specification<T> buildSpecification(
      final RangeFilter<X> filter, Function<Root<T>, Expression<X>> metaclassFunction) {
    if (filter.getEquals() != null) {
      return equalsSpecification(metaclassFunction, filter.getEquals());
    } else if (filter.getIn() != null) {
      return valueIn(metaclassFunction, filter.getIn());
    }

    Specification<T> result = Specification.where(null);
    if (filter.getSpecified() != null) {
      result = result.and(byFieldSpecified(metaclassFunction, filter.getSpecified()));
    }
    if (filter.getGreaterThan() != null) {
      result = result.and(greaterThan(metaclassFunction, filter.getGreaterThan()));
    }
    if (filter.getGreaterOrEqualThan() != null) {
      result = result.and(greaterThanOrEqualTo(metaclassFunction, filter.getGreaterOrEqualThan()));
    }
    if (filter.getLessThan() != null) {
      result = result.and(lessThan(metaclassFunction, filter.getLessThan()));
    }
    if (filter.getLessOrEqualThan() != null) {
      result = result.and(lessThanOrEqualTo(metaclassFunction, filter.getLessOrEqualThan()));
    }
    return result;
  }

  /**
   * Helper function to return a specification for filtering on one-to-one or many-to-one reference.
   * Usage: <pre>
   *   Specification&lt;Employee&gt; specByProjectId = buildReferringEntitySpecification(criteria.getProjectId(),
   * Employee_.project, Project_.id);
   *   Specification&lt;Employee&gt; specByProjectName = buildReferringEntitySpecification(criteria.getProjectName(),
   * Employee_.project, Project_.name);
   * </pre>
   *
   * @param filter the filter object which contains a value, which needs to match or a flag if
   *        nullness is checked.
   * @param reference the attribute of the static metamodel for the referring entity.
   * @param valueField the attribute of the static metamodel of the referred entity, where the
   *        equality should be checked.
   * @param <OTHER> The type of the referenced entity.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <OTHER, X> Specification<T> buildReferringEntitySpecification(final Filter<X> filter,
      final SingularAttribute<? super T, OTHER> reference,
      final SingularAttribute<? super OTHER, X> valueField) {
    return buildSpecification(filter, root -> root.get(reference).get(valueField));
  }

  /**
   * Helper function to return a specification for filtering on one-to-one or many-to-one reference.
   * Usage: <pre>
   *   Specification&lt;Employee&gt; specByProjectId = buildReferringEntitySpecification(criteria.getProjectId(),
   * Employee_.project, Project_.id);
   *   Specification&lt;Employee&gt; specByProjectName = buildReferringEntitySpecification(criteria.getProjectName(),
   * Employee_.project, Project_.name);
   * </pre>
   *
   * @param filter the filter object which contains a value, which needs to match or a flag if
   *        nullness is checked.
   * @param reference the attribute of the static metamodel for the referring entity.
   * @param valueField the attribute of the static metamodel of the referred entity, where the
   *        equality should be checked.
   * @param <OTHER> The type of the referenced entity.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <OTHER, X> Specification<T> buildReferringEntitySpecification(final Filter<X> filter,
      final String reference, final String valueField) {
    return buildSpecification(filter, root -> root.get(reference).get(valueField));
  }

  /**
   * Helper function to return a specification for filtering on one-to-many or many-to-many
   * reference. Usage: <pre>
   *   Specification&lt;Employee&gt; specByEmployeeId = buildReferringEntitySpecification(criteria.getEmployeId(),
   * Project_.employees, Employee_.id);
   *   Specification&lt;Employee&gt; specByEmployeeName = buildReferringEntitySpecification(criteria.getEmployeName(),
   * Project_.project, Project_.name);
   * </pre>
   *
   * @param filter the filter object which contains a value, which needs to match or a flag if
   *        emptiness is checked.
   * @param reference the attribute of the static metamodel for the referring entity.
   * @param valueField the attribute of the static metamodel of the referred entity, where the
   *        equality should be checked.
   * @param <OTHER> The type of the referenced entity.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <OTHER, X> Specification<T> buildReferringEntitySpecification(final Filter<X> filter,
      final SetAttribute<T, OTHER> reference, final SingularAttribute<OTHER, X> valueField) {
    return buildReferringEntitySpecification(filter, root -> root.join(reference),
        entity -> entity.get(valueField));
  }

  /**
   * Helper function to return a specification for filtering on one-to-many or many-to-many
   * reference. Usage: <pre>
   *   Specification&lt;Employee&gt; specByEmployeeId = buildReferringEntitySpecification(criteria.getEmployeId(),
   * Project_.employees, Employee_.id);
   *   Specification&lt;Employee&gt; specByEmployeeName = buildReferringEntitySpecification(criteria.getEmployeName(),
   * Project_.project, Project_.name);
   * </pre>
   *
   * @param filter the filter object which contains a value, which needs to match or a flag if
   *        emptiness is checked.
   * @param reference the attribute of the static metamodel for the referring entity.
   * @param valueField the attribute of the static metamodel of the referred entity, where the
   *        equality should be checked.
   * @param <OTHER> The type of the referenced entity.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <OTHER, X> Specification<T> buildReferringEntitySpecification(final Filter<X> filter,
      final SetAttribute<T, OTHER> reference, final String valueField) {
    return buildReferringEntitySpecification(filter, root -> root.join(reference),
        entity -> entity.get(valueField));
  }

  /**
   * Helper function to return a specification for filtering on one-to-many or many-to-many
   * reference. Usage: <pre>
   *   Specification&lt;Employee&gt; specByEmployeeId = buildReferringEntitySpecification(
   *          criteria.getEmployeId(),
   *          root -&gt; root.get(Project_.company).join(Company_.employees),
   *          entity -&gt; entity.get(Employee_.id));
   *   Specification&lt;Employee&gt; specByProjectName = buildReferringEntitySpecification(
   *          criteria.getProjectName(),
   *          root -&gt; root.get(Project_.project)
   *          entity -&gt; entity.get(Project_.name));
   * </pre>
   *
   * @param filter the filter object which contains a value, which needs to match or a flag if
   *        emptiness is checked.
   * @param functionToEntity the function, which joins he current entity to the entity set, on which
   *        the filtering is applied.
   * @param entityToColumn the function, which of the static metamodel of the referred entity, where
   *        the equality should be checked.
   * @param <OTHER> The type of the referenced entity.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <OTHER, MISC, X> Specification<T> buildReferringEntitySpecification(
      final Filter<X> filter, Function<Root<T>, SetJoin<MISC, OTHER>> functionToEntity,
      Function<SetJoin<MISC, OTHER>, Expression<X>> entityToColumn) {
    if (filter.getEquals() != null) {
      return equalsSpecification(functionToEntity.andThen(entityToColumn), filter.getEquals());
    } else if (filter.getSpecified() != null) {
      // Interestingly, 'functionToEntity' doesn't work, we need the longer lambda formula
      return byFieldSpecified(root -> functionToEntity.apply(root), filter.getSpecified());
    }
    return null;
  }

  /**
   * Helper function to return a specification for filtering on one-to-many or many-to-many
   * reference. Where equality, less than, greater than and less-than-or-equal-to and
   * greater-than-or-equal-to and null/non-null conditions are supported. Usage: <pre>
   *   Specification&lt;Employee&gt; specByEmployeeId = buildReferringEntitySpecification(criteria.getEmployeId(),
   * Project_.employees, Employee_.id);
   *   Specification&lt;Employee&gt; specByEmployeeName = buildReferringEntitySpecification(criteria.getEmployeName(),
   * Project_.project, Project_.name);
   * </pre>
   *
   * @param filter the filter object which contains a value, which needs to match or a flag if
   *        emptiness is checked.
   * @param reference the attribute of the static metamodel for the referring entity.
   * @param valueField the attribute of the static metamodel of the referred entity, where the
   *        equality should be checked.
   * @param <OTHER> The type of the referenced entity.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <OTHER, X extends Comparable<? super X>> Specification<T> buildReferringEntitySpecification(
      final RangeFilter<X> filter, final SetAttribute<T, OTHER> reference,
      final SingularAttribute<OTHER, X> valueField) {
    return buildReferringEntitySpecification(filter, root -> root.join(reference),
        entity -> entity.get(valueField));
  }

  /**
   * Helper function to return a specification for filtering on one-to-many or many-to-many
   * reference. Where equality, less than, greater than and less-than-or-equal-to and
   * greater-than-or-equal-to and null/non-null conditions are supported. Usage: <pre>
   *   Specification&lt;Employee&gt; specByEmployeeId = buildReferringEntitySpecification(criteria.getEmployeId(),
   * Project_.employees, Employee_.id);
   *   Specification&lt;Employee&gt; specByEmployeeName = buildReferringEntitySpecification(criteria.getEmployeName(),
   * Project_.project, Project_.name);
   * </pre>
   *
   * @param filter the filter object which contains a value, which needs to match or a flag if
   *        emptiness is checked.
   * @param reference the attribute of the static metamodel for the referring entity.
   * @param valueField the attribute of the static metamodel of the referred entity, where the
   *        equality should be checked.
   * @param <OTHER> The type of the referenced entity.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <OTHER, X extends Comparable<? super X>> Specification<T> buildReferringEntitySpecification(
      final RangeFilter<X> filter, final SetAttribute<T, OTHER> reference,
      final String valueField) {
    return buildReferringEntitySpecification(filter, root -> root.join(reference),
        entity -> entity.get(valueField));
  }

  /**
   * Helper function to return a specification for filtering on one-to-many or many-to-many
   * reference. Where equality, less than, greater than and less-than-or-equal-to and
   * greater-than-or-equal-to and null/non-null conditions are supported. Usage: <pre><code>
   *   Specification&lt;Employee&gt; specByEmployeeId = buildReferringEntitySpecification(
   *          criteria.getEmployeId(),
   *          root -&gt; root.get(Project_.company).join(Company_.employees),
   *          entity -&gt; entity.get(Employee_.id));
   *   Specification&lt;Employee&gt; specByProjectName = buildReferringEntitySpecification(
   *          criteria.getProjectName(),
   *          root -&gt; root.get(Project_.project)
   *          entity -&gt; entity.get(Project_.name));
   * </code> </pre>
   *
   * @param filter the filter object which contains a value, which needs to match or a flag if
   *        emptiness is checked.
   * @param functionToEntity the function, which joins he current entity to the entity set, on which
   *        the filtering is applied.
   * @param entityToColumn the function, which of the static metamodel of the referred entity, where
   *        the equality should be checked.
   * @param <OTHER> The type of the referenced entity.
   * @param <MISC> The type of the entity which is the last before the OTHER in the chain.
   * @param <X> The type of the attribute which is filtered.
   * @return a Specification
   */
  protected <OTHER, MISC, X extends Comparable<? super X>> Specification<T> buildReferringEntitySpecification(
      final RangeFilter<X> filter, Function<Root<T>, SetJoin<MISC, OTHER>> functionToEntity,
      Function<SetJoin<MISC, OTHER>, Expression<X>> entityToColumn) {

    Function<Root<T>, Expression<X>> fused = functionToEntity.andThen(entityToColumn);
    if (filter.getEquals() != null) {
      return equalsSpecification(fused, filter.getEquals());
    } else if (filter.getIn() != null) {
      return valueIn(fused, filter.getIn());
    }
    Specification<T> result = Specification.where(null);
    if (filter.getSpecified() != null) {
      // Interestingly, 'functionToEntity' doesn't work, we need the longer lambda formula
      result =
          result.and(byFieldSpecified(root -> functionToEntity.apply(root), filter.getSpecified()));
    }
    if (filter.getGreaterThan() != null) {
      result = result.and(greaterThan(fused, filter.getGreaterThan()));
    }
    if (filter.getGreaterOrEqualThan() != null) {
      result = result.and(greaterThanOrEqualTo(fused, filter.getGreaterOrEqualThan()));
    }
    if (filter.getLessThan() != null) {
      result = result.and(lessThan(fused, filter.getLessThan()));
    }
    if (filter.getLessOrEqualThan() != null) {
      result = result.and(lessThanOrEqualTo(fused, filter.getLessOrEqualThan()));
    }
    return result;
  }

  /**
   * Generic method, which based on a Root&lt;ENTITY&gt; returns an Expression which type is the
   * same as the given 'value' type.
   * 
   * @param metaclassFunction function which returns the column which is used for filtering.
   * @param value the actual value to filter for.
   * @return a Specification.
   */
  protected <X> Specification<T> equalsSpecification(
      Function<Root<T>, Expression<X>> metaclassFunction, final X value) {
    return (root, query, builder) -> builder.equal(metaclassFunction.apply(root), value);
  }

  protected Specification<T> likeSpecification(
      Function<Root<T>, Expression<String>> metaclassFunction, final String value) {
    return (root, query, builder) -> builder.like(metaclassFunction.apply(root),
        likeQueryWrapper(value));
  }

  protected <X> Specification<T> byFieldSpecified(
      Function<Root<T>, Expression<X>> metaclassFunction, final boolean specified) {
    return specified
        ? (root, query, builder) -> builder.isNotNull(metaclassFunction.apply(root))
        : (root, query, builder) -> builder.isNull(metaclassFunction.apply(root));
  }

  protected <X> Specification<T> byFieldEmptiness(
      Function<Root<T>, Expression<Set<X>>> metaclassFunction, final boolean specified) {
    return specified
        ? (root, query, builder) -> builder.isNotEmpty(metaclassFunction.apply(root))
        : (root, query, builder) -> builder.isEmpty(metaclassFunction.apply(root));
  }

  protected <X> Specification<T> valueIn(Function<Root<T>, Expression<X>> metaclassFunction,
      final Collection<X> values) {
    return (root, query, builder) -> {
      In<X> in = builder.in(metaclassFunction.apply(root));
      for (X value : values) {
        in = in.value(value);
      }
      return in;
    };
  }

  protected <X extends Comparable<? super X>> Specification<T> greaterThanOrEqualTo(
      Function<Root<T>, Expression<X>> metaclassFunction, final X value) {
    return (root, query, builder) -> builder.greaterThanOrEqualTo(metaclassFunction.apply(root),
        value);
  }

  protected <X extends Comparable<? super X>> Specification<T> greaterThan(
      Function<Root<T>, Expression<X>> metaclassFunction, final X value) {
    return (root, query, builder) -> builder.greaterThan(metaclassFunction.apply(root), value);
  }

  protected <X extends Comparable<? super X>> Specification<T> lessThanOrEqualTo(
      Function<Root<T>, Expression<X>> metaclassFunction, final X value) {
    return (root, query, builder) -> builder.lessThanOrEqualTo(metaclassFunction.apply(root),
        value);
  }

  protected <X extends Comparable<? super X>> Specification<T> lessThan(
      Function<Root<T>, Expression<X>> metaclassFunction, final X value) {
    return (root, query, builder) -> builder.lessThan(metaclassFunction.apply(root), value);
  }

  protected String likeQueryWrapper(final String txt) {
    return "%" + txt + '%';
  }

}
