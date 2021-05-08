package com.mssm.controller;

import com.mssm.domain.*;
import com.mssm.service.GoodsAttributeService;
import com.mssm.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsAttribute")
public class GoodsAttributeController {
    @Autowired
    private GoodsAttributeService goodsAttributeService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    // 保存商品属性和分类(新增+更新)
    @RequestMapping("/addGoodsAttrAndCat")
    public ResponseResult addGoodsAttrAndCat(@RequestBody Goods goods){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>>>>>>>> "+ goods);
        try {
            goodsAttributeService.addGoodsAttrAndCat(goods); // 保存属性
            result.setMeta(new Meta(200,"成功保存商品相关属性"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(200,"失败保存商品相关属性, 产生意外"));
            return result;
        }
    }

    /**
     * 根据商品id查询属性
     * @param gid
     * @return
     */
    @RequestMapping("/query/{gid}")
    public ResponseResult queryByGid(@PathVariable Integer gid){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>>>>>>>> gid: " + gid);
        try {
            Map<String, Object> map = goodsAttributeService.queryGoodsAttributeList(gid);
            List<GoodsCategory> categoryList = goodsCategoryService.queryGoodsCategoryList(gid);
            map.put("categoryList",categoryList);
            result.setData(map);
            result.setMeta(new Meta(200,"成功查询商品属性"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败查询商品属性, 产生意外"));
            return result;
        }
    }

    /**
     * 查询所有商品属性
     * @return
     */
    @RequestMapping("/queryAll")
    public ResponseResult queryAll(){
        ResponseResult result = new ResponseResult();
        try {
            List<GoodsAttribute> goodsAttributeList = goodsAttributeService.findAll();
            result.setData(goodsAttributeList);
            result.setMeta(new Meta(200,"成功获取商品库存"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败获取商品库存, 产生意外"));
            return result;
        }
    }

    /**
     * 查询所有商品和库存信息
     * @return
     */
    @RequestMapping("/queryGoodsWithStock")
    public ResponseResult queryGoodsWithStock(){
        ResponseResult result = new ResponseResult();
        try {
            List<Goods> goods = goodsAttributeService.queryGoodsWithStock();
            result.setData(goods);
            result.setMeta(new Meta(200,"成功获取商品库存"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(200,"失败获取商品库存, 产生意外"));
            return result;
        }
    }

    /**
     * 添加指定商品的一个数量的库存
     */
    @PostMapping("/plusOne/{aid}")
    public ResponseResult plusOne(@PathVariable Integer aid){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>>>>>>>>>>>> "+aid);
        try {
            GoodsAttribute goodsAttribute = goodsAttributeService.plusOne(aid);
            result.setData(goodsAttribute);
            result.setMeta(new Meta(200,"成功添加一个库存"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败添加一个库存, 产生意外"));
            return result;
        }
    }

    /**
     * 减指定商品的一个数量的库存
     */
    @PostMapping("/minusOne/{aid}")
    public ResponseResult minusOne(@PathVariable Integer aid){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>>>>>>>>>>>> "+aid);
        try {
            GoodsAttribute goodsAttribute = goodsAttributeService.minusOne(aid);
            result.setData(goodsAttribute);
            result.setMeta(new Meta(200,"成功减一个库存"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败减一个库存, 产生意外"));
            return result;
        }
    }

    /**
     * 保存指定商品库存
     */
    @RequestMapping("/updateStock")
    public ResponseResult updateStock(@RequestBody GoodsAttribute goodsAttribute){
        ResponseResult result = new ResponseResult();
        try {
            goodsAttributeService.updateStock(goodsAttribute);
            result.setMeta(new Meta(200, "成功保存库存"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500, "失败保存库存, 产生意外"));
            return result;
        }
    }

    /**
     * 删除指定商品的库存项
     */
    @RequestMapping("/deleteById")
    public ResponseResult deleteById(@RequestBody GoodsAttribute goodsAttribute){
        ResponseResult result = new ResponseResult();
        try {
            List<GoodsAttribute> goodsAttributeList = goodsAttributeService.deleteById(goodsAttribute);
            result.setData(goodsAttributeList);
            result.setMeta(new Meta(200,"成功删除库存项"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除库存项, 产生意外"));
            return result;
        }
    }
}
