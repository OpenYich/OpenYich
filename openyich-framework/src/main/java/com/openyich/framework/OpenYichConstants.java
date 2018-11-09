package com.openyich.framework;

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

  // Spring profile for task
  String SPRING_PROFILE_TASK = "task";

  // Spring profile used when deploying with Spring Cloud
  String SPRING_PROFILE_CLOUD = "cloud";

  // Spring profile used to enable swagger
  String SPRING_PROFILE_SWAGGER = "swagger";

  // Spring profile used when deploying to Kubernetes and OpenShift
  String SPRING_PROFILE_K8S = "k8s";

}
