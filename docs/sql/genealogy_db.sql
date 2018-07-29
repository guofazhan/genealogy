/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : genealogy_db

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2018-07-29 22:27:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '区域ID',
  `CODE` varchar(40) DEFAULT '' COMMENT '区域编号',
  `PARENT_ID` int(11) NOT NULL COMMENT '父级',
  `NAME` varchar(128) NOT NULL DEFAULT '' COMMENT '地区名称',
  `REMARK` text COMMENT '描述',
  `SORT` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `IS_DEL` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识',
  `CREATE_BY` varchar(64) DEFAULT '' COMMENT '创建人',
  `CREATE_TIME` varchar(14) NOT NULL COMMENT '创建时间',
  `LAST_MODIFY_BY` varchar(64) DEFAULT '' COMMENT '最后修改人',
  `LAST_MODIFY_TIME` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='地区表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('4', '0001', '0', '中国', '顶级目录，国家', '1', '0', 'admin', '20180729102357', 'admin', '20180729102749');
INSERT INTO `sys_area` VALUES ('5', '0029', '4', '陕西', '二级地区，省份', '10', '0', 'admin', '20180729102827', 'admin', '20180729102827');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `NAME` varchar(64) DEFAULT '' COMMENT '菜单名称',
  `TYPE` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '菜单类型0：目录 1：菜单 2：按钮',
  `URL` varchar(255) DEFAULT NULL COMMENT '菜单对应的URL',
  `PARENT_ID` int(10) unsigned NOT NULL COMMENT '父菜单ID',
  `SORT` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `CODE` varchar(255) DEFAULT NULL COMMENT '菜单代码字符串',
  `ICON` varchar(255) DEFAULT NULL COMMENT '菜单对应的icon',
  `IS_DEL` int(10) unsigned DEFAULT '0',
  `CREATE_BY` varchar(255) DEFAULT NULL,
  `CREATE_TIME` varchar(14) DEFAULT NULL,
  `LAST_MODIFY_BY` varchar(255) DEFAULT NULL,
  `LAST_MODIFY_TIME` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', ' ', '0', '0', '', 'fa fa-desktop', '0', ' ', '', 'admin', '20180728165403');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '1', 'sys/user/', '1', '1', null, 'fa fa-user', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', '1', 'sys/menu/', '1', '2', null, 'fa fa-th-list', '0', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('5', '职位管理', '1', 'sys/position', '1', '3', '', 'fa fa-briefcase', '0', 'admin', '20180728165524', 'admin', '20180728165701');
INSERT INTO `sys_menu` VALUES ('6', '地区管理', '1', 'sys/area', '1', '4', '', 'fa fa-map-marker', '0', 'admin', '20180728165911', 'admin', '20180728165911');
INSERT INTO `sys_menu` VALUES ('7', '添加', '2', '', '3', '1', 'sys:menu:add', '', '0', 'admin', '20180728170341', 'admin', '20180728170341');
INSERT INTO `sys_menu` VALUES ('8', '编辑', '2', '', '3', '2', 'sys:menu:edit', '', '0', 'admin', '20180728170440', 'admin', '20180728170440');
INSERT INTO `sys_menu` VALUES ('9', '删除', '2', '', '3', '3', 'sys:menu:remove', '', '0', 'admin', '20180728170524', 'admin', '20180728170524');
INSERT INTO `sys_menu` VALUES ('12', '系统监控', '0', '', '0', '8', '', 'fa fa-video-camera', '0', 'admin', '20180728181457', 'admin', '20180728181457');
INSERT INTO `sys_menu` VALUES ('13', '运行状态', '1', 'druid/index.html', '12', '1', '', 'fa fa-object-ungroup', '0', 'admin', '20180728181640', 'admin', '20180728181640');
INSERT INTO `sys_menu` VALUES ('14', '角色管理', '1', 'sys/role', '1', '5', '', 'fa fa-group', '0', 'admin', '20180728181947', 'admin', '20180728182101');
INSERT INTO `sys_menu` VALUES ('15', '家系管理', '0', '', '0', '4', '', 'fa fa-sitemap', '0', 'admin', '20180729144602', 'admin', '20180729144602');
INSERT INTO `sys_menu` VALUES ('16', '任务管理', '0', '', '0', '7', '', 'fa fa-bullhorn', '0', 'admin', '20180729144819', 'admin', '20180729144819');
INSERT INTO `sys_menu` VALUES ('17', '户口登记', '1', 'family/rr', '15', '1', '', 'fa fa-fax', '0', 'admin', '20180729183113', 'admin', '20180729215540');
INSERT INTO `sys_menu` VALUES ('18', '常驻人口管理', '1', 'family/rp', '15', '2', '', 'fa fa-bar-chart', '0', 'admin', '20180729183217', 'admin', '20180729215612');
INSERT INTO `sys_menu` VALUES ('19', '犯罪人员管理', '1', 'family/criminals', '15', '3', '', 'fa fa-male', '0', 'admin', '20180729183435', 'admin', '20180729215633');
INSERT INTO `sys_menu` VALUES ('20', '家系图管理', '1', 'family/tree', '15', '4', '', 'fa fa-sitemap', '0', 'admin', '20180729183523', 'admin', '20180729215652');
INSERT INTO `sys_menu` VALUES ('21', '报表管理', '0', '', '0', '11', '', 'fa fa-bar-chart-o', '0', 'admin', '20180729184202', 'admin', '20180729184202');
INSERT INTO `sys_menu` VALUES ('22', '统计报表', '1', 'report/sr', '21', '1', '', 'fa fa-bar-chart', '0', 'admin', '20180729184336', 'admin', '20180729215719');
INSERT INTO `sys_menu` VALUES ('23', '日志管理', '1', 'report/log', '21', '2', '', 'fa fa-file-code-o', '0', 'admin', '20180729184505', 'admin', '20180729215738');

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '职位ID',
  `NAME` varchar(64) NOT NULL DEFAULT '' COMMENT '职位名称',
  `REMARK` text COMMENT '职位描述',
  `IS_DEL` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识',
  `CREATE_BY` varchar(255) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` varchar(14) DEFAULT NULL COMMENT '创建时间',
  `LAST_MODIFY_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `LAST_MODIFY_TIME` varchar(14) DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='人员职位表';

-- ----------------------------
-- Records of sys_position
-- ----------------------------
INSERT INTO `sys_position` VALUES ('1', '系统管理员', '虚拟职位，仅用在超级管理员账户上', '1', 'admin', '20180729082630', 'admin', '20180729082630');
INSERT INTO `sys_position` VALUES ('2', '系统管理员', '虚拟职位，仅用在超级管理员账户上', '0', 'admin', '20180729083728', 'admin', '20180729084812');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `NAME` varchar(64) NOT NULL COMMENT '角色名称',
  `REMARK` text COMMENT '职位描述',
  `IS_DEL` int(10) unsigned DEFAULT '0' COMMENT '删除标识',
  `IS_LOCK` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '锁标识',
  `CREATE_BY` varchar(64) DEFAULT NULL,
  `CREATE_TIME` varchar(14) DEFAULT NULL,
  `LAST_MODIFY_BY` varchar(64) DEFAULT NULL,
  `LAST_MODIFY_TIME` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='账号角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('5', '超级管理员角色', '超级管理员角色，具有最高权限，负责维护系统信息', '0', '0', 'admin', '20180728231605', 'admin', '20180728233957');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色菜单关联ID',
  `ROLE_ID` int(10) unsigned DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` int(10) unsigned DEFAULT NULL COMMENT '菜单ID',
  `IS_DEL` int(10) unsigned DEFAULT NULL COMMENT '删除标识',
  `CREATE_BY` varchar(64) DEFAULT NULL,
  `CREATE_TIME` varchar(14) DEFAULT NULL,
  `LAST_MODIFY_BY` varchar(64) DEFAULT NULL,
  `LAST_MODIFY_TIME` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('25', '5', '7', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('26', '5', '8', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('27', '5', '9', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('28', '5', '2', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('29', '5', '3', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('30', '5', '5', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('31', '5', '6', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('32', '5', '14', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('33', '5', '13', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('34', '5', '1', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('35', '5', '12', '1', 'admin', '20180728231605', 'admin', '20180728231605');
INSERT INTO `sys_role_menu` VALUES ('36', '5', '7', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('37', '5', '8', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('38', '5', '9', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('39', '5', '2', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('40', '5', '3', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('41', '5', '5', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('42', '5', '6', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('43', '5', '14', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('44', '5', '13', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('45', '5', '1', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('46', '5', '12', '1', 'admin', '20180728233917', 'admin', '20180728233917');
INSERT INTO `sys_role_menu` VALUES ('47', '5', '2', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('48', '5', '8', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('49', '5', '9', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('50', '5', '5', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('51', '5', '6', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('52', '5', '14', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('53', '5', '13', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('54', '5', '12', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('55', '5', '1', '0', 'admin', '20180728233957', 'admin', '20180728233957');
INSERT INTO `sys_role_menu` VALUES ('56', '5', '3', '0', 'admin', '20180728233957', 'admin', '20180728233957');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `LOGIN_NAME` varchar(64) NOT NULL COMMENT '用户登录名称',
  `PASSWORD` varchar(64) NOT NULL COMMENT '登录密码',
  `SHOW_NAME` varchar(64) NOT NULL COMMENT '账号的显示名',
  `POSITION` varchar(64) NOT NULL DEFAULT '' COMMENT '账号对应的职位信息',
  `SEX` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '账号的性别信息',
  `EMAIL` varchar(64) NOT NULL DEFAULT '' COMMENT '账号邮箱信息',
  `MOBILE` varchar(64) NOT NULL DEFAULT '' COMMENT '账号电话信息',
  `ADDRESS` text COMMENT '账号居住地信息',
  `IS_LOCK` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '账号锁标识',
  `IS_DEL` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识',
  `CREATE_BY` varchar(64) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` varchar(14) DEFAULT NULL COMMENT '创建时间',
  `LAST_MODIFY_BY` varchar(64) DEFAULT NULL COMMENT '最后更新人',
  `LAST_MODIFY_TIME` varchar(14) DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'guofazhan', '812e932b1b2ed8cb2f89abc079bc7dc0', '郭发展', '', '0', '', '', '', '0', '0', 'admin', '20180729142149', 'admin', '20180729142149');
