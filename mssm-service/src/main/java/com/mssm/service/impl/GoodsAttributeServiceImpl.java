package com.mssm.service.impl;

import com.mssm.domain.Color;
import com.mssm.domain.Goods;
import com.mssm.domain.GoodsAttribute;
import com.mssm.domain.Size;
import com.mssm.mapper.GoodsAttributeMapper;
import com.mssm.service.GoodsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsAttributeServiceImpl implements GoodsAttributeService {

    @Autowired
    private GoodsAttributeMapper goodsAttributeMapper;

    @Override
    public void addGoodsAttributeList(Goods goods) {
        goodsAttributeMapper.deleteGoodsAttributeByGId(goods.getId());
        for(GoodsAttribute goodsAttribute: goods.getAttributeList()){
            goodsAttribute.setGid(goods.getId());
            goodsAttribute.setGname(goods.getName());
            goodsAttributeMapper.addGoodsAttribute(goodsAttribute);
        }
    }

    @Override
    public Map<String,Object> queryGoodsAttributeList(Integer gid) {
        List<Color> colorList = goodsAttributeMapper.findColorByGId(gid);
        List<Size> sizeList = goodsAttributeMapper.findSizeByGId(gid);
        Map<String,Object> map = new HashMap<>();
        map.put("colorList", colorList);
        map.put("sizeList", sizeList);
        return map;
    }

    @Override
    public List<GoodsAttribute> findAll() {
        List<GoodsAttribute> all = goodsAttributeMapper.findAll();
        return all;
    }

    @Override
    public List<Goods> queryGoodsWithStock() {
        return goodsAttributeMapper.queryGoodsWithStock();
    }

    /**
     * 添加指定商品的一个数量的库存
     */
    @Override
    public GoodsAttribute plusOne(Integer aid) {
        goodsAttributeMapper.plusOne(aid);
        GoodsAttribute goodsAttribute = goodsAttributeMapper.findById(aid);
        return goodsAttribute;
    }

    /**
     * 减指定商品的一个数量的库存
     */
    @Override
    public GoodsAttribute minusOne(Integer aid) {
        goodsAttributeMapper.minusOne(aid);
        GoodsAttribute goodsAttribute = goodsAttributeMapper.findById(aid);
        return goodsAttribute;
    }

    /**
     * 保存指定商品库存
     */
    @Override
    public void updateStock(GoodsAttribute goodsAttribute) {
        goodsAttributeMapper.updateStock(goodsAttribute);
    }

    /**
     * 删除指定商品的库存项
     */
    @Override
    public List<GoodsAttribute> deleteById(GoodsAttribute goodsAttribute) {
        goodsAttributeMapper.deleteById(goodsAttribute.getId());
        List<GoodsAttribute> goodsAttributeList = goodsAttributeMapper.findByGId(goodsAttribute.getGid());
        return goodsAttributeList;
    }


}
