package com.mssm.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import com.mssm.domain.*;
import com.mssm.service.FileService;
import com.mssm.service.GoodsService;
import com.mssm.utils.JwtUtil;
import com.mssm.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final String AUTH = "Authorization";

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FileService fileService;

    /**
     * 保存商品
     * @param goods
     * @param request
     * @return
     */
    @RequestMapping("/addGoods")
    public ResponseResult addGoods(@RequestBody Goods goods, HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>>>>>> " + goods);
        ResponseResult result = new ResponseResult();
        if(JwtUtil.verify(request.getHeader(AUTH))){
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

        result.setMeta(new Meta(500,"失败保存商品, token失效"));
        return null;
    }

    // 查询商品
    @RequestMapping("/query")
    public ResponseResult query(@RequestBody QueryVO queryVO , HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>>>>>>>> "+queryVO);
        ResponseResult result = new ResponseResult();
        //1.token验证
        if(JwtUtil.verify(request.getHeader(AUTH))){
            try{
                //2.获取商品信息
                PageInfo<Goods> goodsPageInfo = goodsService.query(queryVO);
                result.setData(goodsPageInfo);
                result.setMeta(new Meta(200,"成功查询商品列表"));
                return result;
            }catch (Exception e){
                e.printStackTrace();
                result.setMeta(new Meta(500,"失败查询商品列表, 产生意外"));
                return result;
            }
        }
        result.setMeta(new Meta(500,"失败查询商品列表, token失效"));
        return result;
    }

    // 修改商品状态
    @RequestMapping("/status")
    public ResponseResult status(Goods goods, HttpServletRequest request){
        System.out.println(">>>>>>>>>>>> " + goods.getId() + " " + goods.getStatus());
        ResponseResult result = new ResponseResult();
        //1.token验证
        if(JwtUtil.verify(request.getHeader(AUTH))){
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
        result.setMeta(new Meta(500,"失败修改商品状态, token失效"));
        return result;
    }

    // 删除商品
    @RequestMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Integer id, HttpServletRequest request){
        System.out.println(">>>>>>>>>>> " + id);
        ResponseResult result = new ResponseResult();
        //1.token验证
        if(JwtUtil.verify(request.getHeader(AUTH))){
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
        result.setMeta(new Meta(500,"失败删除商品, token失效"));
        return result;
    }

    // 根据商品id查询商品+图片
    @RequestMapping("/query/{id}")
    public ResponseResult queryById(@PathVariable Integer id, HttpServletRequest request){
        System.out.println(">>>>>>>>>>>>>> " + id);
        ResponseResult result = new ResponseResult();
        //1.token验证
        if(JwtUtil.verify(request.getHeader(AUTH))){
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
        result.setMeta(new Meta(500,"失败查询商品+图片信息, token失效"));
        return result;
    }
}
