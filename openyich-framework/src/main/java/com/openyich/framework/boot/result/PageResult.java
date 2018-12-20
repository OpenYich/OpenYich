package com.openyich.framework.boot.result;

import org.springframework.data.domain.Page;

public class PageResult extends Result {

  private static final long serialVersionUID = 1L;

  /**
   * the number of total pages.
   */
  private int totalPages;

  /**
   * the total amount of elements.
   */
  private long totalElements;

  /**
   * the number of elements currently.
   */
  private int numberOfElements;

  /**
   * whether the current is the first one.
   */
  private boolean first;

  /**
   * whether the current is the last one.
   */
  private boolean last;

  /**
   * if there is a next.
   */
  private boolean hasNext;

  /**
   * if there is a previous.
   */
  private boolean hasPrevious;

  public PageResult() {
    super();
  }

  public PageResult(String code, String error, Page<?> pageResult) {
    super(code, error, pageResult.getContent());
    this.setTotalPages(pageResult.getTotalPages());
    this.setTotalElements(pageResult.getTotalElements());
    this.setNumberOfElements(pageResult.getNumberOfElements());
    this.setFirst(pageResult.isFirst());
    this.setLast(pageResult.isLast());
    this.setHasNext(pageResult.hasNext());
    this.setHasPrevious(pageResult.hasPrevious());
  }

  public int getTotalPages() {
    return totalPages;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public int getNumberOfElements() {
    return numberOfElements;
  }

  public boolean isFirst() {
    return first;
  }

  public boolean isLast() {
    return last;
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public boolean isHasPrevious() {
    return hasPrevious;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public void setNumberOfElements(int numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public void setFirst(boolean first) {
    this.first = first;
  }

  public void setLast(boolean last) {
    this.last = last;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public void setHasPrevious(boolean hasPrevious) {
    this.hasPrevious = hasPrevious;
  }

}
