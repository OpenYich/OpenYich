package com.openyich.framework.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.openyich.framework.boot.OpenYich;
import com.openyich.framework.boot.config.JacksonConfiguration;

/**
 * OpenYich Auto-configuration
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-08
 */
@Configuration
@EnableConfigurationProperties({OpenYichProperties.class})
@Import({OpenYich.class, JacksonConfiguration.class})
public class OpenYichAutoConfiguration {
  
}
