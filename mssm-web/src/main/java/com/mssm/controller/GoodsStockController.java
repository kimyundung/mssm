package com.mssm.controller;

import com.mssm.domain.GoodsStock;
import com.mssm.domain.Meta;
import com.mssm.domain.ResponseResult;
import com.mssm.mapper.AttributeMapper;
import com.mssm.service.GoodsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goodsStock")
public class GoodsStockController {
    @Autowired
    private GoodsStockService goodsStockService;

    /**
     * 保存商品库存(新僧+更新)
     * @param goodsStockList
     * @return
     */
    @RequestMapping("/addStockList")
    public ResponseResult addStockList(@RequestBody GoodsStock[] goodsStockList){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>>>>> " + Arrays.toString(goodsStockList));
        try {
            goodsStockService.addGoodsStockList(goodsStockList);
            result.setMeta(new Meta(200,"成功保存商品库存信息"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败保存商品库存信息, 产生意外"));
            return result;
        }
    }

    /**
     * 根据商品id 查询商品库存信息
     * @param gid
     * @return
     */
    @RequestMapping("/queryStockList/{gid}")
    public ResponseResult queryStockList(@PathVariable Integer gid){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>>> gid: "+gid);
        try {
            Map<String, Object> map = goodsStockService.queryGoodsStockList(gid);
            result.setData(map);
            result.setMeta(new Meta(200,"成功查询商品库存相关信息"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败查询商品库存相关信息, 产生意外"));
            return result;
        }
    }

}
