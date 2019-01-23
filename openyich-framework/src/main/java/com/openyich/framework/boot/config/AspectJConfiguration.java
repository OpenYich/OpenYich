package com.openyich.framework.boot.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.openyich.framework.boot.aspectj.interceptor.StopWatchInterceptor;
import com.openyich.framework.boot.autoconfigure.OpenYichProperties;

@Configuration
@ConditionalOnWebApplication
@EnableAspectJAutoProxy
@AutoConfigureAfter(OpenYichProperties.class)
@Import({
  StopWatchInterceptor.class
})
public class AspectJConfiguration {

}
