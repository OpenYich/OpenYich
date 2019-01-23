package com.openyich.framework.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.openyich.framework.boot.config.AspectJConfiguration;
import com.openyich.framework.boot.config.AsyncConfiguration;
import com.openyich.framework.boot.config.DateTimeFormatConfiguration;
import com.openyich.framework.boot.config.ExceptionTranslator;
import com.openyich.framework.boot.config.HttpHeadersTranslator;
import com.openyich.framework.boot.config.JacksonConfiguration;
import com.openyich.framework.boot.config.LocaleConfiguration;
import com.openyich.framework.boot.config.ProblemResponseBodyAdvice;
import com.openyich.framework.boot.config.SwaggerConfiguration;
import com.openyich.framework.boot.config.WebConfigurer;
import com.openyich.framework.boot.hazelcast.HazelcastCacheConfiguration;
import com.openyich.framework.boot.security.ssl.UndertowSSLConfiguration;
import com.openyich.framework.boot.security.uaa.UaaConfiguration;
import com.openyich.framework.boot.web.DefaultRequestBodyAware;
import com.openyich.framework.boot.web.DefaultResponseBodyAware;

/**
 * OpenYich Auto-configuration
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({OpenYichProperties.class})
@Import({
  AspectJConfiguration.class,
  AsyncConfiguration.class, 
  DateTimeFormatConfiguration.class,
  DefaultRequestBodyAware.class,
  DefaultResponseBodyAware.class,
  ExceptionTranslator.class,
  HazelcastCacheConfiguration.class,
  HttpHeadersTranslator.class,
  JacksonConfiguration.class,
  LocaleConfiguration.class,
  ProblemResponseBodyAdvice.class,
  SwaggerConfiguration.class,
  UaaConfiguration.class,
  UndertowSSLConfiguration.class,
  WebConfigurer.class
})
public class OpenYichAutoConfiguration {
  
}
