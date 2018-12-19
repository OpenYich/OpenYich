package com.openyich.framework.boot.jpa.specification;

import static javax.persistence.criteria.Predicate.BooleanOperator.OR;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

public class PredicateBuilder<T> {

  private final Predicate.BooleanOperator operator;
  private List<Specification<T>> specifications;

  public PredicateBuilder(Predicate.BooleanOperator operator) {
    this.operator = operator;
    this.specifications = Lists.newArrayList();
  }

  public PredicateBuilder<T> equal(String property, List<Object> values) {
    return this.predicate(new EqualSpecification<>(property, values));
  }

  public PredicateBuilder<T> equal(String property, Object... values) {
    return this.predicate(new EqualSpecification<>(property, Arrays.asList(values)));
  }

  public PredicateBuilder<T> equal(String property, JoinType joinType, List<Object> values) {
    return this.predicate(new EqualSpecification<>(property, values, joinType));
  }

  public PredicateBuilder<T> equal(String property, JoinType joinType, Object... values) {
    return this.predicate(new EqualSpecification<>(property, Arrays.asList(values), joinType));
  }

  public PredicateBuilder<T> equal(boolean condition, String property, List<Object> values) {
    return this.predicate(condition, new EqualSpecification<>(property, values));
  }

  public PredicateBuilder<T> equal(boolean condition, String property, Object... values) {
    return this.predicate(condition, new EqualSpecification<>(property, Arrays.asList(values)));
  }

  public PredicateBuilder<T> equal(boolean condition, String property, JoinType joinType,
      List<Object> values) {
    return this.predicate(condition, new EqualSpecification<>(property, values, joinType));
  }

  public PredicateBuilder<T> equal(boolean condition, String property, JoinType joinType,
      Object... values) {
    return this.predicate(condition,
        new EqualSpecification<>(property, Arrays.asList(values), joinType));
  }

  public PredicateBuilder<T> notEqual(String property, List<Object> values) {
    return this.predicate(new NotEqualSpecification<>(property, values));
  }

  public PredicateBuilder<T> notEqual(String property, Object... values) {
    return this.predicate(new NotEqualSpecification<>(property, Arrays.asList(values)));
  }

  public PredicateBuilder<T> notEqual(String property, JoinType joinType, List<Object> values) {
    return this.predicate(new NotEqualSpecification<>(property, values, joinType));
  }

  public PredicateBuilder<T> notEqual(String property, JoinType joinType, Object... values) {
    return this.predicate(new NotEqualSpecification<>(property, Arrays.asList(values), joinType));
  }

  public PredicateBuilder<T> notEqual(boolean condition, String property, List<Object> values) {
    return this.predicate(condition, new NotEqualSpecification<>(property, values));
  }

  public PredicateBuilder<T> notEqual(boolean condition, String property, Object... values) {
    return this.predicate(condition, new NotEqualSpecification<>(property, Arrays.asList(values)));
  }

  public PredicateBuilder<T> notEqual(boolean condition, String property, JoinType joinType,
      List<Object> values) {
    return this.predicate(condition, new NotEqualSpecification<>(property, values, joinType));
  }

  public PredicateBuilder<T> notEqual(boolean condition, String property, JoinType joinType,
      Object... values) {
    return this.predicate(condition,
        new NotEqualSpecification<>(property, Arrays.asList(values), joinType));
  }

  public PredicateBuilder<T> greaterThan(String property, Comparable<?> compare) {
    return this.predicate(new GreaterThanSpecification<>(property, compare));
  }

  public PredicateBuilder<T> greaterThan(String property, Comparable<?> compare,
      JoinType joinType) {
    return this.predicate(new GreaterThanSpecification<>(property, compare, joinType));
  }

  public PredicateBuilder<T> greaterThan(boolean condition, String property,
      Comparable<?> compare) {
    return this.predicate(condition, new GreaterThanSpecification<>(property, compare));
  }

  public PredicateBuilder<T> greaterThan(boolean condition, String property, Comparable<?> compare,
      JoinType joinType) {
    return this.predicate(condition, new GreaterThanSpecification<>(property, compare, joinType));
  }

  public PredicateBuilder<T> greaterThanOrEqualTo(String property, Comparable<?> compare) {
    return this.predicate(new GreaterThanOrEqualToSpecification<>(property, compare));
  }

  public PredicateBuilder<T> greaterThanOrEqualTo(String property, Comparable<?> compare,
      JoinType joinType) {
    return this.predicate(new GreaterThanOrEqualToSpecification<>(property, compare, joinType));
  }

  public PredicateBuilder<T> greaterThanOrEqualTo(boolean condition, String property,
      Comparable<?> compare) {
    return this.predicate(condition, new GreaterThanOrEqualToSpecification<>(property, compare));
  }

  public PredicateBuilder<T> greaterThanOrEqualTo(boolean condition, String property,
      Comparable<?> compare, JoinType joinType) {
    return this.predicate(condition,
        new GreaterThanOrEqualToSpecification<>(property, compare, joinType));
  }

  public PredicateBuilder<T> lessThan(String property, Comparable<?> compare) {
    return this.predicate(new LessThanSpecification<>(property, compare));
  }

  public PredicateBuilder<T> lessThan(String property, Comparable<?> compare, JoinType joinType) {
    return this.predicate(new LessThanSpecification<>(property, compare, joinType));
  }

  public PredicateBuilder<T> lessThan(boolean condition, String property, Comparable<?> compare) {
    return this.predicate(condition, new LessThanSpecification<>(property, compare));
  }

  public PredicateBuilder<T> lessThan(boolean condition, String property, Comparable<?> compare,
      JoinType joinType) {
    return this.predicate(condition, new LessThanSpecification<>(property, compare, joinType));
  }

  public PredicateBuilder<T> lessThanOrEqualTo(String property, Comparable<?> compare) {
    return this.predicate(new LessThanOrEqualToSpecification<>(property, compare));
  }

  public PredicateBuilder<T> lessThanOrEqualTo(String property, Comparable<?> compare,
      JoinType joinType) {
    return this.predicate(new LessThanOrEqualToSpecification<>(property, compare, joinType));
  }

  public PredicateBuilder<T> lessThanOrEqualTo(boolean condition, String property,
      Comparable<?> compare) {
    return this.predicate(condition, new LessThanOrEqualToSpecification<>(property, compare));
  }

  public PredicateBuilder<T> lessThanOrEqualTo(boolean condition, String property,
      Comparable<?> compare, JoinType joinType) {
    return this.predicate(condition,
        new LessThanOrEqualToSpecification<>(property, compare, joinType));
  }

  public PredicateBuilder<T> between(String property, Object lower, Object upper) {
    return this.predicate(new BetweenSpecification<>(property, lower, upper));
  }

  public PredicateBuilder<T> between(String property, Object lower, Object upper,
      JoinType joinType) {
    return this.predicate(new BetweenSpecification<>(property, lower, upper, joinType));
  }

  public PredicateBuilder<T> between(boolean condition, String property, Object lower,
      Object upper) {
    return this.predicate(condition, new BetweenSpecification<>(property, lower, upper));
  }

  public PredicateBuilder<T> between(boolean condition, String property, Object lower, Object upper,
      JoinType joinType) {
    return this.predicate(condition, new BetweenSpecification<>(property, lower, upper, joinType));
  }

  public PredicateBuilder<T> like(String property, List<String> patterns) {
    return this.predicate(new LikeSpecification<T>(property, patterns));
  }

  public PredicateBuilder<T> like(String property, String... patterns) {
    return this.predicate(new LikeSpecification<T>(property, Arrays.asList(patterns)));
  }

  public PredicateBuilder<T> like(String property, JoinType joinType, List<String> patterns) {
    return this.predicate(new LikeSpecification<T>(property, patterns, joinType));
  }

  public PredicateBuilder<T> like(String property, JoinType joinType, String... patterns) {
    return this.predicate(new LikeSpecification<T>(property, Arrays.asList(patterns), joinType));
  }

  public PredicateBuilder<T> like(boolean condition, String property, List<String> patterns) {
    return this.predicate(condition, new LikeSpecification<T>(property, patterns));
  }

  public PredicateBuilder<T> like(boolean condition, String property, String... patterns) {
    return this.predicate(condition, new LikeSpecification<T>(property, Arrays.asList(patterns)));
  }

  public PredicateBuilder<T> like(boolean condition, String property, JoinType joinType,
      List<String> patterns) {
    return this.predicate(condition, new LikeSpecification<T>(property, patterns, joinType));
  }

  public PredicateBuilder<T> like(boolean condition, String property, JoinType joinType,
      String... patterns) {
    return this.predicate(condition,
        new LikeSpecification<T>(property, Arrays.asList(patterns), joinType));
  }

  public PredicateBuilder<T> notLike(String property, List<String> patterns) {
    return this.predicate(new NotLikeSpecification<T>(property, patterns));
  }

  public PredicateBuilder<T> notLike(String property, String... patterns) {
    return this.predicate(new NotLikeSpecification<T>(property, Arrays.asList(patterns)));
  }

  public PredicateBuilder<T> notLike(String property, JoinType joinType, List<String> patterns) {
    return this.predicate(new NotLikeSpecification<T>(property, patterns, joinType));
  }

  public PredicateBuilder<T> notLike(String property, JoinType joinType, String... patterns) {
    return this.predicate(new NotLikeSpecification<T>(property, Arrays.asList(patterns), joinType));
  }

  public PredicateBuilder<T> notLike(boolean condition, String property, List<String> patterns) {
    return this.predicate(condition, new NotLikeSpecification<T>(property, patterns));
  }

  public PredicateBuilder<T> notLike(boolean condition, String property, String... patterns) {
    return this.predicate(condition,
        new NotLikeSpecification<T>(property, Arrays.asList(patterns)));
  }

  public PredicateBuilder<T> notLike(boolean condition, String property, JoinType joinType,
      List<String> patterns) {
    return this.predicate(condition, new NotLikeSpecification<T>(property, patterns, joinType));
  }

  public PredicateBuilder<T> notLike(boolean condition, String property, JoinType joinType,
      String... patterns) {
    return this.predicate(condition,
        new NotLikeSpecification<T>(property, Arrays.asList(patterns), joinType));
  }

  public PredicateBuilder<T> in(String property, List<Object> values) {
    return this.predicate(new InSpecification<T>(property, values));
  }

  public PredicateBuilder<T> in(String property, Object... values) {
    return this.predicate(new InSpecification<T>(property, Arrays.asList(values)));
  }

  public PredicateBuilder<T> in(String property, JoinType joinType, List<Object> values) {
    return this.predicate(new InSpecification<T>(property, values, joinType));
  }

  public PredicateBuilder<T> in(String property, JoinType joinType, Object... values) {
    return this.predicate(new InSpecification<T>(property, Arrays.asList(values), joinType));
  }

  public PredicateBuilder<T> in(boolean condition, String property, List<Object> values) {
    return this.predicate(condition, new InSpecification<T>(property, values));
  }

  public PredicateBuilder<T> in(boolean condition, String property, Object... values) {
    return this.predicate(condition, new InSpecification<T>(property, Arrays.asList(values)));
  }

  public PredicateBuilder<T> in(boolean condition, String property, JoinType joinType,
      List<Object> values) {
    return this.predicate(condition, new InSpecification<T>(property, values, joinType));
  }

  public PredicateBuilder<T> in(boolean condition, String property, JoinType joinType,
      Object... values) {
    return this.predicate(condition,
        new InSpecification<T>(property, Arrays.asList(values), joinType));
  }

  public PredicateBuilder<T> notIn(String property, List<Object> values) {
    return this.predicate(new NotInSpecification<T>(property, values));
  }

  public PredicateBuilder<T> notIn(String property, Object... values) {
    return this.predicate(new NotInSpecification<T>(property, Arrays.asList(values)));
  }

  public PredicateBuilder<T> notIn(String property, JoinType joinType, List<Object> values) {
    return this.predicate(new NotInSpecification<T>(property, values, joinType));
  }

  public PredicateBuilder<T> notIn(String property, JoinType joinType, Object... values) {
    return this.predicate(new NotInSpecification<T>(property, Arrays.asList(values), joinType));
  }

  public PredicateBuilder<T> notIn(boolean condition, String property, List<Object> values) {
    return this.predicate(condition, new NotInSpecification<T>(property, values));
  }

  public PredicateBuilder<T> notIn(boolean condition, String property, Object... values) {
    return this.predicate(condition, new NotInSpecification<T>(property, Arrays.asList(values)));
  }

  public PredicateBuilder<T> notIn(boolean condition, String property, JoinType joinType,
      List<Object> values) {
    return this.predicate(condition, new NotInSpecification<T>(property, values, joinType));
  }

  public PredicateBuilder<T> notIn(boolean condition, String property, JoinType joinType,
      Object... values) {
    return this.predicate(condition,
        new NotInSpecification<T>(property, Arrays.asList(values), joinType));
  }

  public PredicateBuilder<T> predicate(Specification<T> specification) {
    return predicate(true, specification);
  }

  public PredicateBuilder<T> predicate(boolean condition, Specification<T> specification) {
    if (condition) {
      this.specifications.add(specification);
    }
    return this;
  }

  public Specification<T> build() {
    return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
      Predicate[] predicates = new Predicate[specifications.size()];
      for (int i = 0; i < specifications.size(); i++) {
        predicates[i] = specifications.get(i).toPredicate(root, query, cb);
      }
      if (Objects.equals(predicates.length, 0)) {
        return null;
      }
      return OR.equals(operator) ? cb.or(predicates) : cb.and(predicates);
    };
  }

}
