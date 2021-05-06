package com.mssm.service.impl;

import com.mssm.domain.Color;
import com.mssm.domain.Size;
import com.mssm.mapper.AttributeMapper;
import com.mssm.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public List<Color> findAllColor() {
        return attributeMapper.findAllColor();
    }

    /**
     * 查询所有尺码信息
     * @param name
     * @return
     */
    @Override
    public List<Color> addColor(String name) {
        attributeMapper.addColor(name);
        List<Color> colorList = attributeMapper.findAllColor();
        return colorList;
    }

    /**
     * 删除颜色并返回颜色列表
     * @param id
     * @return
     */
    @Override
    public List<Color> deleteColor(Integer id) {
        attributeMapper.deleteColor(id);
        List<Color> colorList = attributeMapper.findAllColor();
        return colorList;
    }

    @Override
    public List<Size> findAllSize() {
        return attributeMapper.findAllSize();
    }

    /**
     * 添加尺码并返回尺码列表
     * @param name
     * @return
     */
    @Override
    public List<Size> addSize(String name) {
        attributeMapper.addSize(name);
        List<Size> sizeList = attributeMapper.findAllSize();
        return sizeList;
    }

    /**
     * 删除尺码并返回尺码列表
     * @param id
     * @return
     */
    @Override
    public List<Size> deleteSize(Integer id) {
        attributeMapper.deleteSize(id);
        List<Size> sizeList = attributeMapper.findAllSize();
        return sizeList;
    }


}
