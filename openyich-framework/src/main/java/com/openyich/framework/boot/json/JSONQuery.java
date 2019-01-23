package com.openyich.framework.boot.json;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.openyich.framework.boot.jpa.specification.Pageables;
import com.openyich.framework.boot.jpa.specification.Sorts;

/**
 * Base class for Spring MVC Query Request types.
 */
public class JSONQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  private String q;

  private String startDate;

  private String endDate;

  @Pattern(regexp = "\\w+( )?(DESC|ASC|desc|asc)?")
  private String sort;

  @Min(1)
  private Integer page = 1;

  @Min(1)
  private Integer size = 10;

  public Pageable buildPageable() {
    Pageables.Builder builder = Pageables.builder().startPage(page - 1).pageSize(size);
    if (hasSort()) {
      String[] arrays = org.apache.commons.lang3.StringUtils.split(sort, " ");
      if (arrays != null && arrays.length > 0) {
        String property = org.apache.commons.lang3.StringUtils.trim(arrays[0]);
        if (org.apache.commons.lang3.StringUtils.containsAny(sort, "DESC", "desc")) {
          builder.sort(Sorts.builder().desc(property).build());
        } else {
          builder.sort(Sorts.builder().asc(property).build());
        }
      }
    }
    return builder.build();
  }

  public String getEndDate() {
    return endDate;
  }

  public Integer getPage() {
    return page;
  }

  public String getQ() {
    return q;
  }

  public Integer getSize() {
    return size;
  }

  public String getSort() {
    return sort;
  }

  public String getStartDate() {
    return startDate;
  }

  public boolean hasSort() {
    return StringUtils.hasText(sort);
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
  
  public void setSort(String sort) {
    this.sort = sort;
  }
  
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
  
}
