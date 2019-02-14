/*
SQLyog v10.2 
MySQL - 5.6.17 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `ROLE` (
	 ROLE_ID int(5) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
	  ROLE_NAME varchar(20) DEFAULT NULL COMMENT '角色名称',
	  ROLE_ACCESS mediumtext COMMENT '具体权限说明',
	  ROLE_STATUS int(1) DEFAULT NULL COMMENT '角色状态',
	  PERMISSION_ONE bigint(11) DEFAULT '0' COMMENT '权限1',
	  PERMISSION_TWO bigint(11) DEFAULT '0' COMMENT '权限2',
	  ROLE_REMARK varchar(255) DEFAULT NULL COMMENT '角色说明',
	  PRIMARY KEY (`ROLE_ID`)
); 
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('1','超级管理员','<CLOB>','0','7','0','',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('47','测试1','系统菜单,权限管理,用户管理,节点管理,角色管理,配置信息,户型分类,面积分类,风格分类,百科分类,施工状态,职务信息,积分分类,日常操作,楼盘信息,门店信息,员工信息,工地管理,基本配置,业主信息,案例活动,装修百科,星钻案例,最新活动,用户互动,我要报修,我的积分,装修分期,免费量房','0','1','0','123',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('48','123','角色管理','0','0','0','',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('49','123','角色管理','0','0','0','',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('50','2222','户型,面积','0','0','0','',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('51','rrrr','节点管理,户型分类','0','1','0','rrr',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('52','2222','角色管理,面积','0','0','0','222',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('53','ffffffffff','用户管理,面积','0','0','0','fffffffffffff',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('54','dddddddddddd','角色管理,户型','0','0','0','ddddddddddd',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('55','ee','装修配置,户型,风格','0','0','0','ee',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('56','eeeeeeeeee','用户管理,户型分类','0','0','0','eeeeeeeee',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('57','111111','用户管理,户型分类,风格分类','0','0','0','1111',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('62','abc','bbb','0','1','0','ccc',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('63','abc','bbb','0','1','0','ccc',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('65','abc','bbb','0','1','0','ccc',NULL);
insert into `ROLE` (`ROLE_ID`, `ROLE_NAME`, `ROLE_ACCESS`, `ROLE_STATUS`, `PERMISSION_ONE`, `PERMISSION_TWO`, `ROLE_REMARK`, `RRR`) values('66','1111','节点管理,户型分类','0','1','0','1111',NULL);
