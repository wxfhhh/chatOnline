/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : oicq

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 20/06/2023 16:36:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `me` bigint NULL DEFAULT NULL,
  `friend` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1663924019973443587 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1663924019973443585, 1661379072804012034, 303);
INSERT INTO `friend` VALUES (1663924019973443586, 303, 1661379072804012034);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `owner_id` bigint NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1668281362450231299 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (1666410313173516289, '开心一家人', 1661379072804012034, 'Wed Jun 07 19:42:33 CST 2023', '/images/group-2.png');
INSERT INTO `group` VALUES (1668281362450231298, '喵星一班', 303, 'Mon Jun 12 23:37:26 CST 2023', '/images/group-3.png');

-- ----------------------------
-- Table structure for group_msg
-- ----------------------------
DROP TABLE IF EXISTS `group_msg`;
CREATE TABLE `group_msg`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` bigint NULL DEFAULT NULL,
  `member_id` bigint NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1671065629517537283 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_msg
-- ----------------------------
INSERT INTO `group_msg` VALUES (1668262615958200322, 1666410313173516289, 1661379072804012034, '1223', '6月12日 22:22');
INSERT INTO `group_msg` VALUES (1668269903049707522, 1666410313173516289, 1661379072804012034, '123', '6月12日 22:51');
INSERT INTO `group_msg` VALUES (1668441554114691073, 1666410313173516289, 303, '567', '6月13日 10:13');
INSERT INTO `group_msg` VALUES (1671047815356223489, 1666410313173516289, 1661379072804012034, '123', '6月20日 14:49');
INSERT INTO `group_msg` VALUES (1671058785512222721, 1666410313173516289, 1661379072804012034, '实验2.docx', '6月20日 15:27');
INSERT INTO `group_msg` VALUES (1671065542221488129, 1666410313173516289, 1661379072804012034, '72966b4a-be23-4184-9d05-43c4b5d97cbf.jpg', '6月20日 16:0');
INSERT INTO `group_msg` VALUES (1671065629517537282, 1666410313173516289, 1661379072804012034, '你好', '6月20日 16:0');

-- ----------------------------
-- Table structure for groupid
-- ----------------------------
DROP TABLE IF EXISTS `groupid`;
CREATE TABLE `groupid`  (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of groupid
-- ----------------------------
INSERT INTO `groupid` VALUES (101);

-- ----------------------------
-- Table structure for lobby
-- ----------------------------
DROP TABLE IF EXISTS `lobby`;
CREATE TABLE `lobby`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 641 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lobby
-- ----------------------------
INSERT INTO `lobby` VALUES (640, '303|眠狼', '/images/user-2.png', 'hhhh', '5月28日 13:40');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` bigint NULL DEFAULT NULL,
  `member_id` bigint NULL DEFAULT NULL,
  `time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1668284965135175683 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1666410313173516290, 1666410313173516289, 1661379072804012034, 'Wed Jun 07 19:42:33 CST 2023');
INSERT INTO `member` VALUES (1668281362450231299, 1668281362450231298, 303, 'Mon Jun 12 23:37:26 CST 2023');
INSERT INTO `member` VALUES (1668284965135175682, 1666410313173516289, 303, 'Mon Jun 12 23:51:45 CST 2023');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `from` bigint NULL DEFAULT NULL,
  `to` bigint NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1670643582890987523 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1670640965053870081, 303, 1661379072804012034, '123');
INSERT INTO `message` VALUES (1670640983542362114, 303, 1661379072804012034, '42c7f5b5-2432-4aed-8a85-05a4efef256d.jpg');
INSERT INTO `message` VALUES (1670641254486011906, 303, 1661379072804012034, '14-312108030103-吴仙凤-模电实验.doc');
INSERT INTO `message` VALUES (1670643115985235970, 1661379072804012034, 303, '不错不错');
INSERT INTO `message` VALUES (1670643582890987522, 303, 1661379072804012034, '可以');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `from` bigint NULL DEFAULT NULL,
  `to` bigint NULL DEFAULT NULL,
  `msg` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `time` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1663924019973443588 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `nickname` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `birthday` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `signature` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `register_time` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1661379072804012035 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (303, '3248857704@qq.com', '眠狼', '8484229', 'Mon May 22 21:11:57 CST 2023', '/images/user-2.png', NULL, NULL, NULL, 'Mon May 22 21:14:39 CST 2023');
INSERT INTO `user` VALUES (1661379072804012034, '123@qq.com', '123', '1234', 'Wed May 24 22:30:12 CST 2023', '/images/user-5.png', NULL, '天天开心!', NULL, 'Mon Jun 12 22:11:48 CST 2023');

-- ----------------------------
-- Table structure for verifycode
-- ----------------------------
DROP TABLE IF EXISTS `verifycode`;
CREATE TABLE `verifycode`  (
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `code` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of verifycode
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
