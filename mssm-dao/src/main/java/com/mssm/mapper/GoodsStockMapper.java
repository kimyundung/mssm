package com.mssm.mapper;

import com.mssm.domain.Color;
import com.mssm.domain.GoodsStock;
import com.mssm.domain.Size;

import java.util.List;

public interface GoodsStockMapper {
    /**
     * 添加商品库存
     * @param goodsStock
     */
    public void addGoodsStock(GoodsStock goodsStock);

    /**
     * 更新商品库存
     * @param goodsStock
     */
    public void updateGoodsStock(GoodsStock goodsStock);

    /**
     * 根据商品id 查询商品库存相关信息
     * @param gid
     * @return
     */
    public List<GoodsStock> queryGoodsStockList(Integer gid);


    /**
     * 根据id查询颜色信息
     * @param gid
     * @return
     */
    public List<Color> findColorByGId(Integer gid);

    /**
     * 根据id查询尺码信息
     * @param gid
     * @return
     */
    public List<Size> findSizeByGId(Integer gid);
}
