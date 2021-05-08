package com.mssm.service;

import com.mssm.domain.Goods;
import com.mssm.domain.GoodsAttribute;

import java.util.List;
import java.util.Map;

public interface GoodsAttributeService {

    /**
     * 保存商品属性和分类(新增+更新)
     * @param goods
     */
    public void addGoodsAttrAndCat(Goods goods);

    /**
     * 根据商品id 查询商品属性信息
     * @param gid
     * @return
     */
    public Map<String,Object> queryGoodsAttributeList(Integer gid);

    /**
     * 查询所有商品属性
     * @return
     */
    public List<GoodsAttribute> findAll();

    /**
     * 查询商品+库存
     * @return
     */
    public List<Goods> queryGoodsWithStock();

    /**
     * 添加指定商品的一个数量的库存
     */
    public GoodsAttribute plusOne(Integer aid);

    /**
     * 减指定商品的一个数量的库存
     */
    public GoodsAttribute minusOne(Integer aid);

    /**
     * 保存指定商品库存
     */
    public void updateStock(GoodsAttribute goodsAttribute);

    /**
     * 删除指定商品的库存项
     */
    public List<GoodsAttribute> deleteById(GoodsAttribute goodsAttribute);
}
