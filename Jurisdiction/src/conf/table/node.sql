/*
SQLyog v10.2 
MySQL - 5.6.17 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `NODE` (
	 NODE_ID int(5) NOT NULL AUTO_INCREMENT,
	  NODE_PID int(5) DEFAULT NULL,
	  NODE_TITLE varchar(150) DEFAULT NULL,
	  NODE_URL varchar(1500) DEFAULT NULL,
	  NODE_ICO varchar(150) DEFAULT NULL,
	  NODE_STATUS int(1) DEFAULT NULL,
	  NODE_SORT int(5) DEFAULT NULL,
	  NODE_REMARK varchar(765) DEFAULT NULL,
	  PRIMARY KEY (`NODE_ID`)
); 
insert into `NODE` (`NODE_ID`, `NODE_PID`, `NODE_TITLE`, `NODE_URL`, `NODE_ICO`, `NODE_STATUS`, `NODE_SORT`, `NODE_REMARK`) values('1','0','系统菜单','','','0','0','');
insert into `NODE` (`NODE_ID`, `NODE_PID`, `NODE_TITLE`, `NODE_URL`, `NODE_ICO`, `NODE_STATUS`, `NODE_SORT`, `NODE_REMARK`) values('2','1','权限管理','','','0','5','');
insert into `NODE` (`NODE_ID`, `NODE_PID`, `NODE_TITLE`, `NODE_URL`, `NODE_ICO`, `NODE_STATUS`, `NODE_SORT`, `NODE_REMARK`) values('3','2','用户管理','/adminUser/userView','','0','1','');
insert into `NODE` (`NODE_ID`, `NODE_PID`, `NODE_TITLE`, `NODE_URL`, `NODE_ICO`, `NODE_STATUS`, `NODE_SORT`, `NODE_REMARK`) values('4','2','节点管理','/adminNode/nodeView','','0','2','');
insert into `NODE` (`NODE_ID`, `NODE_PID`, `NODE_TITLE`, `NODE_URL`, `NODE_ICO`, `NODE_STATUS`, `NODE_SORT`, `NODE_REMARK`) values('5','2','角色管理','/adminRole/roleView','','0','3','');

