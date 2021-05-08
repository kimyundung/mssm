package com.mssm.service;

import com.mssm.domain.Swiper;

import java.util.List;

public interface SwiperService {

    /**
     * 添加轮播图
     */
    public void add(Swiper swiper);

    /**
     * 查询所有轮播图
     */
    public List<Swiper> queryAll();

    /**
     * 根据id删除轮播图并返回轮播图集合
     */
    public void deleteById(Integer id);
}
