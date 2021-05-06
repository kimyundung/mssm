package com.mssm.mapper;

import com.mssm.domain.GoodsCategory;

import java.util.List;

public interface GoodsCategoryMapper {
    /**
     * 查询所有
     * @return
     */
    public List<GoodsCategory> findAll();

    /**
     * 新增商品分类
     * @param goodsCategory
     */
    public void addGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 根据商品id 删除相关商品分类信息
     * @param gid
     */
    public void deleteGoodsCategoryByGId(Integer gid);

    /**
     * 根据商品id 查询商品分类信息
     * @param gid
     * @return
     */
    public List<GoodsCategory> findByGId(Integer gid);
}
