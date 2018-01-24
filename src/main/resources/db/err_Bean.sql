/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50719
Source Host           : 118.126.100.245:3306
Source Database       : yunsoso

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-01-24 11:46:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for err_bean
-- ----------------------------
DROP TABLE IF EXISTS `err_bean`;
CREATE TABLE `err_bean` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `reason` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `recycle` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
