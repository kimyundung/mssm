package com.mssm.service;

import com.mssm.domain.Color;
import com.mssm.domain.GoodsStock;
import com.mssm.domain.Size;

import java.util.List;
import java.util.Map;

public interface GoodsStockService {

    /**
     * 添加商品库存
     * @param goodsStockList
     */
    public void addGoodsStockList(GoodsStock[] goodsStockList);

    /**
     * 根据商品id 查询商品库存相关信息
     * @param gid
     * @return
     */
    public Map<String,Object> queryGoodsStockList(Integer gid);

}
