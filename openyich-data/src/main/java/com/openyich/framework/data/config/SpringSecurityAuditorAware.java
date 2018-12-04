package com.openyich.framework.data.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.openyich.framework.boot.security.SecurityUtils;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component("springSecurityAuditorAware")
public class SpringSecurityAuditorAware implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(SecurityUtils.getCurrentUserLogin().orElse("system"));
  }

}
