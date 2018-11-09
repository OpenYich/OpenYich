package com.openyich.framework.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * OpenYich Auto-configuration
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-08
 */
@Configuration
@EnableConfigurationProperties({OpenYichProperties.class})
public class OpenYichAutoConfiguration {

}
