package com.mssm.service.impl;

import com.mssm.domain.Swiper;
import com.mssm.mapper.SwiperMapper;
import com.mssm.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperServiceImpl implements SwiperService {

    @Autowired
    private SwiperMapper swiperMapper;

    /**
     * 添加轮播图
     */
    @Override
    public void add(Swiper swiper) {
        swiperMapper.add(swiper);
    }

    /**
     * 查询所有轮播图
     */
    @Override
    public List<Swiper> queryAll() {
        return swiperMapper.queryAll();
    }

    /**
     * 根据id删除轮播图并返回轮播图集合
     */
    @Override
    public void deleteById(Integer id) {
        swiperMapper.deleteById(id);
    }

}
