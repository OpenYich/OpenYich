package com.openyich.framework.data.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to OpenYich Data.
 */
@ConfigurationProperties(prefix = "openyich.data", ignoreUnknownFields = false)
public class OpenYichDataProperties {

}
