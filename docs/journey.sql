create database journey;
use journey;

#存储语音包信息
create table voice(
  id int primary key AUTO_INCREMENT,
  name char(80),
  url char(80)  #下载地址
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE journeyNote (
	noteId char(36) NOT NULL,
	title char(50) null,	#标题
	userId int null,	#作者
	content BLOB null,		#正文
	tag char(50) null,		#标签
	category char(50) null,	#分类
	departureDate datetime null,#出发日期
	travelDays char(20) null,		#出行天数
	human int null,				#人物
	fee char(10),				#人均费用
	updateTime datetime null,	#更新时间
	PRIMARY KEY (`noteId`),
	KEY `userId` (`userId`),
	CONSTRAINT `journeyNote_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#游记文件
create table noteFile(
	fileId char(36) not null,
	userId char(36) not null,
	url char(80) null,#文件URL
	uploadTime timestamp null,	#上传时间
	PRIMARY KEY (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#游记评论
create table noteComment(
	cid int not null,
	pid int not null,
	noteId char(32) not null,
	content char(255) not null,
	createTime datetime null,
	PRIMARY KEY (`cid`,pid),
	KEY `noteId` (`noteId`),
	CONSTRAINT `noteComment_ibfk_1` FOREIGN KEY (`noteId`) REFERENCES `journeyNote` (`noteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#数据字典 
create table systemddl(
	seqID int not null primary key,		#主键ID(自增长)
	keyword varchar(20) null,		#数据类型
	ddlCode int null,		#数据项的code
	ddlName varchar(50) null		#数据项的value
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `systemddl` VALUES ('1', '人物', '1', '一个人');
INSERT INTO `systemddl` VALUES ('2', '人物', '2', '小两口');
INSERT INTO `systemddl` VALUES ('3', '人物', '3', '带孩子');
INSERT INTO `systemddl` VALUES ('4', '人物', '4', '家族出游');
INSERT INTO `systemddl` VALUES ('5', '人物', '5', '和朋友');
INSERT INTO `systemddl` VALUES ('6', '人物', '5', '其他');
INSERT INTO `systemddl` VALUES ('7', '景点类型', '101', '古镇乡村');
INSERT INTO `systemddl` VALUES ('8', '景点类型', '102', '自然景观');
INSERT INTO `systemddl` VALUES ('9', '景点类型', '103', '人文景观');
INSERT INTO `systemddl` VALUES ('10', '景点类型', '104', '展馆');
INSERT INTO `systemddl` VALUES ('11', '景点类型', '105', '休闲度假');
INSERT INTO `systemddl` VALUES ('12', '景点类型', '106', '其他');
INSERT INTO `systemddl` VALUES ('13', '景点类型', '107', '宗教');
INSERT INTO `systemddl` VALUES ('14', '景点类型', '108', '大学');




CREATE TABLE `sitetype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `info` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sitetype` VALUES ('101', '古镇乡村');
INSERT INTO `sitetype` VALUES ('102', '自然景观');
INSERT INTO `sitetype` VALUES ('103', '人文景观');
INSERT INTO `sitetype` VALUES ('104', '展馆');
INSERT INTO `sitetype` VALUES ('105', '休闲度假');
INSERT INTO `sitetype` VALUES ('106', '其他');
INSERT INTO `sitetype` VALUES ('107', '宗教');
INSERT INTO `sitetype` VALUES ('108', '大学');

CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `wechatId` char(50) unique,
  `weiboId` char(50) unique,
  `qqId` char(50) unique,
  `username` char(20) unique, -- 类似微信号，可用于登陆，但一开始未设置，一旦设置不可更改
  `nickname` char(20) NOT NULL,
  `password` char(32),  -- 32位md5加密过的
  `authority` int DEFAULT '0', -- 0表示普通用户
  `score` int DEFAULT '0', -- 积分
  `level` int DEFAULT '1', -- 等级
  `sex` char(1) DEFAULT 'm', -- m/f/s 分别表示 男/女/秘密
  `birthday` char(10), -- eg:1990-10-10
  `phone` char(11) unique,
  `email` char(60) unique,
  `image` char(60), -- 头像地址
  `registtime` char(10) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sitethird` (
  `id` int NOT NULL AUTO_INCREMENT,
  `province` char(20),
  `city` char(20),
  `cityId` char(10),
  `firstSiteName` char(30),
  `firstSiteId` char(20),
  `secondSiteName` char(30),
  `secondSiteId` char(20),
  `thirdSiteName` char(30),
  `thirdSiteId` char(20),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sitesecond` (
  `id` int NOT NULL AUTO_INCREMENT,
  `province` char(20),
  `city` char(20),
  `cityId` char(10),
  `firstSiteName` char(30),
  `firstSiteId` char(20),
  `SecondSiteName` char(30),
  `SecondSiteId` char(20),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `followers` (
  `userId` int,
  `followersId` int,
  PRIMARY KEY (`userId`,`followersId`),
  FOREIGN KEY (`followersId`) REFERENCES `user` (`userId`),
  FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `following` (
  `userId` int,
  `followingId` int,
  PRIMARY KEY (`userId`,`followingId`),
  FOREIGN KEY (`followingId`) REFERENCES `user` (`userId`),
  FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `site` (
  `id` int NOT NULL AUTO_INCREMENT,
  `province` char(20),
  `city` char(20),
  `cityId` char(10),
  `siteName` char(30),
  `siteEname` char(80),
  `siteId` char(20),
  `siteLevel` char(1),
  `type` int,
  `outdoor` boolean,
  `qunar` char(100),
  `longitude` char(15),
  `latitude` char(15),
  `img` char(150),
  `description` char(255),
  `address` char(255),
  `phone` char(60),
  `website` char(100),
  `opentime` char(255),
  `price` char(255),
  `season` varchar(255),
  `traffic` varchar(255),
  `tips` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
