package com.mssm.service.impl;

import com.mssm.domain.Color;
import com.mssm.domain.GoodsStock;
import com.mssm.domain.Size;
import com.mssm.mapper.GoodsStockMapper;
import com.mssm.service.GoodsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsStockServiceImpl implements GoodsStockService {

    @Autowired
    private GoodsStockMapper goodsStockMapper;

    @Override
    public void addGoodsStockList(GoodsStock[] goodsStockList) {
        List<GoodsStock> goodsStockList1 = goodsStockMapper.queryGoodsStockList(goodsStockList[0].getGid());
        for(GoodsStock goodsStock: goodsStockList){
            int cnt = 0; //记录相同数据
            for(GoodsStock goodsStock1: goodsStockList1){
                if(goodsStock1.getCid().equals(goodsStock.getCid()) && goodsStock1.getSid().equals(goodsStock.getSid())){
                    //update
                    cnt++;
                }
            }
            if(cnt>0){
                //update
                goodsStockMapper.updateGoodsStock(goodsStock);
            } else {
                //insert
                goodsStockMapper.addGoodsStock(goodsStock);
            }
        }
    }

    @Override
    public Map<String,Object> queryGoodsStockList(Integer gid) {
        List<GoodsStock> goodsStockList = goodsStockMapper.queryGoodsStockList(gid);
        List<Color> colorList = goodsStockMapper.findColorByGId(gid);
        List<Size> sizeList = goodsStockMapper.findSizeByGId(gid);
        Map<String,Object> map = new HashMap<>();
        map.put("goodsStockList", goodsStockList);
        map.put("colorList", colorList);
        map.put("sizeList", sizeList);
        return map;
    }
}
