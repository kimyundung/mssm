package com.mssm.service;

import com.mssm.domain.Color;
import com.mssm.domain.Size;

import java.util.List;

public interface AttributeService {
    /**
     * 查询所有颜色
     * @return
     */
    public List<Color> findAllColor();

    /**
     * 添加颜色并返回颜色列表
     * @param name
     * @return
     */
    public List<Color> addColor(String name);

    /**
     * 删除颜色并返回颜色列表
     * @param id
     * @return
     */
    public List<Color> deleteColor(Integer id);

    /**
     * 查询所有尺码信息
     * @return
     */
    public List<Size> findAllSize();

    /**
     * 添加尺码并返回尺码列表
     */
    public List<Size> addSize(String name);

    /**
     * 删除尺码并返回尺码列表
     */
    public List<Size> deleteSize(Integer id);
}
