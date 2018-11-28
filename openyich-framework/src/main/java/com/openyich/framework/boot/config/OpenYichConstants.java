package com.openyich.framework.boot.config;

/**
 * OpenYich constants
 * 
 * @author zhycn
 * @since 2.1.0 2018-11-09
 */
public interface OpenYichConstants {

  // Spring profiles for development, test and production
  String SPRING_PROFILE_DEVELOPMENT = "dev";
  String SPRING_PROFILE_TEST = "test";
  String SPRING_PROFILE_PRODUCTION = "prod";

  // Spring profile used to enable Spring scheduling
  String SPRING_PROFILE_TASK = "task";

  // Spring profile used when deploying with Spring Cloud
  String SPRING_PROFILE_CLOUD = "cloud";
  
  // Spring profile used when deploying to Heroku
  String SPRING_PROFILE_HEROKU = "heroku";

  // Spring profile used to enable swagger
  String SPRING_PROFILE_SWAGGER = "swagger";

  // Spring profile used when deploying to Kubernetes and OpenShift
  String SPRING_PROFILE_K8S = "k8s";

}
