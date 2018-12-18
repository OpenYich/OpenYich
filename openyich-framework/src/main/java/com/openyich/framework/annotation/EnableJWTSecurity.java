package com.openyich.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.openyich.framework.boot.config.JWTSecurityConfiguration;

/**
 * Enable JWT for Spring security and dependency on spring-boot-starter-security.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(JWTSecurityConfiguration.class)
@Documented
public @interface EnableJWTSecurity {

}
