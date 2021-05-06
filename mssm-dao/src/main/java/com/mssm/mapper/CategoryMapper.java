package com.mssm.mapper;

import com.mssm.domain.Category;

import java.util.List;

public interface CategoryMapper {
    // 查询一级+二级+三级
    public List<Category> findAllCategory(Integer id);

    // 查询一级+二级
    public List<Category> findSecondCategory(Integer id);

    // 查询三级分类
    public List<Category> findThirdCategoryByPid(Integer id);

    // 查询一级分类
    public List<Category> findFirstCategory(Integer id);

    // 查询分类名称
    public Integer findCategoryName(String name);

    // 添加分类
    public void insertCategory(Category category);

    // 编辑分类
    public void updateCategory(Category category);

    // 修改分类状态
    public void updateCategoryStatus(Category category);

    // 根据分类id查询所有子分类id
    public List<Integer> findSubCategoryIdList(Integer id);

    // 根据分类id查询所有父分类id
    public Integer findParCategoryPid(Integer id);

    // 逻辑删除分类
    public void deleteCategory(Category category);
}
