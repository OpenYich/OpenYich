package com.openyich.framework.boot.jpa.filter;

import com.google.common.collect.Lists;
import com.openyich.framework.boot.jpa.filter.Filter;
import com.openyich.framework.boot.jpa.filter.RangeFilter;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RangeFilterTest {

  private RangeFilter<Short> filter;

  private Short value = 42;

  @Before
  public void setup() {
    filter = new RangeFilter<Short>();
  }

  @Test
  public void testConstructor() {
    assertThat(filter.getEquals()).isNull();
    assertThat(filter.getGreaterThan()).isNull();
    assertThat(filter.getGreaterOrEqualThan()).isNull();
    assertThat(filter.getLessThan()).isNull();
    assertThat(filter.getLessOrEqualThan()).isNull();
    assertThat(filter.getSpecified()).isNull();
    assertThat(filter.getIn()).isNull();
    assertThat(filter.toString()).isEqualTo("RangeFilter []");
  }

  @Test
  public void testSetEquals() {
    Filter<Short> chain = filter.setEquals(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getEquals()).isEqualTo(value);
  }

  @Test
  public void testSetLessThan() {
    Filter<Short> chain = filter.setLessThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getLessThan()).isEqualTo(value);
  }

  @Test
  public void testSetLessOrEqualThan() {
    Filter<Short> chain = filter.setLessOrEqualThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getLessOrEqualThan()).isEqualTo(value);
  }

  @Test
  public void testSetGreaterThan() {
    Filter<Short> chain = filter.setGreaterThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getGreaterThan()).isEqualTo(value);
  }

  @Test
  public void testSetGreaterOrEqualThan() {
    Filter<Short> chain = filter.setGreaterOrEqualThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getGreaterOrEqualThan()).isEqualTo(value);
  }

  @Test
  public void testSetSpecified() {
    Filter<Short> chain = filter.setSpecified(true);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getSpecified()).isEqualTo(true);
  }

  @Test
  public void testSetIn() {
    List<Short> list = new LinkedList<>();
    Filter<Short> chain = filter.setIn(list);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getIn()).isEqualTo(list);
  }

  @Test
  public void testEquals() {
    final RangeFilter<Short> filter2 = new RangeFilter<>();
    assertThat(filter).isEqualTo(filter2);
    filter.setEquals(value);
    filter2.setEquals(value);
    assertThat(filter).isEqualTo(filter2);
    filter.setIn(Lists.newArrayList(value, value));
    filter2.setIn(Lists.newArrayList(value, value));
    assertThat(filter).isEqualTo(filter2);
    filter.setLessThan(value);
    filter2.setLessThan(value);
    assertThat(filter).isEqualTo(filter2);
    filter.setGreaterOrEqualThan(value);
    filter2.setGreaterOrEqualThan(value);
    assertThat(filter).isEqualTo(filter2);
    final Filter<Short> filter3 = new Filter<>();
    filter3.setEquals(value);
    assertThat(filter3).isNotEqualTo(filter);
    assertThat(filter3).isNotEqualTo(filter2);

    assertThat(filter).isEqualTo(filter);
  }

  @Test
  public void testHashCode() {
    final RangeFilter<Short> filter2 = new RangeFilter<>();
    assertThat(filter.hashCode()).isEqualTo(filter2.hashCode());
    filter.setEquals(value);
    filter2.setEquals(value);
    assertThat(filter.hashCode()).isEqualTo(filter2.hashCode());
    filter.setIn(Lists.newArrayList(value, value));
    filter2.setIn(Lists.newArrayList(value, value));
    assertThat(filter.hashCode()).isEqualTo(filter2.hashCode());
    filter.setLessThan(value);
    filter2.setLessThan(value);
    assertThat(filter.hashCode()).isEqualTo(filter2.hashCode());
    filter.setGreaterOrEqualThan(value);
    filter2.setGreaterOrEqualThan(value);
    assertThat(filter.hashCode()).isEqualTo(filter2.hashCode());
    final Filter<Short> filter3 = new Filter<>();
    filter3.setEquals(value);
    assertThat(filter3.hashCode()).isNotEqualTo(filter.hashCode());
    assertThat(filter3.hashCode()).isNotEqualTo(filter2.hashCode());

    assertThat(filter.hashCode()).isEqualTo(filter.hashCode());
  }

  @Test
  public void testToString() {
    filter.setEquals(value);
    filter.setLessThan(value);
    filter.setLessOrEqualThan(value);
    filter.setGreaterThan(value);
    filter.setGreaterOrEqualThan(value);
    filter.setSpecified(true);
    filter.setIn(new LinkedList<>());
    assertThat(filter.toString())
        .isEqualTo("RangeFilter " + "[greaterThan=42, greaterOrEqualThan=42, lessThan=42, "
            + "lessOrEqualThan=42, equals=42, specified=true, in=[]]");
  }
}
