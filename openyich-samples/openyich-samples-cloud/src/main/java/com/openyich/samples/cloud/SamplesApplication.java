package com.openyich.samples.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SamplesApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SamplesApplication.class, args);
  }
  
  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    // @formatter:off
    return builder.routes().route("path_route", r -> r.path("/get").uri("http://httpbin.org"))
        .build();
    // @formatter:on
  }

}
