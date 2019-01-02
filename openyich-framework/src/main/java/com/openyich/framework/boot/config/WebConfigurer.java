package com.openyich.framework.boot.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

import io.undertow.UndertowOptions;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
@ConditionalOnWebApplication
public class WebConfigurer
    implements
      ServletContextInitializer,
      WebServerFactoryCustomizer<WebServerFactory> {

  private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

  private final Environment env;

  private final OpenYichProperties openYichProperties;

  public WebConfigurer(Environment env, OpenYichProperties openYichProperties) {
    this.env = env;
    this.openYichProperties = openYichProperties;
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    if (env.getActiveProfiles().length != 0) {
      log.info("Web application configuration, using profiles: {}",
          (Object[]) env.getActiveProfiles());
    }
  }

  /**
   * Customize the Servlet engine: Mime types, the document root, the cache.
   */
  @Override
  public void customize(WebServerFactory server) {
    setMimeMappings(server);

    /*
     * Enable HTTP/2 for Undertow - https://twitter.com/ankinson/status/829256167700492288 HTTP/2
     * requires HTTPS, so HTTP requests will fallback to HTTP/1.1. See the OpenYichProperties class
     * and your application-*.yml configuration files for more information.
     */
    if (openYichProperties.getHttp().getVersion().equals(OpenYichProperties.Http.Version.V_2_0)
        && server instanceof UndertowServletWebServerFactory) {
      ((UndertowServletWebServerFactory) server).addBuilderCustomizers(
          builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
    }
  }

  private void setMimeMappings(WebServerFactory server) {
    if (server instanceof ConfigurableServletWebServerFactory) {
      MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
      String mimeType =
          MediaType.TEXT_HTML_VALUE + ";charset=" + StandardCharsets.UTF_8.name().toLowerCase();
      // IE issue, see https://github.com/jhipster/generator-jhipster/pull/711
      mappings.add("html", mimeType);
      // CloudFoundry issue, see https://github.com/cloudfoundry/gorouter/issues/64
      mappings.add("json", mimeType);
      ConfigurableServletWebServerFactory servletWebServer =
          (ConfigurableServletWebServerFactory) server;
      servletWebServer.setMimeMappings(mappings);
    }
  }

  @Bean
  @ConditionalOnMissingBean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.setCorsConfigurations(openYichProperties.getCors());
    return new CorsFilter(source);
  }

}
