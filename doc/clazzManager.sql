/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 127.0.0.1:3306
 Source Schema         : clazzmanager

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 05/01/2020 02:42:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `grade_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '年级id',
  `subject_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学科id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点id',
  `root_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '根节点id',
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT '版本',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '默认时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '默认时间',
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '一年级', '2019-12-22 16:00:44', 1);
INSERT INTO `grade` VALUES ('2', '二年级', '2019-12-22 22:45:33', 1);

-- ----------------------------
-- Table structure for lesson_plan
-- ----------------------------
DROP TABLE IF EXISTS `lesson_plan`;
CREATE TABLE `lesson_plan`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `section_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节id',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '人员id',
  `lesson_plan_text` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '在线文档内容',
  `ppt_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传文件的ppt名字',
  `ppt_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ppt附件地址',
  `ppt_file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传后的ppt文件名',
  `exercises_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传的练习题名字',
  `exercises_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '练习题附件地址',
  `exercises_file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传后的练习题名字',
  `version` bigint(20) NULL DEFAULT 1 COMMENT '版本',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '默认时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lesson_plan
-- ----------------------------
INSERT INTO `lesson_plan` VALUES ('1', '1', '1', '1', '', NULL, NULL, NULL, NULL, NULL, 1, '2020-01-04 19:49:02');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT '版本',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '备课组长', 1, '2019-12-15 11:31:50');
INSERT INTO `role` VALUES ('2', '教师', 1, '2019-12-15 11:32:00');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT '版本',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '默认时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', '语文', 1, '2019-12-22 16:01:03');
INSERT INTO `subject` VALUES ('2', '数学', 1, '2019-12-22 22:45:58');
INSERT INTO `subject` VALUES ('3', '英语', 1, '2019-12-22 22:45:58');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `job_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工号',
  `group_leader_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否为组长1是 0否',
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT '版本',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '系统时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `jobNumber`(`job_number`) USING BTREE COMMENT '工号唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('-1', '管理员', 'admin', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1205757841722445824', '测试1', '0001', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1205783813582622720', '测试2', '0002', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1205789169054519296', '测试3', '003', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1205790695005229056', '测试4', '004', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1205792066039320576', '测试5', '005', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1205792225699696640', '测试5', '006', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1205792388690350080', '测试5', '007', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1206072891460947968', '测试9', '009', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1208673134933315584', '测试组长', '010', 1, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1209501088080859136', '雪花', '012', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1209512570877251584', '陈杭3', 'chenahng3', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1209512824099966976', '陈杭4', 'chenahng4', 1, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1211193966385565696', 'security', '00010', 0, 1, '2020-01-01 02:02:08');
INSERT INTO `user` VALUES ('1211224417040994304', '权限测试用户', 'user', 0, 1, '2020-01-01 02:02:08');

-- ----------------------------
-- Table structure for user_grade_subject
-- ----------------------------
DROP TABLE IF EXISTS `user_grade_subject`;
CREATE TABLE `user_grade_subject`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '人员id',
  `grade_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年级id',
  `subject_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学科id',
  `version` bigint(20) NOT NULL DEFAULT 1 COMMENT '版本',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '默认时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_grade_subject_user_id_grade_id_subject_id_uindex`(`user_id`, `grade_id`, `subject_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_grade_subject
-- ----------------------------
INSERT INTO `user_grade_subject` VALUES ('1', '1205757841722445824', '1', '1', 1, '2019-12-22 16:01:08');
INSERT INTO `user_grade_subject` VALUES ('2', '1205757841722445824', '1', '2', 1, '2019-12-22 16:30:38');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '共用user表id',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密文密码',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '默认时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('-1', '$2a$10$J/pss9izgn6ECwXRJYSQk.0bQU5lkrB7v9wEvHkfbluij.iVXrFZ2', '2020-01-04 12:14:02');
INSERT INTO `user_password` VALUES ('1205757841722445824', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-14 15:54:06');
INSERT INTO `user_password` VALUES ('1205783813582622720', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-14 17:37:18');
INSERT INTO `user_password` VALUES ('1205789169054519296', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-14 17:58:35');
INSERT INTO `user_password` VALUES ('1205790695005229056', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-14 18:05:47');
INSERT INTO `user_password` VALUES ('1205792066039320576', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-14 18:10:05');
INSERT INTO `user_password` VALUES ('1205792225699696640', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-14 18:10:43');
INSERT INTO `user_password` VALUES ('1205792388690350080', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-14 18:11:22');
INSERT INTO `user_password` VALUES ('1206072891460947968', 'string', '2019-12-15 12:46:52');
INSERT INTO `user_password` VALUES ('1208673134933315584', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-22 16:58:26');
INSERT INTO `user_password` VALUES ('1209501088080859136', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-24 23:48:25');
INSERT INTO `user_password` VALUES ('1209512570877251584', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-25 00:34:20');
INSERT INTO `user_password` VALUES ('1209512824099966976', '07dc1d04570d8faaeb0b1e763d6d9546', '2019-12-25 00:35:25');
INSERT INTO `user_password` VALUES ('1211193966385565696', '$2a$10$AHPmCfGuBYoXm1mk75ySkebezGtoyVmd6mp089eZtZz2c.cvUV96.', '2019-12-29 15:55:19');
INSERT INTO `user_password` VALUES ('1211224417040994304', '$2a$10$NqM55UvYsco7xeWnyJSFGOyUDDVblI.r80xtGR1qAk70Iuu7uc7NO', '2020-01-04 12:12:32');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '人员id',
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `def_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '默认时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_role_id`(`user_id`, `role_id`) USING BTREE COMMENT '用户角色id不重复'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1205757844725567488', '1205757841722445824', '1', '2019-12-14 15:54:06');
INSERT INTO `user_role` VALUES ('1205757844725567489', '1205757841722445824', '2', '2019-12-14 15:54:06');
INSERT INTO `user_role` VALUES ('1205789170476388352', '1205789169054519296', '1', '2019-12-14 17:58:35');
INSERT INTO `user_role` VALUES ('1205789170476388353', '1205789169054519296', '5', '2019-12-14 17:58:35');
INSERT INTO `user_role` VALUES ('1205790895157415936', '1205790695005229056', '0', '2019-12-14 18:05:47');
INSERT INTO `user_role` VALUES ('1205792066186121216', '1205792066039320576', '0', '2019-12-14 18:10:05');
INSERT INTO `user_role` VALUES ('1205792225724862464', '1205792225699696640', '0', '2019-12-14 18:10:43');
INSERT INTO `user_role` VALUES ('1208673136824946688', '1208673134933315584', '2', '2019-12-22 16:58:26');
INSERT INTO `user_role` VALUES ('1208673136824946689', '1208673134933315584', '1', '2019-12-22 16:58:26');
INSERT INTO `user_role` VALUES ('1209501088282185728', '1209501088080859136', '2', '2019-12-24 23:48:25');
INSERT INTO `user_role` VALUES ('1209512626124623872', '1209512570877251584', '2', '2019-12-25 00:34:20');
INSERT INTO `user_role` VALUES ('1209512874150596608', '1209512824099966976', '2', '2019-12-25 00:35:25');
INSERT INTO `user_role` VALUES ('1209512882748919808', '1209512824099966976', '1', '2019-12-25 00:35:25');
INSERT INTO `user_role` VALUES ('1211193968415608832', '1211193966385565696', '2', '2019-12-29 15:55:19');
INSERT INTO `user_role` VALUES ('1211224418106347520', '1211224417040994304', '2', '2019-12-29 17:56:19');

SET FOREIGN_KEY_CHECKS = 1;
