package com.openyich.framework.boot.jpa.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.openyich.framework.boot.jpa.filter.Filter;
import com.openyich.framework.boot.jpa.filter.ShortFilter;

public class ShortFilterTest {

  private ShortFilter filter;

  private Short value = 42;

  @Before
  public void setup() {
    filter = new ShortFilter();
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
    assertThat(filter.toString()).isEqualTo("ShortFilter []");
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
  public void testToString() {
    filter.setEquals(value);
    filter.setLessThan(value);
    filter.setLessOrEqualThan(value);
    filter.setGreaterThan(value);
    filter.setGreaterOrEqualThan(value);
    filter.setSpecified(true);
    filter.setIn(new LinkedList<>());
    String str = value.toString();
    assertThat(filter.toString()).isEqualTo(
        "ShortFilter " + "[greaterThan=" + str + ", greaterOrEqualThan=" + str + ", lessThan=" + str
            + ", " + "lessOrEqualThan=" + str + ", equals=" + str + ", specified=true, in=[]]");
  }
}
