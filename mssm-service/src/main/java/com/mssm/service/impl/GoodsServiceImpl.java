package com.mssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mssm.domain.*;
import com.mssm.mapper.FileMapper;
import com.mssm.mapper.GoodsAttributeMapper;
import com.mssm.mapper.GoodsCategoryMapper;
import com.mssm.mapper.GoodsMapper;
import com.mssm.service.FileService;
import com.mssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    private GoodsAttributeMapper goodsAttributeMapper;

    // 条件+分页查询商品
    @Override
    public PageInfo<Goods> queryConditionPage(QueryVO queryVO) {
        PageHelper.startPage(queryVO.getPagenum(),queryVO.getPagesize());
        List<Goods> goodsList = goodsMapper.query(queryVO.getQuery(), queryVO.getStatus());
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
        return goodsPageInfo;
    }

    @Override
    public List<Goods> queryCondition(QueryVO queryVO) {
        List<Goods> goodsList = goodsMapper.query(queryVO.getQuery(), queryVO.getStatus());
        return goodsList;
    }

    // 查询所有商品
    @Override
    public List<Goods> queryAll() {
        return goodsMapper.queryAll();
    }

    // 添加商品
    @Override
    public void addGoods(Goods goods) {
        // 添加商品
        if(goods.getId()==null){
            goodsMapper.addGoods(goods);
            fileService.addFileList(goods);
        }
        // 编辑商品
        else {
            goodsMapper.updateGoods(goods);
        }
    }

    // 修改商品状态
    @Override
    public void status(Goods goods) {
        goodsMapper.status(goods.getId(),goods.getStatus());
    }

    /**
     * 删除商品
     * - 先删除相关分类
     * - 再删除相关属性
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //1 删除分类
        goodsCategoryMapper.deleteGoodsCategoryByGId(id);
        //2 删除属性
        goodsAttributeMapper.deleteGoodsAttributeByGId(id);
        //3 删除商品
        goodsMapper.delete(id);
    }

    // 根据商品id查询商品+图片信息
    @Override
    public Goods queryById(Integer id) {
        // 商品信息
        Goods goods = goodsMapper.queryById(id);
        // 图片信息
        List<File> files = fileMapper.queryByGid(id);
        List<File> files3 = fileMapper.queryByGid3(id);
        // 商品+图片
        goods.setFileList(files);
        goods.setFileList3(files3);
        return goods;
    }
}
