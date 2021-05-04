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
     * 查询所有尺码信息
     * @return
     */
    public List<Size> findAllSize();

}
