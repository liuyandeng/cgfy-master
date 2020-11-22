/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 06/11/2020 22:11:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- CREATE DATABASE if not exists test DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
-- ----------------------------
-- ----------------------------
-- Table structure for test_gen
-- ----------------------------
DROP TABLE IF EXISTS `test_gen`;
CREATE TABLE `test_gen`  (
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `mobile_phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `home_add_test` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'cgfy' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_gen
-- ----------------------------
INSERT INTO `test_gen` VALUES ('1', '张三', '男', 25, '1', '1');
INSERT INTO `test_gen` VALUES ('2', '李四', '男', 30, '1', '1');
INSERT INTO `test_gen` VALUES ('3', '王五', '女', 20, '1', NULL);

SET FOREIGN_KEY_CHECKS = 1;
