/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50735 (5.7.35-log)
 Source Host           : localhost:3306
 Source Schema         : employee_system

 Target Server Type    : MySQL
 Target Server Version : 50735 (5.7.35-log)
 File Encoding         : 65001

 Date: 13/09/2024 15:47:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'c3284d0f94606de1fd2af172aba15bf3');
INSERT INTO `admin` VALUES (2, 'root', 'b9be11166d72e9e3ae7fd407165e4bd2');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `did` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `dlocation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门位置',
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '研发部', '北京');
INSERT INTO `department` VALUES (2, '市场部', '北京');
INSERT INTO `department` VALUES (3, '行政部', '北京');
INSERT INTO `department` VALUES (4, 'ddd', 'ddd');
INSERT INTO `department` VALUES (5, 'dasda', 'dsad');
INSERT INTO `department` VALUES (61, 'Mori Ayano', 'k4jfMbR9lM');
INSERT INTO `department` VALUES (62, 'Sano Nanami', 'UmgfJnyBhP');
INSERT INTO `department` VALUES (63, 'Sun Zitao', 'LRKLuHkw77');
INSERT INTO `department` VALUES (64, 'Tang Rui', 'DJ71h4y0uh');
INSERT INTO `department` VALUES (65, 'Fukuda Seiko', 'Fde2O0wdX6');
INSERT INTO `department` VALUES (76, '22212', '222');
INSERT INTO `department` VALUES (78, '321321', '321312');
INSERT INTO `department` VALUES (80, '33221', '3322');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `eid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `eno` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工号',
  `ename` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `eage` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `egender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `ejob` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工种',
  `eentrydate` date NULL DEFAULT NULL COMMENT '入职时间',
  `esalary` decimal(8, 2) NULL DEFAULT NULL COMMENT '基本薪资',
  `estate` tinyint(1) NULL DEFAULT NULL COMMENT '在职状态(1.在职 2.离职)',
  `did` int(11) NULL DEFAULT NULL COMMENT '部门编号',
  `eavatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像的文件名',
  PRIMARY KEY (`eid`) USING BTREE,
  UNIQUE INDEX `empno`(`eno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (5, '10004', 'Tom', 22, '男', '软件开发工程师', '2019-05-09', 42000.00, 1, 1, 'eebb46c61e334e5c9cb06440b83dc929_20220718_62d4af5b8cfd3.jpg');
INSERT INTO `employee` VALUES (6, '10022', '大海', 25, '女', '软件开发工程师', '2018-12-30', 40000.00, 1, 1, '1.jpg');
INSERT INTO `employee` VALUES (7, '10006', 'Smith', 30, '男', '软件开发工程师', '2018-12-30', 50000.00, 1, 1, '1.jpg');
INSERT INTO `employee` VALUES (8, '10007', 'Rose', 31, '女', '软件开发工程师', '2019-06-18', 40000.00, 1, 1, '1.jpg');
INSERT INTO `employee` VALUES (9, '10008', 'Peter', 26, '男', '硬件开发工程师', '2019-06-17', 30000.00, 1, 1, '1.jpg');
INSERT INTO `employee` VALUES (13, '10011', '华强2', 29, '男', '销售', '2019-08-21', 6000.00, 1, 2, '1.jpg');
INSERT INTO `employee` VALUES (14, '10012', '华强3', 28, '男', '销售', '2019-09-22', 6000.00, 1, 2, '1.jpg');
INSERT INTO `employee` VALUES (63, '10086', '10086', 12, '男', '10086', '2024-07-31', 10086.00, 1, 1, '1.jpg');
INSERT INTO `employee` VALUES (65, '10085', '11', 323, '男', '33', '2024-08-03', 33.00, 0, 4, '1.jpg');
INSERT INTO `employee` VALUES (70, '12312', '12312', 12312, '男', '12312', '2024-08-10', 12312.00, 1, 2, NULL);
INSERT INTO `employee` VALUES (71, '3213', '32131', 23, '男', '321', '2024-08-11', 321.00, 1, 1, NULL);
INSERT INTO `employee` VALUES (73, '32113', '32113', 32113, '男', '32113', '2024-08-07', 32113.00, 0, 3, '5b8a5dc5e4c4448f8d582788e442c82a_b_7a5f4ef88f5b142428cab728c8651dbb.jpg');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `pid` bigint(20) NOT NULL,
  `pname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pstart` date NULL DEFAULT NULL,
  `pend` date NULL DEFAULT NULL,
  `pprogress` int(11) NULL DEFAULT NULL,
  `pdescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '项目1', '2023-06-27', '2023-10-07', 6, '11');
INSERT INTO `project` VALUES (2, '项目2', '2024-08-07', '2024-08-31', 10, '12');

-- ----------------------------
-- Table structure for project_employee
-- ----------------------------
DROP TABLE IF EXISTS `project_employee`;
CREATE TABLE `project_employee`  (
  `pid` bigint(20) NULL DEFAULT NULL,
  `eid` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_employee
-- ----------------------------
INSERT INTO `project_employee` VALUES (1, 6);
INSERT INTO `project_employee` VALUES (1, 9);
INSERT INTO `project_employee` VALUES (1, 13);

SET FOREIGN_KEY_CHECKS = 1;
