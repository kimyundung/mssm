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
    public void add(GoodsCategory goodsCategory);

    /**
     * 根据商品id 删除相关商品分类信息
     * @param gid
     */
    public void deleteByGId(Integer gid);

    /**
     * 根据商品id 查询商品分类信息
     * @param gid
     * @return
     */
    public List<GoodsCategory> findByGId(Integer gid);

    /**
     * 根据商品id逻辑删除
     * @param gid
     */
    public void deleteByGIdLogic(Integer gid);

    /**
     * 根据商品id查询分类cnt
     */
    public Integer cntByGId(Integer gid);
}
