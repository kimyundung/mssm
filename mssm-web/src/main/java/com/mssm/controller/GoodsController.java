package com.mssm.controller;

import com.github.pagehelper.PageInfo;
import com.mssm.domain.*;
import com.mssm.service.GoodsAttributeService;
import com.mssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    // 查询商品
    @RequestMapping("/query")
    public ResponseResult query(@RequestBody QueryVO queryVO , HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            if(queryVO.getPagesize()!=null && queryVO.getPagenum()!=null){
                //2.获取商品信息
                PageInfo<Goods> goodsPageInfo = goodsService.queryConditionPage(queryVO);
                result.setData(goodsPageInfo);
            } else {
                List<Goods> goods = goodsService.queryCondition(queryVO);
            }
            result.setMeta(new Meta(200,"成功查询商品列表"));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败查询商品列表, 产生意外"));
            return result;
        }

    }

    /**
     * 保存商品(插入+更新)
     * @param goods
     * @param request
     * @return
     */
    @RequestMapping("/addGoods")
    public ResponseResult addGoods(@RequestBody Goods goods, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            goodsService.addGoods(goods);
            result.setMeta(new Meta(200,"成功保存商品"));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败保存商品, 产生意外"));
            return result;
        }

    }

    // 修改商品状态
    @RequestMapping("/status")
    public ResponseResult status(Goods goods, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            // 修改状态
            goodsService.status(goods);
            result.setMeta(new Meta(200,"成功修改商品状态"));
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败修改商品状态, 产生意外"));
            return result;
        }

    }

    // 删除商品
    @RequestMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Integer id, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            // 删除商品
            goodsService.delete(id);
            result.setMeta(new Meta(200,"成功删除商品"));
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败删除商品, 产生意外"));
            return result;
        }

    }

    // 根据商品id查询商品+图片
    @RequestMapping("/query/{id}")
    public ResponseResult queryById(@PathVariable Integer id, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            // 查询
            Goods goods = goodsService.queryById(id);
            result.setData(goods);
            result.setMeta(new Meta(200,"成功查询商品+图片信息"));
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败查询商品+图片信息, 产生意外"));
            return result;
        }

    }
}
