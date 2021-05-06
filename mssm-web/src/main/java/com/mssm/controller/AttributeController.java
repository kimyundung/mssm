package com.mssm.controller;

import com.mssm.domain.Color;
import com.mssm.domain.Meta;
import com.mssm.domain.ResponseResult;
import com.mssm.domain.Size;
import com.mssm.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 添加颜色并返回颜色列表
     * @param name
     * @return
     */
    @RequestMapping("/addColor/{name}")
    public ResponseResult addColor(@PathVariable String name){
        ResponseResult result = new ResponseResult();
        try {
            List<Color> colorList = attributeService.addColor(name);
            result.setData(colorList);
            result.setMeta(new Meta(200, "成功添加颜色"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500, "失败添加颜色, 产生意外"));
            return result;
        }
    }

    /**
     * 删除颜色并返回颜色列表
     * @param id
     * @return
     */
    @RequestMapping("/deleteColor/{id}")
    public ResponseResult deleteColor(@PathVariable Integer id){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>> id: "+id);
        try {
            List<Color> colorList = attributeService.deleteColor(id);
            result.setData(colorList);
            result.setMeta(new Meta(200, "成功删除颜色"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(500, "失败删除颜色, 产生意外"));
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

    /**
     * 添加尺码并返回尺码列表
     */
    @RequestMapping("/addSize/{name}")
    public ResponseResult addSize(@PathVariable String name){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>>> name: "+name);
        try {
            List<Size> sizeList = attributeService.addSize(name);
            result.setData(sizeList);
            result.setMeta(new Meta(200, "成功添加尺码"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(200, "失败添加尺码, 产生意外"));
            return result;
        }
    }

    /**
     * 删除尺码并返回尺码列表
     */
    @RequestMapping("/deleteSize/{id}")
    public ResponseResult deleteSize(@PathVariable Integer id){
        ResponseResult result = new ResponseResult();
        System.out.println(">>>>>>>>>>>> id: "+id);
        try {
            List<Size> sizeList = attributeService.deleteSize(id);
            result.setData(sizeList);
            result.setMeta(new Meta(200, "成功删除尺码"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMeta(new Meta(200, "失败删除尺码, 产生意外"));
            return result;
        }
    }
}
