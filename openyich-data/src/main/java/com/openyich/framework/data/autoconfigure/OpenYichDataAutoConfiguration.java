package com.openyich.framework.data.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.openyich.framework.data.config.SpringSecurityAuditorAware;

@Configuration
@EnableConfigurationProperties({OpenYichDataProperties.class})
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
@Import({
  SpringSecurityAuditorAware.class
})
public class OpenYichDataAutoConfiguration {

}
