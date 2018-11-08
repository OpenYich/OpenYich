# OpenYich

This project provides a pom file that you can either import into your Spring Boot Application's bom, or use as your Spring Boot Application's parent pom. 


## Quick Start

The OpenYich BOM uses Maven's support for dependency management to provide dependency versions to your Spring Boot Application's build. To consume this dependency management you can import into your Spring Boot Application's pom: 


```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.github.zhycn</groupId>
            <artifactId>openyich-dependencies</artifactId>
            <version>2.1.0-SNAPSHOT</version>
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
	<version>2.1.0-SNAPSHOT</version>
	<relativePath/>
</parent>
```