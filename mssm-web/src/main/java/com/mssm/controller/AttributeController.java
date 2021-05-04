package com.mssm.controller;

import com.mssm.domain.Color;
import com.mssm.domain.Meta;
import com.mssm.domain.ResponseResult;
import com.mssm.domain.Size;
import com.mssm.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @RequestMapping("/queryAllColor")
    public ResponseResult queryAllColor(){
        ResponseResult result = new ResponseResult();
        try {
            List<Color> colorList = attributeService.findAllColor();
            result.setData(colorList);
            result.setMeta(new Meta(200,"成功查询颜色信息"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败查询颜色信息, 产生意外"));
            return result;
        }
    }

    @RequestMapping("/queryAllSize")
    public ResponseResult queryAllSize(){
        ResponseResult result = new ResponseResult();

        try {
            List<Size> sizeList = attributeService.findAllSize();
            result.setData(sizeList);
            result.setMeta(new Meta(200, "成功查询尺码信息"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500,"失败查询尺码信息, 产生意外"));
            return result;
        }
    }

}
