/*
Navicat MySQL Data Transfer

Source Server         : HOSTUS
Source Server Version : 50720
Source Host           : 103.11.65.253:3306
Source Database       : yunsoso

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-05 17:03:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yun_bean
-- ----------------------------
DROP TABLE IF EXISTS `yun_bean`;
CREATE TABLE `yun_bean` (
  `id` varchar(36) NOT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `format` varchar(10) DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `size_format` varchar(10) DEFAULT NULL,
  `author` varchar(8) DEFAULT NULL,
  `keyWord` varchar(50) DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `failure_date` timestamp NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `recycle` VARCHAR(2)	NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
