package com.openyich.framework.data.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.openyich.framework.data.config.SpringSecurityAuditorAware;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@Import({SpringSecurityAuditorAware.class})
public class JpaRepositoryConfiguration {

}
