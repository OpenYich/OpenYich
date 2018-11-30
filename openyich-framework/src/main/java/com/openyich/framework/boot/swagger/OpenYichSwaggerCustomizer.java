package com.openyich.framework.boot.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;

import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Customize the Springfox Docket.
 */
@Order(0)
public class OpenYichSwaggerCustomizer implements SwaggerCustomizer {

  private OpenYichProperties.Swagger properties;

  public OpenYichSwaggerCustomizer(OpenYichProperties.Swagger properties) {
    this.properties = properties;
  }

  @Override
  public void customize(Docket docket) {
    Contact contact = new Contact(
      properties.getContactName(), 
      properties.getContactUrl(),
      properties.getContactEmail()
    );

    ApiInfo apiInfo = new ApiInfo(
      properties.getTitle(), 
      properties.getDescription(),
      properties.getVersion(), 
      properties.getTermsOfServiceUrl(), 
      contact,
      properties.getLicense(), 
      properties.getLicenseUrl(), 
      new ArrayList<>()
    );

    docket.host(properties.getHost())
        .protocols(new HashSet<>(Arrays.asList(properties.getProtocols())))
        .apiInfo(apiInfo)
        .useDefaultResponseMessages(properties.isUseDefaultResponseMessages())
        .forCodeGeneration(true)
        .directModelSubstitute(ByteBuffer.class, String.class)
        .genericModelSubstitutes(ResponseEntity.class)
        .select()
        .paths(regex(properties.getDefaultIncludePattern()))
        .build();
  }

}
