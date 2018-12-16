package com.openyich.framework.data.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({OpenYichDataProperties.class})
public class OpenYichDataAutoConfiguration {

}
