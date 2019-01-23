package com.openyich.framework.boot.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

import com.openyich.framework.boot.aware.RequestBodyAware;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnMissingBean(RequestBodyAware.class)
public class DefaultRequestBodyAware implements RequestBodyAware {

}
