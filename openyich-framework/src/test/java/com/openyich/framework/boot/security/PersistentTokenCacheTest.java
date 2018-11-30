package com.openyich.framework.boot.security;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PersistentTokenCacheTest {

  @Test
  public void testConstructorThrows() {
    Throwable caught = catchThrowable(() -> new PersistentTokenCache<String>(-1l));
    assertThat(caught).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testAbsent() {
    PersistentTokenCache<String> cache = new PersistentTokenCache<>(1000l);
    assertThat(cache.get("key")).isNull();
  }

  @Test
  public void testAccess() {
    PersistentTokenCache<String> cache = new PersistentTokenCache<>(1000l);
    cache.put("key", "val");
    assertThat(cache.size()).isEqualTo(1);
    assertThat(cache.get("key")).isEqualTo("val");
  }

  @Test
  public void testReplace() {
    PersistentTokenCache<String> cache = new PersistentTokenCache<>(1000l);
    cache.put("key", "val");
    cache.put("key", "foo");
    assertThat(cache.get("key")).isEqualTo("foo");
  }

  @Test
  public void testExpires() {
    PersistentTokenCache<String> cache = new PersistentTokenCache<>(1l);
    cache.put("key", "val");
    try {
      Thread.sleep(100l);
    } catch (InterruptedException x) {
      // This should not happen
      throw new Error(x);
    }
    assertThat(cache.get("key")).isNull();
  }

  @Test
  public void testPurge() {
    PersistentTokenCache<String> cache = new PersistentTokenCache<>(1l);
    cache.put("key", "val");
    try {
      Thread.sleep(100l);
    } catch (InterruptedException x) {
      // This should not happen
      throw new Error(x);
    }
    assertThat(cache.size()).isEqualTo(1);
    cache.purge();
    assertThat(cache.size()).isEqualTo(0);
  }

}
