package com.mssm.service;

import com.mssm.domain.Goods;
import com.mssm.domain.GoodsCategory;

import java.util.List;

public interface GoodsCategoryService {

    /**
     * 保存商品分类信息(新增+更新)
     * @param goods
     */
    public void addGoodsCategoryList(Goods goods);

    /**
     * 根据商品id 查询商品分类信息
     * @param gid
     * @return
     */
    public List<GoodsCategory> queryGoodsCategoryList(Integer gid);
}
