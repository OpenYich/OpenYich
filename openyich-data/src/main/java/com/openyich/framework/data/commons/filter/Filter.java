package com.openyich.framework.data.commons.filter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Base class for the various attribute filters. It can be added to a criteria class as a member, to
 * support the following query parameters: <pre>
 *      fieldName.equals='something'
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in='something','other'
 * </pre>
 */
public class Filter<X> implements Serializable {

  private static final long serialVersionUID = 1L;
  private X equals;
  private Boolean specified;
  private List<X> in;

  public X getEquals() {
    return equals;
  }

  public Filter<X> setEquals(X equals) {
    this.equals = equals;
    return this;
  }

  public Boolean getSpecified() {
    return specified;
  }

  public Filter<X> setSpecified(Boolean specified) {
    this.specified = specified;
    return this;
  }

  public List<X> getIn() {
    return in;
  }

  public Filter<X> setIn(List<X> in) {
    this.in = in;
    return this;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Filter<?> filter = (Filter<?>) o;
    return Objects.equals(equals, filter.equals) && Objects.equals(specified, filter.specified)
        && Objects.equals(in, filter.in);
  }

  @Override
  public int hashCode() {
    return Objects.hash(equals, specified, in);
  }

  @Override
  public String toString() {
    return getFilterName() + " [" + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
        + (getIn() != null ? "in=" + getIn() + ", " : "")
        + (getSpecified() != null ? "specified=" + getSpecified() : "") + "]";
  }

  protected String getFilterName() {
    return this.getClass().getSimpleName();
  }
}
