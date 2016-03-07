/*
Navicat MySQL Data Transfer

Source Server         : jeesite
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : fancy

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-03-07 08:36:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `menupid` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `pageurl` varchar(50) DEFAULT NULL,
  `type` decimal(4,0) DEFAULT NULL,
  `state` decimal(4,0) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sortfiled` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '用户管理', '7', '导航菜单', '/#', '1', '1', '2016-02-02 09:40:33', '0');
INSERT INTO `menu` VALUES ('2', '角色管理', '1', '导航菜单', '/rolesys.html', '1', '1', '2016-02-02 09:40:55', '1');
INSERT INTO `menu` VALUES ('5', '用户新增', '1', '用户新增', '/user/addUser.html', '1', '1', '2016-01-02 13:58:32', '0');
INSERT INTO `menu` VALUES ('7', '系统管理', '0', '系统管理', '/#', '1', '1', '2013-12-12 10:51:54', '0');
INSERT INTO `menu` VALUES ('8', '菜单管理', '7', '菜单管理', '/menus.html', '1', '1', '2016-02-02 09:44:35', '0');
INSERT INTO `menu` VALUES ('9', '用户列表', '10', '用户列表', '/user/list.html', '1', '1', '2016-02-02 09:52:26', '1');
INSERT INTO `menu` VALUES ('10', '测试父级', '0', '测试父级', '/#', '1', '1', '2016-02-02 09:50:41', '0');

-- ----------------------------
-- Table structure for re_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `re_role_menu`;
CREATE TABLE `re_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `menuid` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_role_menu
-- ----------------------------
INSERT INTO `re_role_menu` VALUES ('1', '2', '5', '1');
INSERT INTO `re_role_menu` VALUES ('3', '2', '1', '1');
INSERT INTO `re_role_menu` VALUES ('4', '2', '2', '1');
INSERT INTO `re_role_menu` VALUES ('5', '2', '7', '1');
INSERT INTO `re_role_menu` VALUES ('6', '2', '8', '1');
INSERT INTO `re_role_menu` VALUES ('7', '2', '9', '1');
INSERT INTO `re_role_menu` VALUES ('8', '2', '10', '1');

-- ----------------------------
-- Table structure for re_user_role
-- ----------------------------
DROP TABLE IF EXISTS `re_user_role`;
CREATE TABLE `re_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_user_role
-- ----------------------------
INSERT INTO `re_user_role` VALUES ('1', '7', '2', '1');
INSERT INTO `re_user_role` VALUES ('2', '1', '1', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '开发人员', '', null, null, '系统开发人员', '1', '2015-07-14 17:24:09');
INSERT INTO `role` VALUES ('2', '超级管理员', null, null, null, '超级管理员', '1', '2015-07-14 17:24:16');
INSERT INTO `role` VALUES ('3', '技术部', null, null, null, '技术部', '1', '2015-07-14 17:24:33');

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ipAddress` varchar(50) DEFAULT NULL,
  `loginName` varchar(50) NOT NULL,
  `methodName` varchar(100) DEFAULT NULL,
  `methodRemark` varchar(200) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operatingContent` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=661 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of syslog
-- ----------------------------
INSERT INTO `syslog` VALUES ('1', '192.168.1.18', 'admin', 'com.fancy.action.user.UserAction.userLogin', '用户登录', '2015-07-16 13:55:33', '登录帐号:null');
INSERT INTO `syslog` VALUES ('304', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 10:20:27', '登录帐号:null');
INSERT INTO `syslog` VALUES ('305', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 10:20:44', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('306', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-15 10:20:52', '无');
INSERT INTO `syslog` VALUES ('307', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-15 10:20:52', '无');
INSERT INTO `syslog` VALUES ('308', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 10:21:48', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('309', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-15 10:21:49', '无');
INSERT INTO `syslog` VALUES ('310', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-15 10:21:50', '无');
INSERT INTO `syslog` VALUES ('311', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 10:23:17', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('312', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-15 10:23:19', '无');
INSERT INTO `syslog` VALUES ('313', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-15 10:23:20', '无');
INSERT INTO `syslog` VALUES ('314', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 10:23:47', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('315', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-15 10:23:50', '无');
INSERT INTO `syslog` VALUES ('316', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-15 10:23:50', '无');
INSERT INTO `syslog` VALUES ('317', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 22:03:34', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('318', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 22:24:21', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('319', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 22:27:29', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('320', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 22:27:37', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('321', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 22:35:43', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('322', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 22:35:51', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('323', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 22:41:11', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('324', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 22:42:37', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('325', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 23:04:12', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('326', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-15 23:04:33', '登录帐号:admin');
INSERT INTO `syslog` VALUES ('327', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 10:31:01', '登录帐号:null');
INSERT INTO `syslog` VALUES ('328', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 10:36:23', '登录帐号:null');
INSERT INTO `syslog` VALUES ('329', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 10:36:33', '登录帐号:null');
INSERT INTO `syslog` VALUES ('330', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 10:36:36', '无');
INSERT INTO `syslog` VALUES ('331', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 10:36:37', '无');
INSERT INTO `syslog` VALUES ('332', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 10:51:49', '登录帐号:null');
INSERT INTO `syslog` VALUES ('333', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 10:51:52', '无');
INSERT INTO `syslog` VALUES ('334', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 10:51:52', '无');
INSERT INTO `syslog` VALUES ('335', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 10:56:03', '登录帐号:null');
INSERT INTO `syslog` VALUES ('336', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 10:56:10', '登录帐号:null');
INSERT INTO `syslog` VALUES ('337', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 10:56:14', '无');
INSERT INTO `syslog` VALUES ('338', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 10:56:15', '无');
INSERT INTO `syslog` VALUES ('339', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 10:57:24', '登录帐号:null');
INSERT INTO `syslog` VALUES ('340', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 10:57:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('341', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 10:57:29', '无');
INSERT INTO `syslog` VALUES ('342', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 10:57:29', '无');
INSERT INTO `syslog` VALUES ('343', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userlogout', '用户退出', '2015-12-16 11:13:34', '具体请查看用户登录日志');
INSERT INTO `syslog` VALUES ('344', '10.110.1.96', '匿名用户', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:16:05', '登录帐号:null');
INSERT INTO `syslog` VALUES ('345', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:16:06', '无');
INSERT INTO `syslog` VALUES ('346', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:16:06', '无');
INSERT INTO `syslog` VALUES ('347', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:23:18', '登录帐号:null');
INSERT INTO `syslog` VALUES ('348', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:23:22', '登录帐号:null');
INSERT INTO `syslog` VALUES ('349', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:23:29', '登录帐号:null');
INSERT INTO `syslog` VALUES ('350', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:23:35', '登录帐号:null');
INSERT INTO `syslog` VALUES ('351', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:23:39', '无');
INSERT INTO `syslog` VALUES ('352', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:23:39', '无');
INSERT INTO `syslog` VALUES ('353', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:26:29', '登录帐号:null');
INSERT INTO `syslog` VALUES ('354', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:26:30', '无');
INSERT INTO `syslog` VALUES ('355', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:26:30', '无');
INSERT INTO `syslog` VALUES ('356', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:29:40', '登录帐号:null');
INSERT INTO `syslog` VALUES ('357', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:29:41', '无');
INSERT INTO `syslog` VALUES ('358', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:29:41', '无');
INSERT INTO `syslog` VALUES ('359', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:32:58', '登录帐号:null');
INSERT INTO `syslog` VALUES ('360', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:33:00', '无');
INSERT INTO `syslog` VALUES ('361', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:33:01', '无');
INSERT INTO `syslog` VALUES ('362', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:37:51', '登录帐号:null');
INSERT INTO `syslog` VALUES ('363', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:38:44', '登录帐号:null');
INSERT INTO `syslog` VALUES ('364', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:39:13', '登录帐号:null');
INSERT INTO `syslog` VALUES ('365', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:39:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('366', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:39:40', '登录帐号:null');
INSERT INTO `syslog` VALUES ('367', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:40:19', '登录帐号:null');
INSERT INTO `syslog` VALUES ('368', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:40:23', '无');
INSERT INTO `syslog` VALUES ('369', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:40:23', '无');
INSERT INTO `syslog` VALUES ('370', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:40:25', '登录帐号:null');
INSERT INTO `syslog` VALUES ('371', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:40:27', '无');
INSERT INTO `syslog` VALUES ('372', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:40:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('373', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:40:28', '无');
INSERT INTO `syslog` VALUES ('374', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:40:31', '无');
INSERT INTO `syslog` VALUES ('375', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:40:31', '无');
INSERT INTO `syslog` VALUES ('376', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:40:40', '登录帐号:null');
INSERT INTO `syslog` VALUES ('377', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:40:43', '无');
INSERT INTO `syslog` VALUES ('378', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:40:43', '无');
INSERT INTO `syslog` VALUES ('379', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 11:41:10', '登录帐号:null');
INSERT INTO `syslog` VALUES ('380', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:41:12', '无');
INSERT INTO `syslog` VALUES ('381', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 11:41:13', '无');
INSERT INTO `syslog` VALUES ('382', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 13:10:58', '登录帐号:null');
INSERT INTO `syslog` VALUES ('383', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 13:11:25', '登录帐号:null');
INSERT INTO `syslog` VALUES ('384', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:11:27', '无');
INSERT INTO `syslog` VALUES ('385', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:11:28', '无');
INSERT INTO `syslog` VALUES ('386', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 13:17:10', '登录帐号:null');
INSERT INTO `syslog` VALUES ('387', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:17:11', '无');
INSERT INTO `syslog` VALUES ('388', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:17:12', '无');
INSERT INTO `syslog` VALUES ('389', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 13:35:19', '登录帐号:null');
INSERT INTO `syslog` VALUES ('390', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:35:26', '无');
INSERT INTO `syslog` VALUES ('391', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:35:27', '无');
INSERT INTO `syslog` VALUES ('392', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 13:38:52', '登录帐号:null');
INSERT INTO `syslog` VALUES ('393', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:38:53', '无');
INSERT INTO `syslog` VALUES ('394', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:38:53', '无');
INSERT INTO `syslog` VALUES ('395', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 13:50:46', '登录帐号:null');
INSERT INTO `syslog` VALUES ('396', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 13:50:59', '登录帐号:null');
INSERT INTO `syslog` VALUES ('397', '10.110.1.96', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-16 13:52:41', '登录帐号:null');
INSERT INTO `syslog` VALUES ('398', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:52:43', '无');
INSERT INTO `syslog` VALUES ('399', '10.110.1.96', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-16 13:52:43', '无');
INSERT INTO `syslog` VALUES ('400', '192.168.150.169', '匿名用户', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-18 15:32:41', '登录帐号:null');
INSERT INTO `syslog` VALUES ('401', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 15:32:43', '无');
INSERT INTO `syslog` VALUES ('402', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 15:32:43', '无');
INSERT INTO `syslog` VALUES ('403', '192.168.150.169', 'admin', 'cn.telling.action.user.UserAction.userLogin', '用户登录', '2015-12-18 16:14:58', '登录帐号:null');
INSERT INTO `syslog` VALUES ('404', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:15:00', '无');
INSERT INTO `syslog` VALUES ('405', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:15:01', '无');
INSERT INTO `syslog` VALUES ('406', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:43:13', '登录帐号:null');
INSERT INTO `syslog` VALUES ('407', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:43:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('408', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:43:31', '无');
INSERT INTO `syslog` VALUES ('409', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:43:31', '无');
INSERT INTO `syslog` VALUES ('410', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:44:38', '登录帐号:null');
INSERT INTO `syslog` VALUES ('411', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:44:40', '无');
INSERT INTO `syslog` VALUES ('412', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:44:41', '无');
INSERT INTO `syslog` VALUES ('413', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:45:08', '登录帐号:null');
INSERT INTO `syslog` VALUES ('414', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:45:10', '无');
INSERT INTO `syslog` VALUES ('415', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:45:11', '无');
INSERT INTO `syslog` VALUES ('416', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:47:42', '登录帐号:null');
INSERT INTO `syslog` VALUES ('417', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:47:58', '登录帐号:null');
INSERT INTO `syslog` VALUES ('418', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:48:05', '登录帐号:null');
INSERT INTO `syslog` VALUES ('419', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:48:07', '无');
INSERT INTO `syslog` VALUES ('420', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:48:08', '无');
INSERT INTO `syslog` VALUES ('421', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:55:38', '登录帐号:null');
INSERT INTO `syslog` VALUES ('422', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:55:40', '无');
INSERT INTO `syslog` VALUES ('423', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:55:41', '无');
INSERT INTO `syslog` VALUES ('424', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:56:16', '登录帐号:null');
INSERT INTO `syslog` VALUES ('425', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:56:22', '登录帐号:null');
INSERT INTO `syslog` VALUES ('426', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:56:23', '无');
INSERT INTO `syslog` VALUES ('427', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:56:24', '无');
INSERT INTO `syslog` VALUES ('428', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:58:07', '登录帐号:null');
INSERT INTO `syslog` VALUES ('429', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:58:09', '无');
INSERT INTO `syslog` VALUES ('430', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:58:09', '无');
INSERT INTO `syslog` VALUES ('431', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 16:59:13', '登录帐号:null');
INSERT INTO `syslog` VALUES ('432', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:59:14', '无');
INSERT INTO `syslog` VALUES ('433', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 16:59:15', '无');
INSERT INTO `syslog` VALUES ('434', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 17:00:13', '登录帐号:null');
INSERT INTO `syslog` VALUES ('435', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 17:00:14', '无');
INSERT INTO `syslog` VALUES ('436', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 17:00:14', '无');
INSERT INTO `syslog` VALUES ('437', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-18 17:03:27', '登录帐号:null');
INSERT INTO `syslog` VALUES ('438', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 17:03:29', '无');
INSERT INTO `syslog` VALUES ('439', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getLeftMenuJson', '系统左侧菜单查询', '2015-12-18 17:03:29', '无');
INSERT INTO `syslog` VALUES ('440', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 18:01:23', '登录帐号:null');
INSERT INTO `syslog` VALUES ('441', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 18:09:07', '登录帐号:null');
INSERT INTO `syslog` VALUES ('442', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 18:09:16', '登录帐号:null');
INSERT INTO `syslog` VALUES ('443', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 18:10:07', '登录帐号:null');
INSERT INTO `syslog` VALUES ('444', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 18:10:45', '登录帐号:null');
INSERT INTO `syslog` VALUES ('445', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 18:11:03', '登录帐号:null');
INSERT INTO `syslog` VALUES ('446', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 18:13:47', '登录帐号:null');
INSERT INTO `syslog` VALUES ('447', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 19:17:15', '登录帐号:null');
INSERT INTO `syslog` VALUES ('448', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 19:17:22', '登录帐号:null');
INSERT INTO `syslog` VALUES ('449', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 19:17:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('450', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 19:22:17', '登录帐号:null');
INSERT INTO `syslog` VALUES ('451', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 19:22:30', '登录帐号:null');
INSERT INTO `syslog` VALUES ('452', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 19:36:16', '登录帐号:null');
INSERT INTO `syslog` VALUES ('453', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 19:36:34', '登录帐号:null');
INSERT INTO `syslog` VALUES ('454', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:04:03', '登录帐号:null');
INSERT INTO `syslog` VALUES ('455', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:05:54', '登录帐号:null');
INSERT INTO `syslog` VALUES ('456', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:05:58', '登录帐号:null');
INSERT INTO `syslog` VALUES ('457', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:06:02', '登录帐号:null');
INSERT INTO `syslog` VALUES ('458', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:07:09', '登录帐号:null');
INSERT INTO `syslog` VALUES ('459', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:14:31', '登录帐号:null');
INSERT INTO `syslog` VALUES ('460', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:14:37', '登录帐号:null');
INSERT INTO `syslog` VALUES ('461', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:15:13', '登录帐号:null');
INSERT INTO `syslog` VALUES ('462', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:15:25', '登录帐号:null');
INSERT INTO `syslog` VALUES ('463', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:16:03', '登录帐号:null');
INSERT INTO `syslog` VALUES ('464', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:16:12', '登录帐号:null');
INSERT INTO `syslog` VALUES ('465', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:16:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('466', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:16:41', '登录帐号:null');
INSERT INTO `syslog` VALUES ('467', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:17:22', '登录帐号:null');
INSERT INTO `syslog` VALUES ('468', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:19:00', '登录帐号:null');
INSERT INTO `syslog` VALUES ('469', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-20 22:19:21', '登录帐号:null');
INSERT INTO `syslog` VALUES ('470', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-26 17:46:17', '登录帐号:null');
INSERT INTO `syslog` VALUES ('471', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-26 22:10:30', '登录帐号:null');
INSERT INTO `syslog` VALUES ('472', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-26 22:10:39', '登录帐号:null');
INSERT INTO `syslog` VALUES ('473', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-26 22:35:39', '登录帐号:null');
INSERT INTO `syslog` VALUES ('474', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-26 22:36:50', '登录帐号:null');
INSERT INTO `syslog` VALUES ('475', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-26 22:40:25', '登录帐号:null');
INSERT INTO `syslog` VALUES ('476', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-26 22:41:15', '登录帐号:null');
INSERT INTO `syslog` VALUES ('477', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 09:19:04', '登录帐号:null');
INSERT INTO `syslog` VALUES ('478', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 09:19:11', '登录帐号:null');
INSERT INTO `syslog` VALUES ('479', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 09:45:25', '登录帐号:null');
INSERT INTO `syslog` VALUES ('480', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 10:42:36', '登录帐号:null');
INSERT INTO `syslog` VALUES ('481', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 10:42:47', '登录帐号:null');
INSERT INTO `syslog` VALUES ('482', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 11:02:30', '登录帐号:null');
INSERT INTO `syslog` VALUES ('483', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 11:02:40', '登录帐号:null');
INSERT INTO `syslog` VALUES ('484', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userlogout', '用户退出', '2015-12-27 11:34:23', '具体请查看用户登录日志');
INSERT INTO `syslog` VALUES ('485', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 11:34:31', '登录帐号:null');
INSERT INTO `syslog` VALUES ('486', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userlogout', '用户退出', '2015-12-27 11:48:00', '具体请查看用户登录日志');
INSERT INTO `syslog` VALUES ('487', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 11:48:09', '登录帐号:null');
INSERT INTO `syslog` VALUES ('488', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userlogout', '用户退出', '2015-12-27 11:59:29', '具体请查看用户登录日志');
INSERT INTO `syslog` VALUES ('489', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 11:59:37', '登录帐号:null');
INSERT INTO `syslog` VALUES ('490', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 22:05:19', '登录帐号:null');
INSERT INTO `syslog` VALUES ('491', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 22:05:25', '登录帐号:null');
INSERT INTO `syslog` VALUES ('492', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2015-12-27 22:05:30', '登录帐号:null');
INSERT INTO `syslog` VALUES ('493', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 17:11:10', '登录帐号:null');
INSERT INTO `syslog` VALUES ('494', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 17:11:18', '登录帐号:null');
INSERT INTO `syslog` VALUES ('495', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 18:04:11', '登录帐号:null');
INSERT INTO `syslog` VALUES ('496', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 18:06:46', '登录帐号:null');
INSERT INTO `syslog` VALUES ('497', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 18:59:15', '登录帐号:null');
INSERT INTO `syslog` VALUES ('498', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 18:59:24', '登录帐号:null');
INSERT INTO `syslog` VALUES ('499', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 19:37:25', '登录帐号:null');
INSERT INTO `syslog` VALUES ('500', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 20:00:34', '登录帐号:null');
INSERT INTO `syslog` VALUES ('501', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-01 20:00:39', '登录帐号:null');
INSERT INTO `syslog` VALUES ('502', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 11:57:18', '登录帐号:null');
INSERT INTO `syslog` VALUES ('503', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 11:57:24', '登录帐号:null');
INSERT INTO `syslog` VALUES ('504', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 11:59:17', '登录帐号:null');
INSERT INTO `syslog` VALUES ('505', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 11:59:37', '登录帐号:null');
INSERT INTO `syslog` VALUES ('506', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 12:00:21', '登录帐号:null');
INSERT INTO `syslog` VALUES ('507', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 12:00:29', '登录帐号:null');
INSERT INTO `syslog` VALUES ('508', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 12:01:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('509', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 13:00:35', '登录帐号:null');
INSERT INTO `syslog` VALUES ('510', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 13:00:50', '登录帐号:null');
INSERT INTO `syslog` VALUES ('511', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 13:05:10', '登录帐号:null');
INSERT INTO `syslog` VALUES ('512', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 13:10:05', '登录帐号:null');
INSERT INTO `syslog` VALUES ('513', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 13:10:10', '登录帐号:null');
INSERT INTO `syslog` VALUES ('514', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 13:58:44', '登录帐号:null');
INSERT INTO `syslog` VALUES ('515', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 13:58:50', '登录帐号:null');
INSERT INTO `syslog` VALUES ('516', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 13:59:36', '登录帐号:null');
INSERT INTO `syslog` VALUES ('517', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 14:00:05', '登录帐号:null');
INSERT INTO `syslog` VALUES ('518', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 14:03:17', '登录帐号:null');
INSERT INTO `syslog` VALUES ('519', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 14:04:32', '登录帐号:null');
INSERT INTO `syslog` VALUES ('520', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 16:08:33', '登录帐号:null');
INSERT INTO `syslog` VALUES ('521', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 16:08:41', '登录帐号:null');
INSERT INTO `syslog` VALUES ('522', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 22:12:45', '登录帐号:null');
INSERT INTO `syslog` VALUES ('523', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 22:13:00', '登录帐号:null');
INSERT INTO `syslog` VALUES ('524', '127.0.0.1', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-02 22:14:21', '登录帐号:null');
INSERT INTO `syslog` VALUES ('525', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-06 21:46:04', '登录帐号:null');
INSERT INTO `syslog` VALUES ('526', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userlogout', '用户退出', '2016-01-06 21:46:11', '具体请查看用户登录日志');
INSERT INTO `syslog` VALUES ('527', '192.168.1.102', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-06 21:58:33', '登录帐号:null');
INSERT INTO `syslog` VALUES ('528', '192.168.1.102', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-06 21:59:40', '登录帐号:null');
INSERT INTO `syslog` VALUES ('529', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 11:56:45', '登录帐号:null');
INSERT INTO `syslog` VALUES ('530', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 14:12:30', '登录帐号:null');
INSERT INTO `syslog` VALUES ('531', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 14:14:29', '登录帐号:null');
INSERT INTO `syslog` VALUES ('532', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 14:54:35', '登录帐号:null');
INSERT INTO `syslog` VALUES ('533', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 15:05:32', '登录帐号:null');
INSERT INTO `syslog` VALUES ('534', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 15:06:00', '登录帐号:null');
INSERT INTO `syslog` VALUES ('535', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 15:06:08', '登录帐号:null');
INSERT INTO `syslog` VALUES ('536', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 15:06:13', '登录帐号:null');
INSERT INTO `syslog` VALUES ('537', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 15:18:08', '登录帐号:null');
INSERT INTO `syslog` VALUES ('538', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 16:41:35', '登录帐号:null');
INSERT INTO `syslog` VALUES ('539', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 16:41:44', '登录帐号:null');
INSERT INTO `syslog` VALUES ('540', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 16:41:49', '登录帐号:null');
INSERT INTO `syslog` VALUES ('541', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userlogout', '用户退出', '2016-01-27 16:49:15', '具体请查看用户登录日志');
INSERT INTO `syslog` VALUES ('542', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 16:56:13', '登录帐号:null');
INSERT INTO `syslog` VALUES ('543', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 16:56:19', '登录帐号:null');
INSERT INTO `syslog` VALUES ('544', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-27 16:56:25', '登录帐号:null');
INSERT INTO `syslog` VALUES ('545', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 14:07:38', '操作参数:');
INSERT INTO `syslog` VALUES ('546', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 14:07:50', '操作参数:');
INSERT INTO `syslog` VALUES ('547', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 14:07:58', '操作参数:');
INSERT INTO `syslog` VALUES ('548', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 14:08:44', '操作参数:');
INSERT INTO `syslog` VALUES ('549', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 14:09:11', '操作参数:');
INSERT INTO `syslog` VALUES ('550', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 15:24:51', '操作参数:');
INSERT INTO `syslog` VALUES ('551', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 15:57:41', '操作参数:');
INSERT INTO `syslog` VALUES ('552', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:02:28', '操作参数:');
INSERT INTO `syslog` VALUES ('553', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:10:34', '操作参数:');
INSERT INTO `syslog` VALUES ('554', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:18:25', '操作参数:');
INSERT INTO `syslog` VALUES ('555', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:22:07', '操作参数:');
INSERT INTO `syslog` VALUES ('556', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:22:13', '操作参数:');
INSERT INTO `syslog` VALUES ('557', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:22:19', '操作参数:');
INSERT INTO `syslog` VALUES ('558', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:24:00', '操作参数:');
INSERT INTO `syslog` VALUES ('559', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:25:58', '操作参数:');
INSERT INTO `syslog` VALUES ('560', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:33:15', '操作参数:');
INSERT INTO `syslog` VALUES ('561', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:35:12', '操作参数:');
INSERT INTO `syslog` VALUES ('562', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:42:25', '操作参数:');
INSERT INTO `syslog` VALUES ('563', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:42:54', '操作参数:');
INSERT INTO `syslog` VALUES ('564', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 16:43:11', '操作参数:');
INSERT INTO `syslog` VALUES ('565', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-01-29 17:27:51', '登录帐号:null');
INSERT INTO `syslog` VALUES ('566', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 17:27:58', '操作参数:');
INSERT INTO `syslog` VALUES ('567', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 17:32:10', '操作参数:');
INSERT INTO `syslog` VALUES ('568', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 17:35:50', '操作参数:');
INSERT INTO `syslog` VALUES ('569', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-01-29 17:36:47', '操作参数:');
INSERT INTO `syslog` VALUES ('570', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-01 11:57:39', '登录帐号:null');
INSERT INTO `syslog` VALUES ('571', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-01 11:57:50', '登录帐号:null');
INSERT INTO `syslog` VALUES ('572', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-01 13:42:02', '登录帐号:null');
INSERT INTO `syslog` VALUES ('573', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-01 13:42:11', '登录帐号:null');
INSERT INTO `syslog` VALUES ('574', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-01 13:42:26', '登录帐号:null');
INSERT INTO `syslog` VALUES ('575', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-01 13:42:31', '操作参数:');
INSERT INTO `syslog` VALUES ('576', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-01 13:47:42', '操作参数:');
INSERT INTO `syslog` VALUES ('577', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-01 13:52:22', '操作参数:');
INSERT INTO `syslog` VALUES ('578', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-01 13:53:40', '操作参数:');
INSERT INTO `syslog` VALUES ('579', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-01 14:00:26', '操作参数:');
INSERT INTO `syslog` VALUES ('580', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-01 14:02:44', '操作参数:');
INSERT INTO `syslog` VALUES ('581', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-01 14:03:12', '操作参数:');
INSERT INTO `syslog` VALUES ('582', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-01 14:23:56', '操作参数:');
INSERT INTO `syslog` VALUES ('583', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:16:45', '登录帐号:null');
INSERT INTO `syslog` VALUES ('584', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:16:55', '登录帐号:null');
INSERT INTO `syslog` VALUES ('585', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:17:26', '登录帐号:null');
INSERT INTO `syslog` VALUES ('586', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:18:12', '登录帐号:null');
INSERT INTO `syslog` VALUES ('587', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:27:21', '登录帐号:null');
INSERT INTO `syslog` VALUES ('588', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:27:27', '登录帐号:null');
INSERT INTO `syslog` VALUES ('589', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:27:34', '登录帐号:null');
INSERT INTO `syslog` VALUES ('590', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:28:15', '操作参数:');
INSERT INTO `syslog` VALUES ('591', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:36:19', '登录帐号:null');
INSERT INTO `syslog` VALUES ('592', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:36:38', '登录帐号:null');
INSERT INTO `syslog` VALUES ('593', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:37:06', '操作参数:');
INSERT INTO `syslog` VALUES ('594', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:37:15', '操作参数:');
INSERT INTO `syslog` VALUES ('595', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:41:01', '操作参数:');
INSERT INTO `syslog` VALUES ('596', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:44:10', '操作参数:');
INSERT INTO `syslog` VALUES ('597', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:44:48', '登录帐号:null');
INSERT INTO `syslog` VALUES ('598', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:44:56', '登录帐号:null');
INSERT INTO `syslog` VALUES ('599', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:45:01', '操作参数:');
INSERT INTO `syslog` VALUES ('600', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:45:39', '操作参数:');
INSERT INTO `syslog` VALUES ('601', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:51:58', '登录帐号:null');
INSERT INTO `syslog` VALUES ('602', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-02 09:52:29', '登录帐号:null');
INSERT INTO `syslog` VALUES ('603', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:52:36', '操作参数:');
INSERT INTO `syslog` VALUES ('604', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-02 09:52:41', '操作参数:');
INSERT INTO `syslog` VALUES ('605', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 13:59:10', '登录帐号:null');
INSERT INTO `syslog` VALUES ('606', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:04:37', '登录帐号:null');
INSERT INTO `syslog` VALUES ('607', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:04:47', '登录帐号:null');
INSERT INTO `syslog` VALUES ('608', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:04:54', '登录帐号:null');
INSERT INTO `syslog` VALUES ('609', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:05:13', '登录帐号:null');
INSERT INTO `syslog` VALUES ('610', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:05:43', '登录帐号:null');
INSERT INTO `syslog` VALUES ('611', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:11:18', '登录帐号:null');
INSERT INTO `syslog` VALUES ('612', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:13:12', '登录帐号:null');
INSERT INTO `syslog` VALUES ('613', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:17:48', '登录帐号:null');
INSERT INTO `syslog` VALUES ('614', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:20:41', '登录帐号:null');
INSERT INTO `syslog` VALUES ('615', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:24:21', '登录帐号:null');
INSERT INTO `syslog` VALUES ('616', '10.110.1.39', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:24:37', '登录帐号:null');
INSERT INTO `syslog` VALUES ('617', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-03 14:26:07', '操作参数:');
INSERT INTO `syslog` VALUES ('618', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 14:30:42', '登录帐号:null');
INSERT INTO `syslog` VALUES ('619', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-03 16:32:51', '登录帐号:null');
INSERT INTO `syslog` VALUES ('620', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 13:43:54', '登录帐号:null');
INSERT INTO `syslog` VALUES ('621', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 13:46:35', '登录帐号:null');
INSERT INTO `syslog` VALUES ('622', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 13:47:01', '登录帐号:null');
INSERT INTO `syslog` VALUES ('623', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 13:48:22', '登录帐号:null');
INSERT INTO `syslog` VALUES ('624', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 13:50:03', '登录帐号:null');
INSERT INTO `syslog` VALUES ('625', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 13:51:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('626', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 13:53:45', '登录帐号:null');
INSERT INTO `syslog` VALUES ('627', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 13:59:50', '登录帐号:null');
INSERT INTO `syslog` VALUES ('628', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 14:05:03', '登录帐号:null');
INSERT INTO `syslog` VALUES ('629', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 14:08:15', '登录帐号:null');
INSERT INTO `syslog` VALUES ('630', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 14:11:10', '登录帐号:null');
INSERT INTO `syslog` VALUES ('631', '10.110.1.39', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 14:54:38', '登录帐号:null');
INSERT INTO `syslog` VALUES ('632', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 15:28:29', '登录帐号:null');
INSERT INTO `syslog` VALUES ('633', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 15:32:03', '登录帐号:null');
INSERT INTO `syslog` VALUES ('634', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 15:38:45', '登录帐号:null');
INSERT INTO `syslog` VALUES ('635', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 15:39:18', '登录帐号:null');
INSERT INTO `syslog` VALUES ('636', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 15:39:43', '登录帐号:null');
INSERT INTO `syslog` VALUES ('637', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.getViewAllMenuJson', '菜单列表数据查看', '2016-02-25 15:42:45', '操作参数:');
INSERT INTO `syslog` VALUES ('638', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:09:27', '登录帐号:null');
INSERT INTO `syslog` VALUES ('639', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:35:38', '登录帐号:null');
INSERT INTO `syslog` VALUES ('640', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:37:05', '登录帐号:null');
INSERT INTO `syslog` VALUES ('641', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:37:09', '登录帐号:null');
INSERT INTO `syslog` VALUES ('642', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:38:23', '登录帐号:null');
INSERT INTO `syslog` VALUES ('643', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:50:04', '登录帐号:null');
INSERT INTO `syslog` VALUES ('644', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:50:35', '登录帐号:null');
INSERT INTO `syslog` VALUES ('645', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:50:42', '登录帐号:null');
INSERT INTO `syslog` VALUES ('646', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:53:09', '登录帐号:null');
INSERT INTO `syslog` VALUES ('647', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 17:55:16', '登录帐号:null');
INSERT INTO `syslog` VALUES ('648', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 18:01:31', '登录帐号:null');
INSERT INTO `syslog` VALUES ('649', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 18:01:39', '登录帐号:null');
INSERT INTO `syslog` VALUES ('650', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 18:41:07', '登录帐号:null');
INSERT INTO `syslog` VALUES ('651', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 18:41:31', '登录帐号:null');
INSERT INTO `syslog` VALUES ('652', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 18:50:28', '登录帐号:null');
INSERT INTO `syslog` VALUES ('653', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 18:50:48', '登录帐号:null');
INSERT INTO `syslog` VALUES ('654', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 19:02:52', '登录帐号:null');
INSERT INTO `syslog` VALUES ('655', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 19:03:01', '登录帐号:null');
INSERT INTO `syslog` VALUES ('656', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 19:04:56', '登录帐号:null');
INSERT INTO `syslog` VALUES ('657', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 19:05:04', '登录帐号:null');
INSERT INTO `syslog` VALUES ('658', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 19:07:39', '登录帐号:null');
INSERT INTO `syslog` VALUES ('659', '192.168.150.169', 'admin', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 19:07:47', '登录帐号:null');
INSERT INTO `syslog` VALUES ('660', '192.168.150.169', '匿名用户', 'cn.telling.action.main.SysAction.userLogin', '用户登录', '2016-02-25 20:06:14', '登录帐号:null');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(50) DEFAULT NULL,
  `account` varchar(200) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `lasttime` date DEFAULT NULL,
  `sex` decimal(11,0) DEFAULT NULL,
  `phone` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `state` decimal(11,0) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `age` decimal(5,0) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'java', null, '5Y31PtO8cF1bkhuiemEBuIOA8Pq6xxod+Eg0JU4fGzHXp3AMQ4jjbw92uRSNkJhb', null, null, null, null, null, null, '1', null, null, '2016-01-01 19:33:23');
INSERT INTO `users` VALUES ('2', 'net', null, '333', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:49');
INSERT INTO `users` VALUES ('3', 'c#', null, '2222', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:50');
INSERT INTO `users` VALUES ('4', 'php', null, '223', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:51');
INSERT INTO `users` VALUES ('5', 'e', null, '333', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:51');
INSERT INTO `users` VALUES ('6', 'you', null, '22', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:08');
INSERT INTO `users` VALUES ('7', 'admin', 'admin', '5Y31PtO8cF1bkhuiemEBuIOA8Pq6xxod+Eg0JU4fGzHXp3AMQ4jjbw92uRSNkJhb', '操圣', 'zhgo116@qq.com', '2015-06-19', '1', null, null, '1', null, null, '2015-12-20 09:42:52');
INSERT INTO `users` VALUES ('8', 'zhangfei', null, '344dd', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:53');
INSERT INTO `users` VALUES ('9', 'guanyu ', null, '32333', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:55');
INSERT INTO `users` VALUES ('10', 'sdfa', null, 'ewrf', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:56');
INSERT INTO `users` VALUES ('11', 'dsf', null, 'dsf', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:58');
INSERT INTO `users` VALUES ('12', 'dfa', null, 'dfad', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:57');
INSERT INTO `users` VALUES ('13', 'dafas', null, 'df', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:42:59');
INSERT INTO `users` VALUES ('14', 'DAF', null, 'D', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:01');
INSERT INTO `users` VALUES ('15', '34', null, '333', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:00');
INSERT INTO `users` VALUES ('16', '1', null, '33', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:02');
INSERT INTO `users` VALUES ('17', '33', null, '33', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:02');
INSERT INTO `users` VALUES ('18', '4', null, '22', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:03');
INSERT INTO `users` VALUES ('19', '32', null, '341', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:03');
INSERT INTO `users` VALUES ('20', '32412423', null, '4234234', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:07');
INSERT INTO `users` VALUES ('21', '3Q4', null, 'ERWE', null, null, null, null, null, null, '1', null, null, '2015-12-20 09:43:05');

-- ----------------------------
-- Table structure for user_loginlog
-- ----------------------------
DROP TABLE IF EXISTS `user_loginlog`;
CREATE TABLE `user_loginlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `useraccount` varchar(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '帐号',
  `type` int(11) NOT NULL COMMENT '操作类型:0登录，1退出',
  `ip` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作ip',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=893 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_loginlog
-- ----------------------------
INSERT INTO `user_loginlog` VALUES ('749', 'admin', '0', '10.110.1.96', '2015-12-16 10:36:33');
INSERT INTO `user_loginlog` VALUES ('750', 'admin', '0', '10.110.1.96', '2015-12-16 10:51:49');
INSERT INTO `user_loginlog` VALUES ('751', 'admin', '0', '10.110.1.96', '2015-12-16 10:56:10');
INSERT INTO `user_loginlog` VALUES ('752', 'admin', '0', '10.110.1.96', '2015-12-16 10:57:24');
INSERT INTO `user_loginlog` VALUES ('753', 'admin', '0', '10.110.1.96', '2015-12-16 10:57:27');
INSERT INTO `user_loginlog` VALUES ('754', 'admin', '1', '10.110.1.96', '2015-12-16 11:13:33');
INSERT INTO `user_loginlog` VALUES ('755', 'admin', '0', '10.110.1.96', '2015-12-16 11:16:05');
INSERT INTO `user_loginlog` VALUES ('756', 'admin', '0', '10.110.1.96', '2015-12-16 11:23:34');
INSERT INTO `user_loginlog` VALUES ('757', 'admin', '0', '10.110.1.96', '2015-12-16 11:26:29');
INSERT INTO `user_loginlog` VALUES ('758', 'admin', '0', '10.110.1.96', '2015-12-16 11:29:40');
INSERT INTO `user_loginlog` VALUES ('759', 'admin', '0', '10.110.1.96', '2015-12-16 11:32:58');
INSERT INTO `user_loginlog` VALUES ('760', 'admin', '0', '10.110.1.96', '2015-12-16 11:37:51');
INSERT INTO `user_loginlog` VALUES ('761', 'admin', '0', '10.110.1.96', '2015-12-16 11:38:44');
INSERT INTO `user_loginlog` VALUES ('762', 'admin', '0', '10.110.1.96', '2015-12-16 11:39:13');
INSERT INTO `user_loginlog` VALUES ('763', 'admin', '0', '10.110.1.96', '2015-12-16 11:39:28');
INSERT INTO `user_loginlog` VALUES ('764', 'admin', '0', '10.110.1.96', '2015-12-16 11:39:40');
INSERT INTO `user_loginlog` VALUES ('765', 'admin', '0', '10.110.1.96', '2015-12-16 11:40:19');
INSERT INTO `user_loginlog` VALUES ('766', 'admin', '0', '10.110.1.96', '2015-12-16 11:40:25');
INSERT INTO `user_loginlog` VALUES ('767', 'admin', '0', '10.110.1.96', '2015-12-16 11:40:27');
INSERT INTO `user_loginlog` VALUES ('768', 'admin', '0', '10.110.1.96', '2015-12-16 11:40:40');
INSERT INTO `user_loginlog` VALUES ('769', 'admin', '0', '10.110.1.96', '2015-12-16 11:41:10');
INSERT INTO `user_loginlog` VALUES ('770', 'admin', '0', '10.110.1.96', '2015-12-16 13:11:11');
INSERT INTO `user_loginlog` VALUES ('771', 'admin', '0', '10.110.1.96', '2015-12-16 13:14:31');
INSERT INTO `user_loginlog` VALUES ('772', 'admin', '0', '10.110.1.96', '2015-12-16 13:21:15');
INSERT INTO `user_loginlog` VALUES ('773', 'admin', '0', '10.110.1.96', '2015-12-16 13:35:26');
INSERT INTO `user_loginlog` VALUES ('774', 'admin', '0', '10.110.1.96', '2015-12-16 13:51:08');
INSERT INTO `user_loginlog` VALUES ('775', 'admin', '0', '192.168.150.169', '2015-12-18 15:32:41');
INSERT INTO `user_loginlog` VALUES ('776', 'admin', '0', '192.168.150.169', '2015-12-18 16:14:58');
INSERT INTO `user_loginlog` VALUES ('777', 'admin', '0', '192.168.150.169', '2015-12-18 16:43:28');
INSERT INTO `user_loginlog` VALUES ('778', 'admin', '0', '192.168.150.169', '2015-12-18 16:44:38');
INSERT INTO `user_loginlog` VALUES ('779', 'admin', '0', '192.168.150.169', '2015-12-18 16:45:08');
INSERT INTO `user_loginlog` VALUES ('780', 'admin', '0', '192.168.150.169', '2015-12-18 16:48:05');
INSERT INTO `user_loginlog` VALUES ('781', 'admin', '0', '192.168.150.169', '2015-12-18 16:55:38');
INSERT INTO `user_loginlog` VALUES ('782', 'admin', '0', '192.168.150.169', '2015-12-18 16:56:22');
INSERT INTO `user_loginlog` VALUES ('783', 'admin', '0', '192.168.150.169', '2015-12-18 16:58:07');
INSERT INTO `user_loginlog` VALUES ('784', 'admin', '0', '192.168.150.169', '2015-12-18 16:59:13');
INSERT INTO `user_loginlog` VALUES ('785', 'admin', '0', '192.168.150.169', '2015-12-18 17:00:13');
INSERT INTO `user_loginlog` VALUES ('786', 'admin', '0', '192.168.150.169', '2015-12-18 17:03:27');
INSERT INTO `user_loginlog` VALUES ('787', 'admin', '0', '192.168.1.102', '2015-12-20 18:01:22');
INSERT INTO `user_loginlog` VALUES ('788', 'admin', '0', '127.0.0.1', '2015-12-20 18:09:16');
INSERT INTO `user_loginlog` VALUES ('789', 'admin', '0', '127.0.0.1', '2015-12-20 18:10:07');
INSERT INTO `user_loginlog` VALUES ('790', 'admin', '0', '127.0.0.1', '2015-12-20 18:10:45');
INSERT INTO `user_loginlog` VALUES ('791', 'admin', '0', '127.0.0.1', '2015-12-20 18:11:03');
INSERT INTO `user_loginlog` VALUES ('792', 'admin', '0', '127.0.0.1', '2015-12-20 18:13:47');
INSERT INTO `user_loginlog` VALUES ('793', 'admin', '0', '127.0.0.1', '2015-12-20 19:17:28');
INSERT INTO `user_loginlog` VALUES ('794', 'admin', '0', '192.168.1.102', '2015-12-20 19:22:30');
INSERT INTO `user_loginlog` VALUES ('795', 'admin', '0', '192.168.1.102', '2015-12-20 19:36:34');
INSERT INTO `user_loginlog` VALUES ('796', 'admin', '0', '192.168.1.102', '2015-12-20 22:06:02');
INSERT INTO `user_loginlog` VALUES ('797', 'admin', '0', '192.168.1.102', '2015-12-20 22:07:09');
INSERT INTO `user_loginlog` VALUES ('798', 'admin', '0', '192.168.1.102', '2015-12-20 22:14:36');
INSERT INTO `user_loginlog` VALUES ('799', 'admin', '0', '192.168.1.102', '2015-12-20 22:15:13');
INSERT INTO `user_loginlog` VALUES ('800', 'admin', '0', '192.168.1.102', '2015-12-20 22:15:25');
INSERT INTO `user_loginlog` VALUES ('801', 'admin', '0', '192.168.1.102', '2015-12-20 22:16:03');
INSERT INTO `user_loginlog` VALUES ('802', 'admin', '0', '192.168.1.102', '2015-12-20 22:16:12');
INSERT INTO `user_loginlog` VALUES ('803', 'admin', '0', '192.168.1.102', '2015-12-20 22:16:28');
INSERT INTO `user_loginlog` VALUES ('804', 'admin', '0', '192.168.1.102', '2015-12-20 22:16:40');
INSERT INTO `user_loginlog` VALUES ('805', 'admin', '0', '192.168.1.102', '2015-12-20 22:17:22');
INSERT INTO `user_loginlog` VALUES ('806', 'admin', '0', '192.168.1.102', '2015-12-20 22:19:00');
INSERT INTO `user_loginlog` VALUES ('807', 'admin', '0', '192.168.1.102', '2015-12-20 22:19:21');
INSERT INTO `user_loginlog` VALUES ('808', 'admin', '0', '192.168.1.102', '2015-12-26 22:10:39');
INSERT INTO `user_loginlog` VALUES ('809', 'admin', '0', '192.168.1.102', '2015-12-26 22:35:39');
INSERT INTO `user_loginlog` VALUES ('810', 'admin', '0', '192.168.1.102', '2015-12-26 22:36:50');
INSERT INTO `user_loginlog` VALUES ('811', 'admin', '0', '192.168.1.102', '2015-12-26 22:40:25');
INSERT INTO `user_loginlog` VALUES ('812', 'admin', '0', '192.168.1.102', '2015-12-26 22:41:15');
INSERT INTO `user_loginlog` VALUES ('813', 'admin', '0', '192.168.1.102', '2015-12-27 09:19:10');
INSERT INTO `user_loginlog` VALUES ('814', 'admin', '0', '192.168.1.102', '2015-12-27 09:45:25');
INSERT INTO `user_loginlog` VALUES ('815', 'admin', '0', '192.168.1.102', '2015-12-27 10:42:47');
INSERT INTO `user_loginlog` VALUES ('816', 'admin', '0', '192.168.1.102', '2015-12-27 11:02:40');
INSERT INTO `user_loginlog` VALUES ('817', 'admin', '1', '192.168.1.102', '2015-12-27 11:34:23');
INSERT INTO `user_loginlog` VALUES ('818', 'admin', '0', '192.168.1.102', '2015-12-27 11:34:31');
INSERT INTO `user_loginlog` VALUES ('819', 'admin', '1', '192.168.1.102', '2015-12-27 11:48:00');
INSERT INTO `user_loginlog` VALUES ('820', 'admin', '0', '192.168.1.102', '2015-12-27 11:48:09');
INSERT INTO `user_loginlog` VALUES ('821', 'admin', '1', '192.168.1.102', '2015-12-27 11:59:29');
INSERT INTO `user_loginlog` VALUES ('822', 'admin', '0', '192.168.1.102', '2015-12-27 11:59:37');
INSERT INTO `user_loginlog` VALUES ('823', 'admin', '0', '192.168.1.102', '2015-12-27 22:05:30');
INSERT INTO `user_loginlog` VALUES ('824', 'admin', '0', '127.0.0.1', '2016-01-01 17:11:18');
INSERT INTO `user_loginlog` VALUES ('825', 'admin', '0', '127.0.0.1', '2016-01-01 18:06:46');
INSERT INTO `user_loginlog` VALUES ('826', 'admin', '0', '127.0.0.1', '2016-01-01 18:59:24');
INSERT INTO `user_loginlog` VALUES ('827', 'admin', '0', '127.0.0.1', '2016-01-01 20:00:39');
INSERT INTO `user_loginlog` VALUES ('828', 'admin', '0', '127.0.0.1', '2016-01-02 11:57:24');
INSERT INTO `user_loginlog` VALUES ('829', 'admin', '0', '127.0.0.1', '2016-01-02 11:59:17');
INSERT INTO `user_loginlog` VALUES ('830', 'admin', '0', '127.0.0.1', '2016-01-02 11:59:37');
INSERT INTO `user_loginlog` VALUES ('831', 'admin', '0', '127.0.0.1', '2016-01-02 12:00:29');
INSERT INTO `user_loginlog` VALUES ('832', 'admin', '0', '127.0.0.1', '2016-01-02 13:00:35');
INSERT INTO `user_loginlog` VALUES ('833', 'admin', '0', '127.0.0.1', '2016-01-02 13:00:50');
INSERT INTO `user_loginlog` VALUES ('834', 'admin', '0', '127.0.0.1', '2016-01-02 13:10:10');
INSERT INTO `user_loginlog` VALUES ('835', 'admin', '0', '127.0.0.1', '2016-01-02 13:58:50');
INSERT INTO `user_loginlog` VALUES ('836', 'admin', '0', '127.0.0.1', '2016-01-02 13:59:36');
INSERT INTO `user_loginlog` VALUES ('837', 'admin', '0', '127.0.0.1', '2016-01-02 14:00:05');
INSERT INTO `user_loginlog` VALUES ('838', 'admin', '0', '127.0.0.1', '2016-01-02 14:03:17');
INSERT INTO `user_loginlog` VALUES ('839', 'admin', '0', '127.0.0.1', '2016-01-02 14:04:31');
INSERT INTO `user_loginlog` VALUES ('840', 'admin', '0', '127.0.0.1', '2016-01-02 16:08:40');
INSERT INTO `user_loginlog` VALUES ('841', 'admin', '0', '127.0.0.1', '2016-01-02 22:13:00');
INSERT INTO `user_loginlog` VALUES ('842', 'admin', '0', '127.0.0.1', '2016-01-02 22:14:21');
INSERT INTO `user_loginlog` VALUES ('843', 'admin', '0', '192.168.1.102', '2016-01-06 21:46:04');
INSERT INTO `user_loginlog` VALUES ('844', 'admin', '1', '192.168.1.102', '2016-01-06 21:46:11');
INSERT INTO `user_loginlog` VALUES ('845', 'admin', '0', '192.168.1.102', '2016-01-06 21:58:33');
INSERT INTO `user_loginlog` VALUES ('846', 'admin', '0', '192.168.1.102', '2016-01-06 21:59:40');
INSERT INTO `user_loginlog` VALUES ('847', 'admin', '0', '10.110.1.39', '2016-01-27 11:56:45');
INSERT INTO `user_loginlog` VALUES ('848', 'admin', '0', '10.110.1.39', '2016-01-27 14:14:29');
INSERT INTO `user_loginlog` VALUES ('849', 'admin', '0', '10.110.1.39', '2016-01-27 14:54:34');
INSERT INTO `user_loginlog` VALUES ('850', 'admin', '0', '10.110.1.39', '2016-01-27 15:06:13');
INSERT INTO `user_loginlog` VALUES ('851', 'admin', '0', '10.110.1.39', '2016-01-27 15:18:08');
INSERT INTO `user_loginlog` VALUES ('852', 'admin', '0', '10.110.1.39', '2016-01-27 16:41:49');
INSERT INTO `user_loginlog` VALUES ('853', 'admin', '1', '10.110.1.39', '2016-01-27 16:49:15');
INSERT INTO `user_loginlog` VALUES ('854', 'admin', '0', '10.110.1.39', '2016-01-27 16:56:25');
INSERT INTO `user_loginlog` VALUES ('855', 'admin', '0', '10.110.1.39', '2016-01-29 17:27:50');
INSERT INTO `user_loginlog` VALUES ('856', 'admin', '0', '10.110.1.39', '2016-02-01 13:42:11');
INSERT INTO `user_loginlog` VALUES ('857', 'admin', '0', '10.110.1.39', '2016-02-02 09:16:55');
INSERT INTO `user_loginlog` VALUES ('858', 'admin', '0', '10.110.1.39', '2016-02-02 09:17:26');
INSERT INTO `user_loginlog` VALUES ('859', 'admin', '0', '10.110.1.39', '2016-02-02 09:18:12');
INSERT INTO `user_loginlog` VALUES ('860', 'admin', '0', '10.110.1.39', '2016-02-02 09:27:33');
INSERT INTO `user_loginlog` VALUES ('861', 'admin', '0', '10.110.1.39', '2016-02-02 09:36:38');
INSERT INTO `user_loginlog` VALUES ('862', 'admin', '0', '10.110.1.39', '2016-02-02 09:44:56');
INSERT INTO `user_loginlog` VALUES ('863', 'admin', '0', '10.110.1.39', '2016-02-02 09:51:58');
INSERT INTO `user_loginlog` VALUES ('864', 'admin', '0', '10.110.1.39', '2016-02-02 09:52:29');
INSERT INTO `user_loginlog` VALUES ('865', 'admin', '0', '10.110.1.39', '2016-02-03 14:24:37');
INSERT INTO `user_loginlog` VALUES ('866', 'admin', '0', '10.110.1.39', '2016-02-03 14:30:41');
INSERT INTO `user_loginlog` VALUES ('867', 'admin', '0', '10.110.1.39', '2016-02-03 16:32:49');
INSERT INTO `user_loginlog` VALUES ('868', 'admin', '0', '10.110.1.39', '2016-02-25 13:43:54');
INSERT INTO `user_loginlog` VALUES ('869', 'admin', '0', '10.110.1.39', '2016-02-25 13:46:35');
INSERT INTO `user_loginlog` VALUES ('870', 'admin', '0', '10.110.1.39', '2016-02-25 13:47:01');
INSERT INTO `user_loginlog` VALUES ('871', 'admin', '0', '10.110.1.39', '2016-02-25 13:48:22');
INSERT INTO `user_loginlog` VALUES ('872', 'admin', '0', '10.110.1.39', '2016-02-25 13:50:03');
INSERT INTO `user_loginlog` VALUES ('873', 'admin', '0', '10.110.1.39', '2016-02-25 13:51:28');
INSERT INTO `user_loginlog` VALUES ('874', 'admin', '0', '10.110.1.39', '2016-02-25 13:53:45');
INSERT INTO `user_loginlog` VALUES ('875', 'admin', '0', '10.110.1.39', '2016-02-25 13:59:50');
INSERT INTO `user_loginlog` VALUES ('876', 'admin', '0', '10.110.1.39', '2016-02-25 14:05:03');
INSERT INTO `user_loginlog` VALUES ('877', 'admin', '0', '10.110.1.39', '2016-02-25 14:08:15');
INSERT INTO `user_loginlog` VALUES ('878', 'admin', '0', '10.110.1.39', '2016-02-25 14:11:10');
INSERT INTO `user_loginlog` VALUES ('879', 'admin', '0', '10.110.1.39', '2016-02-25 14:54:38');
INSERT INTO `user_loginlog` VALUES ('880', 'admin', '0', '192.168.150.169', '2016-02-25 15:28:29');
INSERT INTO `user_loginlog` VALUES ('881', 'admin', '0', '192.168.150.169', '2016-02-25 15:39:42');
INSERT INTO `user_loginlog` VALUES ('882', 'admin', '0', '192.168.150.169', '2016-02-25 17:09:27');
INSERT INTO `user_loginlog` VALUES ('883', 'admin', '0', '192.168.150.169', '2016-02-25 17:38:23');
INSERT INTO `user_loginlog` VALUES ('884', 'admin', '0', '192.168.150.169', '2016-02-25 17:50:42');
INSERT INTO `user_loginlog` VALUES ('885', 'admin', '0', '192.168.150.169', '2016-02-25 17:55:16');
INSERT INTO `user_loginlog` VALUES ('886', 'admin', '0', '192.168.150.169', '2016-02-25 18:01:39');
INSERT INTO `user_loginlog` VALUES ('887', 'admin', '0', '192.168.150.169', '2016-02-25 18:41:31');
INSERT INTO `user_loginlog` VALUES ('888', 'admin', '0', '192.168.150.169', '2016-02-25 18:50:47');
INSERT INTO `user_loginlog` VALUES ('889', 'admin', '0', '192.168.150.169', '2016-02-25 19:03:01');
INSERT INTO `user_loginlog` VALUES ('890', 'admin', '0', '192.168.150.169', '2016-02-25 19:05:04');
INSERT INTO `user_loginlog` VALUES ('891', 'admin', '0', '192.168.150.169', '2016-02-25 19:07:47');
INSERT INTO `user_loginlog` VALUES ('892', 'admin', '0', '192.168.150.169', '2016-02-25 20:06:14');
