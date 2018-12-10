package com.openyich.framework.data.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Base mapper for Data Layer Interfaces.
 */
public interface IMapper<T, ID> {

  T save(T entity);

  List<T> saveAll(Iterable<T> entities);

  void deleteById(ID id);

  void deleteAllById(Iterable<ID> ids);

  boolean existsById(ID id);

  Optional<T> findById(ID id);

  List<T> findAllById(Iterable<ID> ids);

  Page<T> findAll(T entity, Pageable pageable);

}
