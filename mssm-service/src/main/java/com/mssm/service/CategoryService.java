package com.mssm.service;

import com.mssm.domain.ListPageInfo;
import com.mssm.domain.Category;
import com.mssm.domain.QueryInfo;

import java.util.List;

public interface CategoryService {
    // 查询所有商品分类
    public List<Category> findAllCategory(QueryInfo queryInfo);
    // 分页查询所有商品分类
    public ListPageInfo findAllCategoryByPage(QueryInfo queryInfo);
    // 验证分类名称
    public Boolean checkCateName(String name);
    // 添加分类
    public void saveCategory(Category category);
    // 编辑分类
    public void updateCategory(Category category);
    // 修改分类状态
    public void updateCategoryStatus(Category category);
    // 逻辑删除分类
    public void deleteCategory(Integer id);
}
