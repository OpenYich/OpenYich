# openyich-distributed-id

> A Distributed ID Generator for Spring Boot.

本项目是基于 [Leaf——美团点评分布式ID生成系统](https://tech.meituan.com/MT_Leaf.html) 的开源实现，提供复杂分布式系统ID生成的解决方案。

1. RandomID
2. UrlShortenerID
3. OneTimePasswordID
4. SnowflakeID
5. SegmentID

## 快速开始

在你的 Spring Boot 项目中添加依赖配置：

```
<dependency>
  <groupId>com.github.zhycn</groupId>
  <artifactId>openyich-framework</artifactId>
</dependency>
```

### 1. RandomID

随机ID生成器，可快速生成 UUID、GUID 和 Random 字符串。

```
// 在项目中使用（自动注入）
@Autowired
private RandomID randomID;
```

### 2. UrlShortenerID

短网址生成器。

```
// 在项目中使用（自动注入）
@Autowired
private UrlShortenerID urlShortenerID;
```

### 3. OneTimePasswordID

基于时间的一次性密码（TOTP）生成器。

```
// 在项目中使用（自动注入）
@Autowired
private OneTimePasswordID oneTimePasswordID;
```

### 4. SnowflakeID

基于 Twitter-snowflake 方案的实现。

该接口提供了两个实现类，分别是 DefaultSnowflakeFactory 和 TwitterSnowflakeFactory。它们的区别是：前者只接收一个参数 `workerId`，而后者接收两个参数 `workerId` 和 `dataCenterId`。基于实现类提供了两种选择策略：leaf 和 twitter。

**Leaf 策略（默认）：**

```
# 通过属性文件配置参数：此时，workerId 的取值范围是：0~1023，dataCenterId 设置无效。
openyich.id.snowflake.strategy=leaf
openyich.id.snowflake.worker-id=0
openyich.id.snowflake.data-center-id=0
```

**Twitter 策略：**

```
# 通过属性文件配置参数：此时，workerId 和 dataCenterId 的取值范围都是：0~31。
openyich.id.snowflake.strategy=twitter
openyich.id.snowflake.worker-id=0
openyich.id.snowflake.data-center-id=0
```

> 注意：如果不是多机房部署环境，使用默认即可。

```
// 在项目中使用（自动注入）
@Autowired
private SnowflakeID snowflakeID;
```

### 5. SegmentID

基于美团 Leaf-segment 数据库方案的实现。

> 在使用时，需要配置一个数据源。如果是多数据源，则使用 @Primary 标记的数据源将作为默认数据源。

由于依赖数据库的缘故，需要手动打开配置 `@EnableSegment`：

```
@SpringBootApplication
@EnableSegment // 打开配置
public class DemoApplication {

}
```

同时，在 POM 文件中添加 JPA 依赖配置：

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

然后，在数据源对应的数据库中创建表：

```
-- 基于美团 Leaf-Segment 数据库方案的表结构设计
CREATE TABLE `leaf_segment` (
	`biz_tag` VARCHAR(128) NOT NULL COMMENT '业务标签',
	`max_id` BIGINT(20) NOT NULL DEFAULT '1' COMMENT '当前被分配的最大值',
	`step` INT(11) NOT NULL COMMENT '每次分配的号段长度',
	`description` VARCHAR(256) NULL DEFAULT NULL COMMENT '描述信息',
	`created` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
	`last_modified` TIMESTAMP NULL DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (`biz_tag`)
)
COMMENT='Leaf-Segment'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
```

> 以上是 MySQL 的数据库脚本，其他数据库则作相应的修改即可。

接下来，在项目中配置业务标签：

```
# 是否使用异步加载（默认值：True）
openyich.id.segment.asyn-loading=true

# 设置业务标签（支持设置多个业务标签）
openyich.id.segment.endpoints.one.biz-tag=test
openyich.id.segment.endpoints.one.start-id=1
openyich.id.segment.endpoints.one.step=20
openyich.id.segment.endpoints.one.description=Test Description

# 业务标签二（startId 和 description 不是必须的）
openyich.id.segment.endpoints.two.biz-tag=order_test
openyich.id.segment.endpoints.two.step=20
```

- startId 默认值为：1。
- description 默认值为：No Description。

配置完成后，即可在项目中使用：

```
// 在项目中使用（自动注入）
@Autowired
private SegmentID segmentID;
```