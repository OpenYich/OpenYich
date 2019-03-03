package com.openyich.framework.boot.vo;

import javax.validation.constraints.Min;

/**
 * Base class for Spring MVC Page Query Request types.
 */
public class PageQueryVO extends QueryVO {

  private static final long serialVersionUID = 1L;

  @Min(1)
  private Integer page = 1;

  @Min(1)
  private Integer size = 10;

  public Integer getPage() {
    return page;
  }

  public Integer getSize() {
    return size;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

}
