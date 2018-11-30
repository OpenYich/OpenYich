package com.openyich.framework.boot.swagger;

import springfox.documentation.spring.web.plugins.Docket;

@FunctionalInterface
public interface SwaggerCustomizer {

  /**
   * Customize the Springfox Docket.
   *
   * @param docket the Docket to customize
   */
  void customize(Docket docket);

}
