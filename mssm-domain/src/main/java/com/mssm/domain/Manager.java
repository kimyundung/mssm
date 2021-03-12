package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 管理员类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Manager {
    /*
    `id` 	     INT(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `name`     VARCHAR(255) NOT NULL    		  COMMENT '用户昵称',
    `phone`    VARCHAR(255) NOT NULL 		  COMMENT '注册手机',

    `portrait` VARCHAR(255) DEFAULT NULL 		  COMMENT '用户头像地址',
    `password` VARCHAR(255) DEFAULT NULL 		  COMMENT '用户密码（可以为空，支持只用验证码注册、登录）',

    `status`  BIT(1) NOT NULL DEFAULT b'1' COMMENT '用户状态: 0-不能登录, 1-能登录',
    `locked`  BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否锁定: 0-锁定,     1-未锁定',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除: 0-删除,     1-未删除',

    `createtime` DATETIME NOT NULL COMMENT '注册时间',
    `updatetime` DATETIME NULL     COMMENT '更新时间',
    `deletetime` DATETIME NULL     COMMENT '删除时间',*/

    private Integer id;         //用户id
    private String name;        //用户昵称
    private String phone;       //注册手机

    private String portrait;    //用户头像地址
    private String password;    //用户密码（可以为空，支持只用验证码注册、登录）

    private Integer status;     //用户状态: 0-不能登录, 1-能登录
    private Integer locked;     //是否锁定: 0-锁定,     1-未锁定
    private Integer deleted;    //是否删除: 0-删除,     1-未删除

    private Date createtime;    //注册时间
    private Date updatetime;    //更新时间
    private Date deletetime;    //删除时间
}
