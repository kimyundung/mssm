package com.mssm.controller;

import com.mssm.domain.Meta;
import com.mssm.domain.ResponseResult;
import com.mssm.domain.Swiper;
import com.mssm.service.SwiperService;
import com.mssm.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/swiper")
public class SwiperController {
    @Autowired
    private SwiperService swiperService;

    // 查询所有轮播图
    @RequestMapping("/queryAll")
    public ResponseResult queryAll(){
        ResponseResult result = new ResponseResult();
        try {
            List<Swiper> swiperList = swiperService.queryAll();
            result.setData(swiperList);
            result.setMeta(new Meta(200, "成功获取轮播图集合"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500, "失败获取轮播图集合, 产生意外"));
            return result;
        }
    }

    // 根据id删除轮播图
    @RequestMapping("/delete")
    public ResponseResult deleteById(@RequestBody Swiper file){
        ResponseResult result = new ResponseResult();
        try {
            // 数据库删除
            swiperService.deleteById(file.getId());
            // 服务器删除
            Boolean delete = UploadUtil.delete("", file.getFileId());
            if(delete){
                result.setMeta(new Meta(200,"成功删除轮播图"));
            } else{
                result.setMeta(new Meta(501,"成功删除轮播图, 图片服务器失败, 库存成功"));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500, "失败删除轮播图, 产生意外"));
            return result;
        }
    }
}
