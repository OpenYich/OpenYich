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
