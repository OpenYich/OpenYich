package com.openyich.framework.boot.security.ssl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.test.util.ReflectionTestUtils;
import org.xnio.OptionMap;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;

public class UndertowSSLConfigurationTest {

  private OpenYichProperties properties;

  @Before
  public void setup() {
    properties = new OpenYichProperties();
  }

  @Test
  public void testUndertowSSLConfigurationOK() {
    // Prepare
    UndertowServletWebServerFactory undertowServletWebServerFactory =
        new UndertowServletWebServerFactory();
    properties.getHttp().setUseUndertowUserCipherSuitesOrder(true);

    // Execute
    new UndertowSSLConfiguration(undertowServletWebServerFactory, properties);

    // Verify
    Undertow.Builder builder = Undertow.builder();
    undertowServletWebServerFactory.getBuilderCustomizers().forEach(c -> c.customize(builder));
    OptionMap.Builder serverOptions =
        (OptionMap.Builder) ReflectionTestUtils.getField(builder, "socketOptions");
    assertThat(undertowServletWebServerFactory).isNotNull();
    assertThat(serverOptions.getMap().get(UndertowOptions.SSL_USER_CIPHER_SUITES_ORDER)).isTrue();
  }

  @Test
  public void testUndertowSSLConfigurationKO() {
    // Prepare
    UndertowServletWebServerFactory undertowServletWebServerFactory =
        new UndertowServletWebServerFactory();
    properties.getHttp().setUseUndertowUserCipherSuitesOrder(false);

    // Execute
    new UndertowSSLConfiguration(undertowServletWebServerFactory, properties);

    // Verify
    Undertow.Builder builder = Undertow.builder();
    undertowServletWebServerFactory.getBuilderCustomizers().forEach(c -> c.customize(builder));
    OptionMap.Builder serverOptions =
        (OptionMap.Builder) ReflectionTestUtils.getField(builder, "socketOptions");
    assertThat(undertowServletWebServerFactory).isNotNull();
    assertThat(serverOptions.getMap().get(UndertowOptions.SSL_USER_CIPHER_SUITES_ORDER)).isNull();
  }

}
