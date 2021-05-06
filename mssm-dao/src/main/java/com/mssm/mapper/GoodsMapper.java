package com.mssm.mapper;

import com.mssm.domain.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    /**
     * 添加商品
     * @param goods 商品信息
     */
    public void addGoods(Goods goods);

    /**
     * 查询所有商品
     * @return 返回List商品集合
     */
    public List<Goods> queryAll();

    /**
     * 条件查询
     * @param name
     * @param status
     * @return
     */
    public List<Goods> query(@Param("name") String name, @Param("status") Integer status);

    /**
     * 修改商品状态
     * @param id
     * @param status
     */
    public void status(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 逻辑删除商品
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据商品id查询商品
     * @param id
     * @return
     */
    public Goods queryById(Integer id);

    /**
     * 编辑商品
     * @param goods
     */
    public void updateGoods(Goods goods);

}
