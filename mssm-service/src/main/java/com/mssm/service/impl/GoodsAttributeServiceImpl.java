package com.mssm.service.impl;

import com.mssm.domain.*;
import com.mssm.mapper.GoodsAttributeMapper;
import com.mssm.mapper.GoodsCategoryMapper;
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
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 保存商品属性和分类(新增+更新)
     * 1. 查询已有属性和分类
     * 2. 添加和删除操作
     * @param goods
     */
    @Override
    public void addGoodsAttrAndCat(Goods goods) {

        // 1 属性
        //  根据商品id获取商品属性列表
        List<GoodsAttribute> oldGAL = goodsAttributeMapper.findByGId(goods.getId());
        //  页面传递过来的商品属性列表
        List<GoodsAttribute> newGAL = goods.getAttributeList();
        //  oldStock 赋值给 newStock
        for (GoodsAttribute newGA : newGAL) {
            // old相同的项new有, 则将库存数量赋值给new
            newGA.setGid(goods.getId());
            newGA.setGname(goods.getName());
            newGA.setStock(0);
            for (GoodsAttribute oldGA : oldGAL) {
                if(newGA.getCid()==oldGA.getCid() && newGA.getSid()==oldGA.getSid()){
                    newGA.setStock(oldGA.getStock());
                    break;
                }
            }
        }
        //System.out.println("--------------- oldGAL: "+oldGAL);
        //System.out.println("--------------- newGAL: "+newGAL);
        // 删除oldAttr
        goodsAttributeMapper.deleteByGId(goods.getId());
        // 添加newAttr
        for (GoodsAttribute newGA : newGAL) {
            goodsAttributeMapper.add(newGA);
        }

        // 2 分类
        //  删除oldCat
        goodsCategoryMapper.deleteByGId(goods.getId());
        //  添加newCat
        List<GoodsCategory> newGCL = goods.getCategoryList();
        for (GoodsCategory newGC : newGCL) {
            newGC.setGid(goods.getId());
            newGC.setGname(goods.getName());
            goodsCategoryMapper.add(newGC);
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
