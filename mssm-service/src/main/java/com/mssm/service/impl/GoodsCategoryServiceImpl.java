package com.mssm.service.impl;

import com.mssm.domain.Goods;
import com.mssm.domain.GoodsCategory;
import com.mssm.mapper.GoodsCategoryMapper;
import com.mssm.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 保存商品分类信息(新增+更新)
     * @param goods
     */
    @Override
    public void addGoodsCategoryList(Goods goods) {
        //1 先删除
        goodsCategoryMapper.deleteByGId(goods.getId());
        //2 在添加
        for(GoodsCategory goodsCategory: goods.getCategoryList()){
            goodsCategory.setGid(goods.getId());
            goodsCategory.setGname(goods.getName());
            goodsCategoryMapper.add(goodsCategory);
        }
    }

    /**
     * 根据商品id 查询商品分类信息
     * @param gid
     * @return
     */
    @Override
    public List<GoodsCategory> queryGoodsCategoryList(Integer gid) {
        return goodsCategoryMapper.findByGId(gid);
    }
}
