package com.openyich.framework.data.commons.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.openyich.framework.data.commons.filter.Filter;
import com.openyich.framework.data.commons.filter.ZonedDateTimeFilter;

public class ZonedDateTimeFilterTest {

  private ZonedDateTimeFilter filter;

  private ZonedDateTime value = ZonedDateTime.now();

  @Before
  public void setup() {
    filter = new ZonedDateTimeFilter();
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
    assertThat(filter.toString()).isEqualTo("ZonedDateTimeFilter []");
  }

  @Test
  public void testSetEquals() {
    Filter<ZonedDateTime> chain = filter.setEquals(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getEquals()).isEqualTo(value);
  }

  @Test
  public void testSetLessThan() {
    Filter<ZonedDateTime> chain = filter.setLessThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getLessThan()).isEqualTo(value);
  }

  @Test
  public void testSetLessOrEqualThan() {
    Filter<ZonedDateTime> chain = filter.setLessOrEqualThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getLessOrEqualThan()).isEqualTo(value);
  }

  @Test
  public void testSetGreaterThan() {
    Filter<ZonedDateTime> chain = filter.setGreaterThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getGreaterThan()).isEqualTo(value);
  }

  @Test
  public void testSetGreaterOrEqualThan() {
    Filter<ZonedDateTime> chain = filter.setGreaterOrEqualThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getGreaterOrEqualThan()).isEqualTo(value);
  }

  @Test
  public void testSetSpecified() {
    Filter<ZonedDateTime> chain = filter.setSpecified(true);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getSpecified()).isEqualTo(true);
  }

  @Test
  public void testSetIn() {
    List<ZonedDateTime> list = new LinkedList<>();
    Filter<ZonedDateTime> chain = filter.setIn(list);
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
    assertThat(filter.toString()).isEqualTo("ZonedDateTimeFilter " + "[greaterThan=" + str
        + ", greaterOrEqualThan=" + str + ", lessThan=" + str + ", " + "lessOrEqualThan=" + str
        + ", equals=" + str + ", specified=true, in=[]]");
  }
}