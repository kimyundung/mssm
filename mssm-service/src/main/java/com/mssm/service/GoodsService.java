package com.mssm.service;

import com.github.pagehelper.PageInfo;
import com.mssm.domain.Goods;
import com.mssm.domain.QueryInfo;
import com.mssm.domain.QueryVO;

import java.util.List;

public interface GoodsService {
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
     * 条件+分页查询
     * @param queryVO
     * @return
     */
    public PageInfo<Goods> query(QueryVO queryVO);

    /**
     * 修改商品状态
     * @param goods
     */
    public void status(Goods goods);

    /**
     * 删除商品
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据商品id查询商品+图片信息
     * @param id
     * @return
     */
    public Goods queryById(Integer id);
}
