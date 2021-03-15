package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 菜单表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu {
    /*
    `id` 	INT(11) 	NOT NULL AUTO_INCREMENT COMMENT 'id',
    `pid` 	INT(11) 	NOT NULL 	COMMENT '父菜单id -1顶级菜单',
    `href` 	VARCHAR(200) 	DEFAULT NULL 	COMMENT '菜单路径',
    `icon` 	VARCHAR(200) 	DEFAULT NULL 	COMMENT '菜单图标',
    `name` 	VARCHAR(200) 	DEFAULT NULL 	COMMENT '菜单名称',

    `description` VARCHAR(500) 	DEFAULT NULL 	COMMENT '描述',
    `shown` 	TINYINT(1) 	DEFAULT '1' 	COMMENT '是否展示',
    `onum` 	    INT(11) 	DEFAULT NULL 	COMMENT '排序号',
    `level` 	INT(11) 	NOT NULL 	COMMENT '菜单层级，从0开始',

    `createtime` 	DATETIME 	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updatetime` 	DATETIME 	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',*/

    private Integer id;     // id
    private Integer pid;    // 父菜单id -1顶级菜单
    private String href;    // 菜单路径
    private String icon;    // 菜单图标
    private String name;    // 菜单名称

    private String description; // 描述
    private Integer shown;      // 是否展示
    private Integer onum;       // 排序号
    private Integer level;      // 菜单层级，从0开始

    private Date createtime;    // 创建时间
    private Date updatetime;    // 更新时间

    private List<Menu> subMenu; // 子菜单
}

