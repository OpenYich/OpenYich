package com.openyich.framework.data.commons.filter;

import java.time.Instant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Filter class for {@link Instant} type attributes.
 *
 * @see RangeFilter
 */
public class InstantFilter extends RangeFilter<Instant> {

  private static final long serialVersionUID = 1L;

  @Override
  @DateTimeFormat(iso = ISO.DATE_TIME)
  public InstantFilter setEquals(Instant equals) {
    super.setEquals(equals);
    return this;
  }

  @Override
  @DateTimeFormat(iso = ISO.DATE_TIME)
  public InstantFilter setGreaterThan(Instant equals) {
    super.setGreaterThan(equals);
    return this;
  }

  @Override
  @DateTimeFormat(iso = ISO.DATE_TIME)
  public InstantFilter setGreaterOrEqualThan(Instant equals) {
    super.setGreaterOrEqualThan(equals);
    return this;
  }

  @Override
  @DateTimeFormat(iso = ISO.DATE_TIME)
  public InstantFilter setLessThan(Instant equals) {
    super.setLessThan(equals);
    return this;
  }

  @Override
  @DateTimeFormat(iso = ISO.DATE_TIME)
  public InstantFilter setLessOrEqualThan(Instant equals) {
    super.setLessOrEqualThan(equals);
    return this;
  }

}
