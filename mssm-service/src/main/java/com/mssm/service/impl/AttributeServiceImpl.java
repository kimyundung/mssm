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

    @Override
    public List<Size> findAllSize() {
        return attributeMapper.findAllSize();
    }
}
