package com.openyich.framework.boot.config;

import java.util.List;
import java.util.Optional;

import javax.servlet.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.DispatcherServlet;

import com.openyich.framework.boot.swagger.OpenYichSwaggerCustomizer;
import com.openyich.framework.boot.swagger.SwaggerCustomizer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Springfox Swagger configuration.
 * <p>
 * Warning! When having a lot of REST endpoints, Springfox can become a performance issue. In that
 * case, you can use the "no-swagger" Spring profile, so that this bean is ignored.
 * 
 * @author zhycn
 * @since 2.1.0 2018-01-30
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({
  ApiInfo.class, 
  BeanValidatorPluginsConfiguration.class, 
  Servlet.class,
  DispatcherServlet.class
})
@Profile(OpenYichConstants.SPRING_PROFILE_SWAGGER)
@AutoConfigureAfter(OpenYichProperties.class)
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

  static final String STARTING_MESSAGE = "Starting Swagger";
  static final String STARTED_MESSAGE = "Started Swagger in {} ms";
  
  private static final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

  private final OpenYichProperties.Swagger properties;

  public SwaggerConfiguration(OpenYichProperties openYichProperties) {
    this.properties = openYichProperties.getSwagger();
  }

  /**
   * Springfox configuration for the API Swagger docs.
   *
   * @param swaggerCustomizers Swagger customizers
   * @param alternateTypeRules alternate type rules
   * @return the Swagger Springfox configuration
   */
  @Bean
  @ConditionalOnMissingBean(name = "swaggerSpringfoxApiDocket")
  public Docket swaggerSpringfoxApiDocket(List<SwaggerCustomizer> swaggerCustomizers,
      ObjectProvider<AlternateTypeRule[]> alternateTypeRules) {
    log.debug(STARTING_MESSAGE);
    StopWatch watch = new StopWatch();
    watch.start();

    Docket docket = createDocket();

    // Apply all SwaggerCustomizers orderly.
    swaggerCustomizers.forEach(customizer -> customizer.customize(docket));

    // Add all AlternateTypeRules if available in spring bean factory.
    // Also you can add your rules in a customizer bean above.
    Optional.ofNullable(alternateTypeRules.getIfAvailable()).ifPresent(docket::alternateTypeRules);

    watch.stop();
    log.debug(STARTED_MESSAGE, watch.getTotalTimeMillis());
    return docket;
  }

  /**
   * OpenYich Swagger Customizer
   *
   * @return the Swagger Customizer of OpenYich
   */
  @Bean
  public OpenYichSwaggerCustomizer swaggerCustomizer() {
    return new OpenYichSwaggerCustomizer(properties);
  }

  protected Docket createDocket() {
    return new Docket(DocumentationType.SWAGGER_2);
  }

}
