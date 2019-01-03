package com.openyich.framework.boot.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

import com.openyich.framework.boot.aware.ResponseWordsAware;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnMissingBean(ResponseWordsAware.class)
public class DefaultResponseWordsAware implements ResponseWordsAware {

  @Override
  public String getWords(String code, String message) {
    return message;
  }

}
