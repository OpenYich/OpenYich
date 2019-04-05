package com.openyich.framework.boot.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Nullable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.openyich.framework.boot.autoconfigure.OpenYichProperties;
import com.openyich.framework.boot.autoconfigure.OpenYichProperties.Swagger;
import com.openyich.framework.boot.swagger.BasicSwaggerCustomizer;
import com.openyich.framework.boot.swagger.SwaggerCustomizer;
import com.openyich.framework.boot.test.LogbackRecorder;
import com.openyich.framework.boot.test.LogbackRecorder.Event;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfigurationTest {

  private Swagger properties;
  private SwaggerConfiguration config;
  private ApiSelectorBuilder builder;
  private LogbackRecorder recorder;

  @Captor
  private ArgumentCaptor<ApiInfo> infoCaptor;

  @Captor
  private ArgumentCaptor<Predicate<String>> pathsCaptor;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    final OpenYichProperties openYichProperties = new OpenYichProperties();
    properties = openYichProperties.getSwagger();
    properties.setHost("test.host.org");
    properties.setProtocols(new String[] {"http", "https"});
    properties.setTitle("test title");
    properties.setDescription("test description");
    properties.setVersion("6.6.6");
    properties.setTermsOfServiceUrl("http://test.host.org/terms");
    properties.setContactName("test contact");
    properties.setContactEmail("test@host.org");
    properties.setContactUrl("http://test.host.org/contact");
    properties.setLicense("free as in beer");
    properties.setLicenseUrl("http://test.host.org/license");
    properties.setUseDefaultResponseMessages(false);

    config = new SwaggerConfiguration(openYichProperties) {
      @Override
      protected Docket createDocket() {
        Docket docket = spy(super.createDocket());
        when(docket.select()).thenReturn(builder = spy(new ApiSelectorBuilder(docket)));
        return docket;
      }
    };

    recorder = LogbackRecorder.forClass(SwaggerConfiguration.class).reset().capture("ALL");
  }

  @After
  public void teardown() {
    recorder.release();
  }

  @Test
  public void testSwaggerSpringfoxApiDocket() {
    List<SwaggerCustomizer> customizers =
        Lists.newArrayList(new BasicSwaggerCustomizer(properties));
    Docket docket = config.swaggerSpringfoxApiDocket(customizers, new NullProvider<>());

    verify(docket, never()).groupName(anyString());
    verify(docket).host(properties.getHost());
    verify(docket).protocols(new HashSet<>(Arrays.asList(properties.getProtocols())));

    verify(docket).apiInfo(infoCaptor.capture());
    ApiInfo info = infoCaptor.getValue();
    assertThat(info.getTitle()).isEqualTo(properties.getTitle());
    assertThat(info.getDescription()).isEqualTo(properties.getDescription());
    assertThat(info.getVersion()).isEqualTo(properties.getVersion());
    assertThat(info.getTermsOfServiceUrl()).isEqualTo(properties.getTermsOfServiceUrl());
    assertThat(info.getContact().getName()).isEqualTo(properties.getContactName());
    assertThat(info.getContact().getEmail()).isEqualTo(properties.getContactEmail());
    assertThat(info.getContact().getUrl()).isEqualTo(properties.getContactUrl());
    assertThat(info.getLicense()).isEqualTo(properties.getLicense());
    assertThat(info.getLicenseUrl()).isEqualTo(properties.getLicenseUrl());
    assertThat(info.getVendorExtensions()).isEmpty();

    verify(docket).useDefaultResponseMessages(properties.isUseDefaultResponseMessages());
    verify(docket).forCodeGeneration(true);
    verify(docket).directModelSubstitute(ByteBuffer.class, String.class);
    verify(docket).genericModelSubstitutes(ResponseEntity.class);

    verify(docket).select();
    verify(builder).paths(pathsCaptor.capture());
    Predicate<String> paths = pathsCaptor.getValue();
    assertThat(paths.apply("/api/foo")).isEqualTo(true);
    assertThat(paths.apply("/foo/api")).isEqualTo(false);

    verify(builder).build();

    List<Event> events = recorder.play();
    assertThat(events).hasSize(2);

    Event event0 = events.get(0);
    assertThat(event0.getLevel()).isEqualTo("DEBUG");
    assertThat(event0.getMessage()).isEqualTo(SwaggerConfiguration.STARTING_MESSAGE);
    assertThat(event0.getThrown()).isNull();

    Event event1 = events.get(1);
    assertThat(event1.getLevel()).isEqualTo("DEBUG");
    assertThat(event1.getMessage()).isEqualTo(SwaggerConfiguration.STARTED_MESSAGE);
    assertThat(event1.getThrown()).isNull();
  }

  static class NullProvider<T> implements ObjectProvider<T> {

    @Nullable
    @Override
    public T getObject(Object... args) throws BeansException {
      return null;
    }

    @Nullable
    @Override
    public T getIfAvailable() throws BeansException {
      return null;
    }

    @Nullable
    @Override
    public T getIfUnique() throws BeansException {
      return null;
    }

    @Nullable
    @Override
    public T getObject() throws BeansException {
      return null;
    }
  }
}
