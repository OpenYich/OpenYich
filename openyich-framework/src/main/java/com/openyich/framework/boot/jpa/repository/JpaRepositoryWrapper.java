package com.openyich.framework.boot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRepositoryWrapper<T, ID>
    extends
      JpaRepository<T, ID>,
      JpaSpecificationExecutor<T> {

}
