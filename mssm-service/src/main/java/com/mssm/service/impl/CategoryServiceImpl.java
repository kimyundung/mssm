package com.mssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mssm.domain.ListPageInfo;
import com.mssm.domain.Category;
import com.mssm.domain.QueryInfo;
import com.mssm.mapper.CategoryMapper;
import com.mssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    // 查询商品分类
    @Override
    public List<Category> findAllCategory(QueryInfo queryInfo) {

        if(queryInfo.getLevel()==null){
            queryInfo.setLevel(3);
        }

        // 一级分类
        if(queryInfo.getLevel()==1){
            List<Category> categoryList = categoryMapper.findFirstCategory(-1);
            return categoryList;
        }

        // 二级分类
        if(queryInfo.getLevel()==2){
            List<Category> categoryList = categoryMapper.findSecondCategory(-1);
            return categoryList;
        }

        // 三级分类
        List<Category> categoryList = categoryMapper.findAllCategory(-1);
        return categoryList;
    }

    // 分页查询商品分类
    @Override
    public ListPageInfo findAllCategoryByPage(QueryInfo queryInfo) {

        if(queryInfo.getLevel()==null){
            queryInfo.setLevel(3);
        }


        // 一级分类
        if(queryInfo.getLevel()==1){
            PageHelper.startPage(queryInfo.getPagenum(),queryInfo.getPagesize());
            List<Category> categoryList = categoryMapper.findFirstCategory(-1);
            PageInfo<Category> categoryPageInfo = new PageInfo<>(categoryList);
            ListPageInfo listPageInfo = new ListPageInfo();
            listPageInfo.setTotal(categoryPageInfo.getTotal());
            listPageInfo.setPagenum(categoryPageInfo.getPageNum());
            listPageInfo.setPagesize(categoryPageInfo.getPageSize());
            listPageInfo.setList(categoryPageInfo.getList());
            return listPageInfo;
        }

        // 二级分类
        if (queryInfo.getLevel()==2){
            PageHelper.startPage(queryInfo.getPagenum(),queryInfo.getPagesize());
            List<Category> categoryList = categoryMapper.findSecondCategory(-1);
            PageInfo<Category> categoryPageInfo = new PageInfo<>(categoryList);
            ListPageInfo listPageInfo = new ListPageInfo();
            listPageInfo.setTotal(categoryPageInfo.getTotal());
            listPageInfo.setPagenum(categoryPageInfo.getPageNum());
            listPageInfo.setPagesize(categoryPageInfo.getPageSize());
            listPageInfo.setList(categoryPageInfo.getList());
            return listPageInfo;
        }

        // 三级分类
        PageHelper.startPage(queryInfo.getPagenum(),queryInfo.getPagesize());
        List<Category> categoryList = categoryMapper.findAllCategory(-1);
        PageInfo<Category> categoryPageInfo = new PageInfo<>(categoryList);
        ListPageInfo listPageInfo = new ListPageInfo();
        listPageInfo.setTotal(categoryPageInfo.getTotal());
        listPageInfo.setPagenum(categoryPageInfo.getPageNum());
        listPageInfo.setPagesize(categoryPageInfo.getPageSize());
        listPageInfo.setList(categoryPageInfo.getList());
        return listPageInfo;
    }

    // 验证分类名称
    @Override
    public Boolean checkCateName(String name) {
        Integer cnt = categoryMapper.findCategoryName(name);
        if(cnt==0){
            return true;
        }
        return false;
    }

    // 添加分类
    @Override
    public void saveCategory(Category category) {
        categoryMapper.insertCategory(category);
    }

    // 编辑分类
    @Override
    public void updateCategory(Category category) {
        category.setUpdatetime(new Date());
        categoryMapper.updateCategory(category);
    }

    // 逻辑删除分类
    @Override
    public void deleteCategory(Integer id) {
        Category category = new Category();
        category.setId(id);
        category.setDeletetime(new Date());
        deleteCate(category);
    }

    // 修改分类状态
    @Override
    public void updateCategoryStatus(Category category) {
        category.setUpdatetime(new Date());
        Integer oId = category.getId();
        // 从0到1时要确认父级分类+子级分类
        if(category.getStatus()==1){
            if(categoryMapper.findParCategoryPid(category.getId())>0){
                updatePerStatus(category);
            }
        }
        category.setId(oId);
        updateStatus(category);
    }

    // 递归修改关联父分类状态
    private void updatePerStatus(Category category){
        if(category.getId()>0){
            Integer pid = categoryMapper.findParCategoryPid(category.getId());
            category.setId(pid);
            categoryMapper.updateCategoryStatus(category);
            // 递归
            updatePerStatus(category);
        }
    }

    // 递归修改关联子分类状态
    private void updateStatus(Category category){
        categoryMapper.updateCategoryStatus(category);
        List<Integer> idList = categoryMapper.findSubCategoryIdList(category.getId());
        if(idList.size()>0){
            for (Integer id : idList) {
                category.setId(id);
                updateStatus(category);
            }
        }
    }

    // 递归删除关联分类
    private void deleteCate(Category category){
        categoryMapper.deleteCategory(category);
        List<Integer> idList = categoryMapper.findSubCategoryIdList(category.getId());
        if(idList.size()>0){
            for (Integer id : idList) {
                category.setId(id);
                deleteCate(category);
            }
        }

    }


}
