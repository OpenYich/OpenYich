package com.openyich.framework.boot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.openyich.framework.boot.config.DatabaseConfiguration;

/**
 * Enable JPA Repository for Spring Data JPA and dependency on spring-boot-starter-data-jpa.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(DatabaseConfiguration.class)
public @interface EnableJpaRepository {

}
