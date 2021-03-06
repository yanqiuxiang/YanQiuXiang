/*
SQLyog v10.2 
MySQL - 5.6.17 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `USERS` (
	  USER_ID int(12) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	  USER_ACCOUNT varchar(16) NOT NULL COMMENT '帐号',
	  USER_PASSWORD varchar(32) DEFAULT NULL COMMENT '密码',
	  USER_NAME varchar(40) DEFAULT NULL COMMENT '用户名',
	  ROLE_ID int(5) NOT NULL DEFAULT '0' COMMENT '角色ID(默认为0，无权限)',
	  USER_MAIL varchar(255) DEFAULT NULL COMMENT '邮箱',
	  USER_PHONE bigint(11) DEFAULT NULL COMMENT '手机号码',
	  USER_ROLE varchar(255) DEFAULT NULL COMMENT '角色说明',
	  USER_CREATDATE datetime DEFAULT NULL COMMENT '注册时间',
	  USER_LASTDATE datetime DEFAULT NULL COMMENT '登录时间',
	  USER_STATUS int(1) NOT NULL DEFAULT '0' COMMENT '状态（0启用 1禁用）',
	  USER_CREATE_STATUS bigint(12) DEFAULT '0' COMMENT '用户新增权限',
	  PRIMARY KEY (`USER_ID`)
); 
insert into `USERS` (`USERS_ID`, `USERS_ACCOUNT`, `USERS_PASSWORD`, `USERS_NAME`, `ROLE_ID`, `USERS_MAIL`, `USERS_PHONE`, `USERS_ROLE`, `USERS_CREATDATE`, `USERS_LASTDATE`, `USERS_STATUS`, `USERS_CREATE_STATUS`) values('0','admin8','e10adc3949ba59abbe56e057f20f883e','developer@legentec.com','1','developer@legentec.com','13576909053','24242342341','2014-06-16 00:00:00','2019-02-12 11:16:05','0','0');
insert into `USERS` (`USERS_ID`, `USERS_ACCOUNT`, `USERS_PASSWORD`, `USERS_NAME`, `ROLE_ID`, `USERS_MAIL`, `USERS_PHONE`, `USERS_ROLE`, `USERS_CREATDATE`, `USERS_LASTDATE`, `USERS_STATUS`, `USERS_CREATE_STATUS`) values('13','yanqiuxiang','e10adc3949ba59abbe56e057f20f883e','鄢秋祥','47','271785410@qq.com','13576909053','111','2018-09-03 11:22:44','2019-01-15 10:51:47','0','0');
insert into `USERS` (`USERS_ID`, `USERS_ACCOUNT`, `USERS_PASSWORD`, `USERS_NAME`, `ROLE_ID`, `USERS_MAIL`, `USERS_PHONE`, `USERS_ROLE`, `USERS_CREATDATE`, `USERS_LASTDATE`, `USERS_STATUS`, `USERS_CREATE_STATUS`) values('14','rrrrrr','ff2f24f8b6d253bb5a8bc55728ca7372','rrrrrr','54','rrrr@qq.com','13012345678','rrrrr','2018-10-11 17:16:02',NULL,'0','0');
insert into `USERS` (`USERS_ID`, `USERS_ACCOUNT`, `USERS_PASSWORD`, `USERS_NAME`, `ROLE_ID`, `USERS_MAIL`, `USERS_PHONE`, `USERS_ROLE`, `USERS_CREATDATE`, `USERS_LASTDATE`, `USERS_STATUS`, `USERS_CREATE_STATUS`) values('15','222222','e3ceb5881a0a1fdaad01296d7554868d','222222','48','2222@qq.com','13012345678','22222','2018-11-01 11:43:07',NULL,'0','0');
insert into `USERS` (`USERS_ID`, `USERS_ACCOUNT`, `USERS_PASSWORD`, `USERS_NAME`, `ROLE_ID`, `USERS_MAIL`, `USERS_PHONE`, `USERS_ROLE`, `USERS_CREATDATE`, `USERS_LASTDATE`, `USERS_STATUS`, `USERS_CREATE_STATUS`) values('16','1111','7fa8282ad93047a4d6fe6111c93b308a','1111','48','111@qq.com','13012345678','11','2018-11-01 11:45:20',NULL,'0','0');
