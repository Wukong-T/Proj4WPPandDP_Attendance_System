/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : attendance

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 01/06/2021 21:15:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attend
-- ----------------------------
DROP TABLE IF EXISTS `attend`;
CREATE TABLE `attend`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `early` int(255) NOT NULL,
  `late` int(255) NOT NULL,
  `vacation` int(255) NOT NULL,
  `truancy` int(255) NOT NULL,
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `propose` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attend
-- ----------------------------
INSERT INTO `attend` VALUES ('2021229091', '石善炜', '170814', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3115001317', '陆世辉', '170814', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3116003004', '全俊龙', '170814', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3116003681', '苏钊全', '170814', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3117000780', '蔡治全', '170814', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3117000984', '曾海鹏', '170814', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3117001069', '李嘉良', '170814', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3117002325', '蔡文雨', '170813', '产品开发部', 3, 1, 0, 2, '81', NULL);
INSERT INTO `attend` VALUES ('3117002327', '邱垂淼', '170813', '产品开发部', 3, 1, 0, 2, '81', NULL);
INSERT INTO `attend` VALUES ('3117002332', '吴泳', '170813', '产品开发部', 3, 1, 0, 2, '81', NULL);
INSERT INTO `attend` VALUES ('3117002347', '严俊哲', '170601', '财务部', 0, 1, 0, 1, '92', NULL);
INSERT INTO `attend` VALUES ('3117002356', '覃伟浩', '170813', '产品开发部', 3, 2, 0, 2, '78', NULL);
INSERT INTO `attend` VALUES ('3117002407', '梁庆昭', '170813', '产品开发部', 3, 2, 0, 2, '78', NULL);
INSERT INTO `attend` VALUES ('3117002443', '陈金伙', '170813', '产品开发部', 3, 2, 0, 2, '78', NULL);
INSERT INTO `attend` VALUES ('3117002481', '施浚扬', '170813', '产品开发部', 3, 2, 0, 2, '78', NULL);
INSERT INTO `attend` VALUES ('3117002498', '刘东林', '170813', '产品开发部', 3, 2, 0, 2, '78', NULL);
INSERT INTO `attend` VALUES ('3117002519', '张永煊', '170601', '财务部', 1, 0, 0, 1, '93', NULL);
INSERT INTO `attend` VALUES ('3117002560', '石正生', '170813', '产品开发部', 3, 2, 0, 2, '78', NULL);
INSERT INTO `attend` VALUES ('3117002570', '廖成瑾', '170813', '产品开发部', 3, 2, 0, 2, '78', NULL);
INSERT INTO `attend` VALUES ('3117002585', '廖俊杰', '170813', '产品开发部', 3, 2, 0, 1, '83', NULL);
INSERT INTO `attend` VALUES ('3117002685', '陈瑞禧', '170813', '产品开发部', 3, 2, 0, 1, '83', NULL);
INSERT INTO `attend` VALUES ('3117002713', '郭援', '170813', '产品开发部', 3, 2, 0, 1, '83', NULL);
INSERT INTO `attend` VALUES ('3117002786', '江志锋', '170813', '产品开发部', 3, 2, 0, 3, '73', NULL);
INSERT INTO `attend` VALUES ('3117002972', '陈锦鸿', '170813', '产品开发部', 3, 2, 0, 2, '78', NULL);
INSERT INTO `attend` VALUES ('3117002988', '严树洁', '170813', '产品开发部', 3, 2, 0, 1, '83', NULL);
INSERT INTO `attend` VALUES ('3117003012', '陈弘毅', '170813', '产品开发部', 3, 1, 0, 3, '76', NULL);
INSERT INTO `attend` VALUES ('3117003017', '梁嘉诚', '170813', '产品开发部', 3, 1, 0, 2, '81', NULL);
INSERT INTO `attend` VALUES ('3117003026', '林俊安', '170813', '产品开发部', 3, 1, 0, 2, '81', NULL);
INSERT INTO `attend` VALUES ('3117003038', '胡正尧', '170813', '产品开发部', 3, 4, 0, 2, '72', NULL);
INSERT INTO `attend` VALUES ('3117003103', '梁峰', '170813', '产品开发部', 3, 4, 0, 2, '72', NULL);
INSERT INTO `attend` VALUES ('3117003107', '蔡长林', '170813', '产品开发部', 3, 4, 0, 3, '67', NULL);
INSERT INTO `attend` VALUES ('3117003109', '徐伟锋', '170813', '产品开发部', 3, 4, 0, 3, '67', NULL);
INSERT INTO `attend` VALUES ('3117003219', '赖成轩', '170813', '产品开发部', 3, 4, 0, 1, '77', NULL);
INSERT INTO `attend` VALUES ('3117003260', '肖俊辉', '170813', '产品开发部', 2, 4, 0, 1, '79', NULL);
INSERT INTO `attend` VALUES ('3117003279', '吴志鹏', '170813', '产品开发部', 2, 4, 0, 1, '79', NULL);
INSERT INTO `attend` VALUES ('3117003314', '黄昊', '170813', '产品开发部', 2, 4, 0, 1, '79', NULL);
INSERT INTO `attend` VALUES ('3117003335', '周嘉英', '170813', '产品开发部', 2, 4, 0, 1, '79', NULL);
INSERT INTO `attend` VALUES ('3117003365', '罗凯元', '170813', '产品开发部', 3, 4, 0, 1, '77', NULL);
INSERT INTO `attend` VALUES ('3117003374', '胡凯', '170813', '产品开发部', 3, 4, 0, 1, '77', NULL);
INSERT INTO `attend` VALUES ('3117003395', '卢健浩', '170813', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3117003400', '张焯斌', '170813', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3117003401', '梁伟鹏', '170813', '产品开发部', 1, 1, 0, 1, '90', NULL);
INSERT INTO `attend` VALUES ('3117003512', '李文杰', '170813', '产品开发部', 1, 1, 0, 1, '90', NULL);
INSERT INTO `attend` VALUES ('3117003537', '刘格溢', '170813', '产品开发部', 1, 1, 0, 1, '90', NULL);
INSERT INTO `attend` VALUES ('3117003552', '李俊浩', '170813', '产品开发部', 1, 1, 0, 1, '90', NULL);
INSERT INTO `attend` VALUES ('3117003591', '李智豪', '170813', '产品开发部', 1, 1, 0, 1, '90', NULL);
INSERT INTO `attend` VALUES ('3117003636', '蔡斌权', '170813', '产品开发部', 1, 1, 0, 1, '90', NULL);
INSERT INTO `attend` VALUES ('3117005132', '姜晓伟', '170813', '产品开发部', 1, 1, 0, 0, '95', NULL);
INSERT INTO `attend` VALUES ('3117005309', '许玉桐', '170813', '产品开发部', 1, 1, 6, 1, '90', NULL);
INSERT INTO `attend` VALUES ('3117005382', '程海文', '170813', '产品开发部', 1, 1, 0, 0, '95', NULL);
INSERT INTO `attend` VALUES ('3117005383', '陈思恺', '170813', '产品开发部', 1, 1, 0, 0, '95', NULL);
INSERT INTO `attend` VALUES ('3216000808', '李晓莹', '170814', '产品开发部', 3, 1, 0, 2, '81', NULL);
INSERT INTO `attend` VALUES ('3216004084', '曾恺欣', '170814', '产品开发部', 3, 1, 0, 1, '86', NULL);
INSERT INTO `attend` VALUES ('3217002742', '林艳芳', '170813', '产品开发部', 1, 1, 0, 0, '95', NULL);
INSERT INTO `attend` VALUES ('3217002829', '马秀婷', '170813', '产品开发部', 1, 1, 0, 0, '95', NULL);
INSERT INTO `attend` VALUES ('3217003122', '陈莹莹', '170813', '产品开发部', 3, 1, 0, 0, '91', NULL);
INSERT INTO `attend` VALUES ('3217003156', '徐宝珠', '170813', '产品开发部', 3, 1, 0, 4, '71', NULL);
INSERT INTO `attend` VALUES ('3217003189', '赵芷君', '170813', '产品开发部', 3, 1, 0, 4, '71', NULL);
INSERT INTO `attend` VALUES ('3217003226', '陈桐心', '170813', '产品开发部', 3, 1, 0, 3, '76', NULL);
INSERT INTO `attend` VALUES ('3217005122', '文李月', '170813', '产品开发部', 3, 1, 0, 4, '71', NULL);
INSERT INTO `attend` VALUES ('3217005315', '唐丽华', '170813', '产品开发部', 3, 1, 0, 1, '86', NULL);

-- ----------------------------
-- Table structure for audit
-- ----------------------------
DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit`  (
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `checkname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`classes`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of audit
-- ----------------------------
INSERT INTO `audit` VALUES ('170813', '胡玲玉');
INSERT INTO `audit` VALUES ('170814', '胡玲玉');

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(20) NOT NULL,
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `choice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of data
-- ----------------------------
INSERT INTO `data` VALUES ('0117005309', '王富民', '男', 42, '', '', '总经理');
INSERT INTO `data` VALUES ('1117005309', '李森', '男', 35, '', '产品开发部', '副经理');
INSERT INTO `data` VALUES ('2117002972', '吴妮蔚', '女', 23, '', '产品开发部', '部门经理');
INSERT INTO `data` VALUES ('2117005309', '胡玲玉', '女', 30, '', '产品开发部', '部门经理');
INSERT INTO `data` VALUES ('2021229091', '石善炜', '男', 23, '170814', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3115001317', '陆世辉', '男', 23, '170814', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3116003004', '全俊龙', '男', 23, '170814', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3116003681', '苏钊全', '男', 23, '170814', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117000780', '蔡治全', '男', 23, '170814', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117000984', '曾海鹏', '男', 23, '170814', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117001069', '李嘉良', '男', 23, '170814', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002325', '蔡文雨', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002327', '邱垂淼', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002332', '吴泳', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002347', '严俊哲', '男', 22, '170601', '财务部', '员工');
INSERT INTO `data` VALUES ('3117002356', '覃伟浩', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002407', '梁庆昭', '男', 23, '170813','产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002443', '陈金伙', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002481', '施浚扬', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002498', '刘东林', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002519', '张永煊', '男', 22, '170601', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002560', '石正生', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002570', '廖成瑾', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002585', '廖俊杰', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002685', '陈瑞禧', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002713', '郭援', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002786', '江志锋', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002972', '陈锦鸿', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117002988', '严树洁', '男', 23, '170813','产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003012', '陈弘毅', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003017', '梁嘉诚', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003026', '林俊安', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003038', '胡正尧', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003103', '梁峰', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003107', '蔡长林', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003109', '徐伟锋', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003219', '赖成轩', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003260', '肖俊辉', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003279', '吴志鹏', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003314', '黄昊', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003335', '周嘉英', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003365', '罗凯元', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003374', '胡凯', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003395', '卢健浩', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003400', '张焯斌', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003401', '梁伟鹏', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003512', '李文杰', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003537', '刘格溢', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003552', '李俊浩', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003591', '李智豪', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117003636', '蔡斌权', '男', 23, '170813','产品开发部', '员工');
INSERT INTO `data` VALUES ('3117005132', '姜晓伟', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117005309', '许玉桐', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117005382', '程海文', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3117005383', '陈思恺', '男', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3216000808', '李晓莹', '女', 23, '170814', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3216004084', '曾恺欣', '女', 18, '170814','产品开发部', '员工');
INSERT INTO `data` VALUES ('3217002742', '林艳芳', '女', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3217002829', '马秀婷', '女', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3217003122', '陈莹莹', '女', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3217003156', '徐宝珠', '女', 23, '170813','产品开发部', '员工');
INSERT INTO `data` VALUES ('3217003189', '赵芷君', '女', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3217003226', '陈桐心', '女', 23, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3217005122', '文李月', '女', 21, '170813', '产品开发部', '员工');
INSERT INTO `data` VALUES ('3217005315', '唐丽华', '女', 23, '170813', '产品开发部', '员工');

-- ----------------------------
-- Table structure for leaveform
-- ----------------------------
DROP TABLE IF EXISTS `leaveform`;
CREATE TABLE `leaveform`  (
  `id` int(255) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duration` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `startTime` date NOT NULL,
  `endTime` date NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `checkname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `frequency` int(255) NOT NULL,
  `auditTeacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditLeader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auditMaster` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of leaveform
-- ----------------------------
INSERT INTO `leaveform` VALUES (000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000020, '3117005309', '许玉桐', '170813', '软件开发部', '一天以上三天以内', '2021-05-15', '2021-05-17', '病假', '胡玲玉', '审核通过', 1, '予以通过', NULL, NULL);
INSERT INTO `leaveform` VALUES (000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000021, '3117005309', '许玉桐', '170813', '软件开发部', '一周以上四周以内', '2021-05-15', '2021-06-08', '出差', '许毅清', '审核通过', 2, '予以通过', NULL, NULL);
INSERT INTO `leaveform` VALUES (000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000022, '3117005309', '许玉桐', '170813', '软件开发部', '三天以上一周以内', '2021-05-15', '2021-05-20', '病假', '黄尚', '审核通过', 3, '予以通过', NULL, NULL);
INSERT INTO `leaveform` VALUES (000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000023, '3117005309', '许玉桐', '170813', '软件开发部', '一周以上四周以内', '2021-05-25', '2021-05-31', '出差', '许毅清', '审核通过', 4, NULL, '予以通过！', NULL);
INSERT INTO `leaveform` VALUES (000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000024, '3117005309', '许玉桐', '170813', '软件开发部', '三天以上一周以内', '2021-05-26', '2021-05-31', '出差', '黄尚', '审核未通过', 5, NULL, NULL, NULL);
INSERT INTO `leaveform` VALUES (000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000025, '3117005309', '许玉桐', '170813', '软件开发部', '一周以上四周以内', '2021-05-27', '2021-06-01', '出差', '胡玲玉', '审核中', 6, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (00000000000000000010, '胡玲玉', '提交任务报告', '提交报告定稿！', '2022-05-24');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `choice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `verifyCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0117005309', '0000000000', '1144391621@qq.com', '总经理', '$2a$10$nTiEqNhmdIXw1es9nCnMOef.kj2QoSnpeCDIWt7i8SA5HhyP37WDe', '王富民');
INSERT INTO `user` VALUES ('1117005309', '1111111111', '1144391621@qq.com', '副经理', '$2a$10$IwBAq.uqCRNA2pHUE4Z7y.wmeTfQqwLVdk8w3ONnRcNE68MFQkp06', '李森');
INSERT INTO `user` VALUES ('2117005309', '2222222222', '1144391621@qq.com', '部门经理', '$2a$10$eHq3iTdWKOP47ugT3aQAv.tETnJperX7CoCrGzF3lMyv5aGTHuYyC', '胡玲玉');
INSERT INTO `user` VALUES ('3117005309', '3333333333', '1144391621@qq.com', '员工', '$2a$10$S6SnpSxwxPqTMT6Pp0Y0EuB5bXaveqVD3nYYYAgEYjnM9qbMJsnq6', '许玉桐');

SET FOREIGN_KEY_CHECKS = 1;
