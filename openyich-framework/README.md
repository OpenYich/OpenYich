# OpenYich Framework

This project is used by the OpenYich server-side library, it provides more common services and supports auto configurations. 

## Quick Start

Importing the dependency into your Spring Boot Application's pom:

```
<dependency>
    <groupId>com.github.zhycn</groupId>
    <artifactId>openyich-framework</artifactId>
</dependency>
```

### Using Spring Data JPA

1\. Importing the JPA's dependencies into your Spring Boot Application's pom:

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
```

2\. Using @EnableJpaRepository

```
@SpringBootApplication
@EnableJpaRepository
public class OpenyichCMSApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenyichCMSApplication.class, args);
  }

}
```

3\. Add configuration properties: 

```
## MySQL Connector/J 6.0.6
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mysqldb?useUnicode=true&characterEncoding=utf8&useSSL=true&autoReconnect=true&autoReconnectForPools=true&noAccessToProcedureBodies=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC
    username: root
    password: 
```

