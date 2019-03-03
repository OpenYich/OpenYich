# OpenYich BOM for all Spring Boot projects

This project provides a pom file that you can either import into your Spring Boot Application's bom, or use as your Spring Boot Application's parent pom. 

- JDK 8+
- Spring Boot 2.1.2.RELEASE
- Spring Cloud Greenwich.RELEASE
- Spring IO Platform Cairo-SR6

## Quick Start

The OpenYich BOM uses Maven's support for dependency management to provide dependency versions to your Spring Boot Application's build. To consume this dependency management you can import into your Spring Boot Application's pom: 

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.github.zhycn</groupId>
            <artifactId>openyich-dependencies</artifactId>
            <version>2.1.10</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Alternatively, rather than importing the OpenYich BOM, you may prefer to use it as your Spring Boot Application pom's parent: 

```
<parent>
    <groupId>com.github.zhycn</groupId>
    <artifactId>openyich-dependencies</artifactId>
    <version>2.1.10</version>
    <relativePath/>
</parent>
```

## Overriding a version

To override a property in Maven you can then declare the property in your pom’s `<properties>` section with the desired value:

```
<properties>
    <commons-lang3.version>3.8.1</commons-lang3.version>
</properties>
```