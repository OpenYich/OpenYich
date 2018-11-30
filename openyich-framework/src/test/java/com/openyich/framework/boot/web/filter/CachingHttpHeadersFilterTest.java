package com.openyich.framework.boot.web.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

import static com.openyich.framework.boot.web.filter.CachingHttpHeadersFilter.DEFAULT_DAYS_TO_LIVE;
import static com.openyich.framework.boot.web.filter.CachingHttpHeadersFilter.LAST_MODIFIED;

public class CachingHttpHeadersFilterTest {

  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private FilterChain chain;
  private OpenYichProperties properties;
  private CachingHttpHeadersFilter filter;

  @Before
  public void setup() {
    request = new MockHttpServletRequest();
    response = spy(new MockHttpServletResponse());
    chain = spy(new MockFilterChain());
    properties = new OpenYichProperties();
    filter = new CachingHttpHeadersFilter(properties);
  }

  @After
  public void teardown() {
    filter.destroy();
  }

  @Test
  public void testWithoutInit() {
    int daysToLive = DEFAULT_DAYS_TO_LIVE;
    long secsToLive = TimeUnit.DAYS.toMillis(daysToLive);

    long before = System.currentTimeMillis();
    before -= before % 1000L;

    Throwable caught = catchThrowable(() -> {
      filter.doFilter(request, response, chain);
      verify(chain).doFilter(request, response);
    });

    long after = System.currentTimeMillis();
    after += 1000L - (after % 1000L);

    verify(response).setHeader("Cache-Control", "max-age=" + secsToLive + ", public");
    verify(response).setHeader("Pragma", "cache");
    verify(response).setDateHeader(eq("Expires"), anyLong());
    assertThat(response.getDateHeader("Expires")).isBetween(before + secsToLive,
        after + secsToLive);
    verify(response).setDateHeader("Last-Modified", LAST_MODIFIED);
    assertThat(caught).isNull();
  }

  @Test
  public void testWithInit() {
    int daysToLive = DEFAULT_DAYS_TO_LIVE >>> 2;
    long secsToLive = TimeUnit.DAYS.toMillis(daysToLive);
    properties.getHttp().getCache().setTimeToLiveInDays(daysToLive);

    long before = System.currentTimeMillis();
    before -= before % 1000L;

    Throwable caught = catchThrowable(() -> {
      filter.init(null);
      filter.doFilter(request, response, chain);
      verify(chain).doFilter(request, response);
    });

    long after = System.currentTimeMillis();
    after += 1000L - (after % 1000L);

    verify(response).setHeader("Cache-Control", "max-age=" + secsToLive + ", public");
    verify(response).setHeader("Pragma", "cache");
    verify(response).setDateHeader(eq("Expires"), anyLong());
    assertThat(response.getDateHeader("Expires")).isBetween(before + secsToLive,
        after + secsToLive);
    verify(response).setDateHeader("Last-Modified", LAST_MODIFIED);
    assertThat(caught).isNull();
  }
}