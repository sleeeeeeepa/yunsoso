/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50721
Source Host           : 118.126.100.245:3306
Source Database       : yunsoso

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-01-23 17:29:55
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
  `key_word` varchar(50) DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT NULL,
  `failure_date` timestamp NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `recycle` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
