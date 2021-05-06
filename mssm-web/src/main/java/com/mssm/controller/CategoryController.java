package com.mssm.controller;

import com.mssm.domain.*;
import com.mssm.service.CategoryService;
import com.mssm.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cate")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 查询商品分类
    @RequestMapping("/findCate")
    public ResponseResult findCate(QueryInfo queryInfo, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            // 不分类
            if(queryInfo.getPagenum()==null && queryInfo.getPagesize()==null){
                List<Category> categoryList = categoryService.findAllCategory(queryInfo);
                result.setData(categoryList);
                result.setMeta(new Meta(200,"获取商品分类成功"));
            }
            // 分页
            else {
                ListPageInfo cateListPageInfo = categoryService.findAllCategoryByPage(queryInfo);
                result.setData(cateListPageInfo);
                result.setMeta(new Meta(200,"分页获取商品分类成功"));
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"获取商品分类失败, 发生意外"));
            return result;
        }
    }

    // 验证分类名称
    @RequestMapping("/checkCateName")
    public ResponseResult checkCateName(String name, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            if(categoryService.checkCateName(name)){
                result.setMeta(new Meta(200,"可用分类名称"));
            } else {
                result.setMeta(new Meta(502,"该分类名称以被使用"));
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"校验商品分类名称失败, 发生意外"));
            return result;
        }

    }

    // 添加&编辑商品分类
    @RequestMapping("/saveCate")
    public ResponseResult saveCate(@RequestBody Category category, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            // 添加分类
            if(category.getId()==null){
                categoryService.saveCategory(category);
                result.setMeta(new Meta(201,"添加商品分类成功"));
            }
            // 编辑分类
            else {
                categoryService.updateCategory(category);
                result.setMeta(new Meta(201,"编辑商品分类成功"));
            }
            return result;
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"保存商品分类失败, 发生意外"));
            return  result;
        }

    }

    // 修改分类状态
    @RequestMapping("/cateStatus")
    public ResponseResult cateStatus(Category category, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            categoryService.updateCategoryStatus(category);
            result.setMeta(new Meta(201,"修改分类状态成功"));
            return result;
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"修改分类状态失败, 产生意外"));
            return result;
        }

    }

    // 删除分类
    @RequestMapping("/deleteCate")
    public ResponseResult deleteCate(Integer id, HttpServletRequest request){
        ResponseResult result = new ResponseResult();

        try{
            categoryService.deleteCategory(id);
            result.setMeta(new Meta(202,"删除商品分类成功"));
            return result;
        } catch (Exception e){
            e.printStackTrace();
            result.setMeta(new Meta(500,"删除商品分类失败, 产生意外"));
            return result;
        }

    }

}
