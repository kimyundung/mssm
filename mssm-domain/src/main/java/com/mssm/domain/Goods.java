package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 商品类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods {
    private Integer id;         // 主键id
    private Integer cid;        // 分类id
    private String name;        // 商品名称
    private Double price;       // 商品价格
    private String introduce;   // 商品详情介绍
    private Integer hnumber;    // 热卖数量
    private Integer promote;    // 是否促销
    private Integer status;     // 商品状态 0:下架 1:上架 2:审核
    private Integer deleted;    // 0:正常  1:删除
    private Date createtime;    // 添加商品时间
    private Date updatetime;    // 修改商品时间
    private Date deletetime;    // 软删除标志字段

    private List<File> fileList;
    private List<File> fileList3;

    private List<GoodsCategory> categoryList;
    private List<GoodsAttribute> attributeList;

}
