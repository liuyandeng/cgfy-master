/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : cgfy_web

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 28/11/2020 21:03:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用于唯一标识每一个客户端',
  `resource_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端能访问的资源id集合',
  `client_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端密码',
  `scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指定client的权限范围',
  `authorized_grant_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权模式',
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端重定向uri',
  `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指定用户的权限范围',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT '设置access_token的有效时间(秒)',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT '设置refresh_token有效期(秒)',
  `additional_information` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '值必须是json格式,例如:{\"key\", \"value\"}',
  `autoapprove` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'false/true/read/write,默认false,适用于authorization_code模式,设置用户是否自动approval操作',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'create_time',
  `archived` tinyint(1) NULL DEFAULT NULL COMMENT 'archived',
  `trusted` tinyint(1) NULL DEFAULT NULL COMMENT 'trusted',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oauth_client_details' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('MOBILE', 'oauth2-resource,jbdp-uums,jbdp-oauth,nb-ms', '$2a$12$kW28wQgklzNgrH1TPl51vuB1Y0yMj7KG8Q8D3RsNmm1UCJVUnnDO.', 'read,write', 'password,refresh_token', NULL, 'ROLE_CLIENT', 2880000, 604800, NULL, 'false', '2020-10-13 16:06:47', 0, 0);
INSERT INTO `oauth_client_details` VALUES ('WEB', 'oauth2-resource,jbdp-uums,jbdp-oauth,nb-ms', '$2a$12$kW28wQgklzNgrH1TPl51vuB1Y0yMj7KG8Q8D3RsNmm1UCJVUnnDO.', 'read,write', 'password,refresh_token', NULL, 'ROLE_CLIENT', 2880000, 604800, NULL, 'false', '2020-11-28 19:50:02', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
