create table `cst_customer`(
	`cust_id` bigint(32) not null auto_increment comment '客户编号（主键）',
	`cust_name` varchar(32) not null comment '客户名称（公司名称）',
	`cust_source` varchar(32) default null comment '客户信息来源',
	`cust_industry` varchar(32) default null comment '客户所属行业',
	`cust_level` varchar(32) default null comment '客户级别',
	`cust_address` varchar(128) default null comment '客户联系地址',
	`cust_phone` varchar(64) default null comment '客户联系电话',
	primary key (`cust_id`)
) engine=InnoDB auto_increment=1 default charset=utf8;