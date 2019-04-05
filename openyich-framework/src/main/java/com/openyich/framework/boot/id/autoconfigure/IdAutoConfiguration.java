package com.openyich.framework.boot.id.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openyich.framework.boot.id.autoconfigure.SnowflakeProperties.Strategy;
import com.openyich.framework.boot.id.factory.DefaultSnowflakeFactory;
import com.openyich.framework.boot.id.factory.OneTimePasswordFactory;
import com.openyich.framework.boot.id.factory.RandomFactory;
import com.openyich.framework.boot.id.factory.TwitterSnowflakeFactory;
import com.openyich.framework.boot.id.factory.UrlShortenerFactory;
import com.openyich.framework.boot.id.factory.support.GoogleAuthenticator;
import com.openyich.framework.boot.id.factory.support.GoogleAuthenticatorConfig;
import com.openyich.framework.boot.id.service.OneTimePasswordID;
import com.openyich.framework.boot.id.service.RandomID;
import com.openyich.framework.boot.id.service.SnowflakeID;
import com.openyich.framework.boot.id.service.UrlShortenerID;

/**
 * ID Auto-configuration
 */
@Configuration
@EnableConfigurationProperties({SnowflakeProperties.class, TotpProperties.class})
public class IdAutoConfiguration {

  @Autowired
  private SnowflakeProperties snowflakeProperties;

  @Autowired
  private TotpProperties totpProperties;

  @Bean
  @ConditionalOnMissingBean
  public OneTimePasswordID createOneTimePasswordFactory() {
    GoogleAuthenticatorConfig config =
        new GoogleAuthenticatorConfig.GoogleAuthenticatorConfigBuilder()
            .setCodeDigits(totpProperties.getCodeDigits())
            .setHmacHashFunction(totpProperties.getHmacHashFunction())
            .setKeyRepresentation(totpProperties.getKeyRepresentation())
            .setTimeStepSizeInMillis(totpProperties.getTimeStepSizeInMillis())
            .setWindowSize(totpProperties.getWindowSize()).build();
    return new OneTimePasswordFactory(new GoogleAuthenticator(config));
  }

  @Bean
  @ConditionalOnMissingBean
  public RandomID createRandomFactory() {
    return new RandomFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public SnowflakeID createSnowflakeFactory() {
    int workerId = snowflakeProperties.getWorkerId();
    int dataCenterId = snowflakeProperties.getDataCenterId();
    
    if (snowflakeProperties.getStrategy() == Strategy.twitter) {
      return new TwitterSnowflakeFactory(workerId, dataCenterId);
    }
    
    return new DefaultSnowflakeFactory(workerId);
  }

  @Bean
  @ConditionalOnMissingBean
  public UrlShortenerID createUrlShortenerFactory() {
    return new UrlShortenerFactory();
  }

}
