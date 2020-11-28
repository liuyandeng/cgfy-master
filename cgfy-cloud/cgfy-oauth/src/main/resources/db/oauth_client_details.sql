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

 Date: 28/11/2020 19:51:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'client_id',
  `resource_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'resource_ids',
  `client_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'client_secret',
  `scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'scope',
  `authorized_grant_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'authorized_grant_types',
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'web_server_redirect_uri',
  `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'authorities',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT 'access_token_validity',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT 'refresh_token_validity',
  `additional_information` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'additional_information',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'create_time',
  `archived` tinyint(1) NULL DEFAULT NULL COMMENT 'archived',
  `trusted` tinyint(1) NULL DEFAULT NULL COMMENT 'trusted',
  `autoapprove` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'autoapprove',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oauth_client_details' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('MOBILE', 'oauth2-resource,jbdp-uums,jbdp-oauth,nb-ms', '$2a$12$kW28wQgklzNgrH1TPl51vuB1Y0yMj7KG8Q8D3RsNmm1UCJVUnnDO.', 'read,write', 'password,refresh_token', NULL, 'ROLE_CLIENT', 2880000, 604800, NULL, '2020-10-13 16:06:47', 0, 0, 'false');
INSERT INTO `oauth_client_details` VALUES ('WEB', 'oauth2-resource,jbdp-uums,jbdp-oauth,nb-ms', '$2a$12$kW28wQgklzNgrH1TPl51vuB1Y0yMj7KG8Q8D3RsNmm1UCJVUnnDO.', 'read,write', 'password,refresh_token', NULL, 'ROLE_CLIENT', 2880000, 604800, NULL, '2020-11-28 19:50:02', 0, 0, 'false');

SET FOREIGN_KEY_CHECKS = 1;
