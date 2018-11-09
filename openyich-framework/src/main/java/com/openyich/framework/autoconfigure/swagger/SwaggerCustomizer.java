package com.openyich.framework.autoconfigure.swagger;

import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zhycn
 * @since 2.1.0 2018-11-09
 */
@FunctionalInterface
public interface SwaggerCustomizer {

  /**
   * Customize the Springfox Docket.
   *
   * @param docket the Docket to customize
   */
  void customize(Docket docket);

}
