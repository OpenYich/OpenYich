package com.openyich.framework.boot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.openyich.framework.boot.security.jwt.JWTSecurityConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(JWTSecurityConfiguration.class)
@Documented
public @interface EnableJWTSecurity {

}
