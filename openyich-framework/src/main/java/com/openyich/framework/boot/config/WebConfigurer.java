package com.openyich.framework.boot.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

import io.undertow.UndertowOptions;


/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
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
    log.info("Web application fully configured");
  }

  /**
   * Customize the Servlet engine: Mime types, the document root, the cache.
   */
  @Override
  public void customize(WebServerFactory server) {
    setMimeMappings(server);

    /*
     * Enable HTTP/2 for Undertow - https://twitter.com/ankinson/status/829256167700492288 HTTP/2
     * requires HTTPS, so HTTP requests will fallback to HTTP/1.1. See the JHipsterProperties class
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
      // IE issue, see https://github.com/jhipster/generator-jhipster/pull/711
      mappings.add("html",
          MediaType.TEXT_HTML_VALUE + ";charset=" + StandardCharsets.UTF_8.name().toLowerCase());
      // CloudFoundry issue, see https://github.com/cloudfoundry/gorouter/issues/64
      mappings.add("json",
          MediaType.TEXT_HTML_VALUE + ";charset=" + StandardCharsets.UTF_8.name().toLowerCase());
      ConfigurableServletWebServerFactory servletWebServer =
          (ConfigurableServletWebServerFactory) server;
      servletWebServer.setMimeMappings(mappings);
    }
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = openYichProperties.getCors();
    if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
      log.debug("Registering CORS filter");
      source.registerCorsConfiguration("/api/**", config);
      source.registerCorsConfiguration("/management/**", config);
      source.registerCorsConfiguration("/v2/api-docs", config);
    }
    return new CorsFilter(source);
  }

}
