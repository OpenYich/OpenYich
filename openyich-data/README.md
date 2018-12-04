# OpenYich Data

A data module built on Spring Data JPA.

## Quick Start

Importing the dependency into your Spring Boot Application's pom:

```
<dependency>
    <groupId>com.github.zhycn</groupId>
    <artifactId>openyich-data</artifactId>
</dependency>
```

Usage:

```
@SpringBootApplication
@EnableJpaRepositories({"com.openyich.framework.demo.repository"})
@EntityScan({"com.openyich.framework.demo.domain"})
public class OpenyichApplication {

}
```

## Configuration Properties

Main configuration properties:

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/openyich?useUnicode=true&characterEncoding=utf8&autoReconnect=true&autoReconnectForPools=true&noAccessToProcedureBodies=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull
    username: openyich
    password: openyich
    druid:
      initial-size: 2
      min-idle: 8
      max-active: 8
      max-wait: 2000
      validation-query: select 'x'
      validation-query-timeout: 1
      keep-alive: true
```