package com.openyich.framework.boot.swagger;

import java.util.List;

import org.springframework.core.annotation.Order;

import com.google.common.collect.Lists;
import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Authorization Customize the Springfox Docket.
 */
@Order(1)
public class AuthorizationSwaggerCustomizer implements SwaggerCustomizer {

  private OpenYichProperties.Swagger properties;

  public AuthorizationSwaggerCustomizer(OpenYichProperties.Swagger properties) {
    this.properties = properties;
  }

  @Override
  public void customize(Docket docket) {
    if (properties.getAuthorization().isEnabled()) {
      Parameter parameter = new ParameterBuilder()
          .name(properties.getAuthorization().getName())
          .description(properties.getAuthorization().getDescription())
          .defaultValue(properties.getAuthorization().getDefaultValue())
          .required(properties.getAuthorization().isEnabled())
          .modelRef(new ModelRef("string")) // type: string
          .parameterType(properties.getAuthorization().getParamType())
          .pattern(properties.getAuthorization().getPattern())
          .build();
        List<Parameter> parameters = Lists.newArrayList();
        parameters.add(parameter);
        docket.globalOperationParameters(parameters);
    }
  }

}
