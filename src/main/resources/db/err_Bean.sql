/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50721
Source Host           : 118.126.100.245:3306
Source Database       : yunsoso

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-01-23 16:44:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for err_Bean
-- ----------------------------
DROP TABLE IF EXISTS `err_Bean`;
CREATE TABLE `err_Bean` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `recycle` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
