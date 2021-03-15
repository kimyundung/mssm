package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 商品分类表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    /*
    `id` 	  INT(11) 	NOT NULL AUTO_INCREMENT COMMENT '分类唯一ID',
    `pid`     INT(11) 	NOT NULL 		COMMENT '分类父ID',
    `name`    VARCHAR(32) 	NOT NULL 		COMMENT '分类名称',
    `icon`    VARCHAR(255)  DEFAULT NULL 		COMMENT '分类图标',
    `src`     TEXT 					COMMENT '指向外部资源的位置',

    `level`   INT(4) 	NOT NULL 		COMMENT '分类层级 1:顶级 2:二级',
    `status`  TINYINT(1) 	DEFAULT '1' 		COMMENT '0:不可用  1:可用',

    `createtime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录生成时间',
    `updatetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间',*/

    private Integer id;     // 分类唯一ID
    private Integer pid;    // 分类父ID
    private String name;    // 分类名称
    private String icon;    // 分类图标
    private String src;     // 指向外部资源的位置
    private Integer level;  // 分类层级 1:顶级 2:二级
    private Integer deleted;// 0:未删除 1:删除
    private Integer status; // 0:不可用  1:可用
    private Date createtime;    // 记录生成时间
    private Date updatetime;    // 记录修改时间
    private Date deletetime;    // 记录删除时间

    private List<Category> children;
}
