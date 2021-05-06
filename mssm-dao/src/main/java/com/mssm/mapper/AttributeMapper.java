package com.mssm.mapper;

import com.mssm.domain.Color;
import com.mssm.domain.Size;

import java.util.List;

public interface AttributeMapper {

    /**
     * 查询所有颜色信息
     *
     * @return
     */
    public List<Color> findAllColor();

    /**
     * 添加颜色并返回颜色列表
     * @param name
     * @return
     */
    public void addColor(String name);

    /**
     * 删除颜色并返回颜色列表
     * @param id
     * @return
     */
    public void deleteColor(Integer id);

    /**
     * 查询所有尺码信息
     *
     * @return
     */
    public List<Size> findAllSize();

    /**
     * 添加尺码并返回尺码列表
     */
    public void addSize(String name);

    /**
     * 删除尺码并返回尺码列表
     */
    public void deleteSize(Integer id);
}
