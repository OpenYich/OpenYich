package com.openyich.framework.data.jpa.specification;

import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class Pageables {

  private Pageables() {}

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private int startPage = 0;
    private int pageSize = 10;
    private Sort sort;

    public Builder startPage(int startPage) {
      this.startPage = startPage;
      return this;
    }

    public Builder pageSize(int pageSize) {
      this.pageSize = pageSize;
      return this;
    }

    public Builder sort(Sort sort) {
      this.sort = sort;
      return this;
    }

    public Pageable build() {
      if (Objects.isNull(sort)) {
        return PageRequest.of(startPage, pageSize);
      } else {
        return PageRequest.of(startPage, pageSize, sort);
      }
    }

  }

}
