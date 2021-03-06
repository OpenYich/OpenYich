package com.openyich.framework.boot.jpa.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.openyich.framework.boot.jpa.filter.Filter;
import com.openyich.framework.boot.jpa.filter.FloatFilter;

public class FloatFilterTest {

  private FloatFilter filter;

  private Float value = 42F;

  @Before
  public void setup() {
    filter = new FloatFilter();
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
    assertThat(filter.toString()).isEqualTo("FloatFilter []");
  }

  @Test
  public void testSetEquals() {
    Filter<Float> chain = filter.setEquals(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getEquals()).isEqualTo(value);
  }

  @Test
  public void testSetLessThan() {
    Filter<Float> chain = filter.setLessThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getLessThan()).isEqualTo(value);
  }

  @Test
  public void testSetLessOrEqualThan() {
    Filter<Float> chain = filter.setLessOrEqualThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getLessOrEqualThan()).isEqualTo(value);
  }

  @Test
  public void testSetGreaterThan() {
    Filter<Float> chain = filter.setGreaterThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getGreaterThan()).isEqualTo(value);
  }

  @Test
  public void testSetGreaterOrEqualThan() {
    Filter<Float> chain = filter.setGreaterOrEqualThan(value);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getGreaterOrEqualThan()).isEqualTo(value);
  }

  @Test
  public void testSetSpecified() {
    Filter<Float> chain = filter.setSpecified(true);
    assertThat(chain).isEqualTo(filter);
    assertThat(filter.getSpecified()).isEqualTo(true);
  }

  @Test
  public void testSetIn() {
    List<Float> list = new LinkedList<>();
    Filter<Float> chain = filter.setIn(list);
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
        "FloatFilter " + "[greaterThan=" + str + ", greaterOrEqualThan=" + str + ", lessThan=" + str
            + ", " + "lessOrEqualThan=" + str + ", equals=" + str + ", specified=true, in=[]]");
  }
}
