package com.mssm.mapper;

import com.mssm.domain.Color;
import com.mssm.domain.Goods;
import com.mssm.domain.GoodsAttribute;
import com.mssm.domain.Size;

import java.util.List;

public interface GoodsAttributeMapper {
    /**
     * 添加商品库存
     * @param goodsAttribute
     */
    public void add(GoodsAttribute goodsAttribute);

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

    /**
     * 查询所有
     * @return
     */
    public List<GoodsAttribute> findAll();

    /**
     * 根据商品id查询
     * @return
     */
    public List<GoodsAttribute> findByGId(Integer gid);

    /**
     * 根据商品id删除
     * @param gid
     */
    public void deleteByGId(Integer gid);

    /**
     * 查询商品+库存
     * @return
     */
    public List<Goods> queryGoodsWithStock();

    /**
     * 添加指定商品的一个数量的库存
     * @param aid
     */
    public void plusOne(Integer aid);

    /**
     * 根据属性id查询属性
     * @param id
     * @return
     */
    public GoodsAttribute findById(Integer id);

    /**
     * 减指定商品的一个数量的库存
     */
    public void minusOne(Integer aid);

    /**
     * 保存指定商品库存
     */
    public void updateStock(GoodsAttribute goodsAttribute);

    /**
     * 删除指定商品的库存项
     */
    public void deleteById(Integer id);

    /**
     * 根据商品id逻辑删除
     */
    public void deleteByGIdLogic(Integer Gid);

    /**
     * 根据商品id查询属性cnt
     */
    public Integer cntByGId(Integer gid);
}
