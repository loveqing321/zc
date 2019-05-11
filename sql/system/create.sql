CREATE DATABASE `zc` DEFAULT CHARACTER SET utf8;

USE `zc`;
SET FOREIGN_KEY_CHECKS=0;

/*
  用户管理 - 这里的用户是指 商户的共享中心用户
*/
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商户ID，一个商户可能有多个账号',
  `username` varchar(50) NOT NULL COMMENT '用户名，常和电话号码一致',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `cname` varchar(50) DEFAULT NULL COMMENT '中文名',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `department` varchar(20) DEFAULT NULL COMMENT '所属部门',
  `post` varchar(20) DEFAULT NULL COMMENT '职位',
  `account_expired` int(1) DEFAULT 0 COMMENT '账号是否过期',
  `account_locked` int(1) DEFAULT 0 COMMENT '账号是否锁定',
  `credentials_expired` int(1) DEFAULT 0 COMMENT '密码是否过期',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '修改用户',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_t_sys_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*
  角色管理 - 用户组的概念
*/
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '修改用户',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_t_sys_role_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*
  用户和角色关联表
*/
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联ID，主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*
  权限表
*/
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `perm` varchar(50) NOT NULL COMMENT '权限字符串',
  `category` varchar(50) NOT NULL COMMENT '权限类别',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '修改用户',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_t_sys_permission_perm` (`perm`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*
  权限表
*/
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联ID，主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;