package com.openyich.framework.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.openyich.framework.boot.config.AsyncConfiguration;
import com.openyich.framework.boot.config.DateTimeFormatConfiguration;
import com.openyich.framework.boot.config.JacksonConfiguration;
import com.openyich.framework.boot.config.LocaleConfiguration;
import com.openyich.framework.boot.config.SwaggerConfiguration;
import com.openyich.framework.boot.errors.ExceptionTranslator;
import com.openyich.framework.boot.security.ssl.UndertowSSLConfiguration;
import com.openyich.framework.boot.security.uaa.UaaConfiguration;

/**
 * OpenYich Auto-configuration
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({OpenYichProperties.class})
@Import({
  AsyncConfiguration.class, 
  DateTimeFormatConfiguration.class,
  ExceptionTranslator.class,
  JacksonConfiguration.class,
  LocaleConfiguration.class,
  SwaggerConfiguration.class,
  UaaConfiguration.class,
  UndertowSSLConfiguration.class
})
public class OpenYichAutoConfiguration {
  
}
