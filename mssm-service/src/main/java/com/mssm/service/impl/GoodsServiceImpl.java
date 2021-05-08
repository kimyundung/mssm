package com.mssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mssm.domain.*;
import com.mssm.mapper.GoodsAttributeMapper;
import com.mssm.mapper.GoodsCategoryMapper;
import com.mssm.mapper.GoodsMapper;
import com.mssm.service.FileService;
import com.mssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private FileService fileService;
    @Autowired
    private GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

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
        //System.out.println("------------- fileList: "+goods.getFileList());
        //System.out.println("------------- fileList3: "+goods.getFileList3());
        // 添加商品
        if(goods.getId()==null){
            goodsMapper.addGoods(goods);
            fileService.addFileList(goods);
        }
        // 编辑商品
        else {
            goodsMapper.updateGoods(goods);
            fileService.updateFileList(goods);
        }
    }

    /**
     * 修改商品状态
     * 有商品分类和商品库存(属性)才可以上架
     * @param goods
     */
    @Override
    public boolean status(Goods goods) {
        // 为上架状态判断是否有商品分类,属性(库存)
        boolean result = false;
        if(goods.getStatus()==1){
            Integer cntA = goodsAttributeMapper.cntByGId(goods.getId());
            Integer cntC = goodsCategoryMapper.cntByGId(goods.getId());
            System.out.println("------------------------ cntA: " + cntA);
            System.out.println("------------------------ cntC: " + cntC);
            if(cntC>0 && cntA>0){
                goodsMapper.status(goods.getId(),goods.getStatus());
                result = true;
            }

        } else {
            goodsMapper.status(goods.getId(),goods.getStatus());
            result = true;
        }
        return result;
    }

    /**
     * 删除商品
     * - 先删除相关分类
     * - 再删除相关属性
     * - 在删除相关文件
     * @param id
     */
    @Override
    public void delete(Integer id) {
        //1 逻辑删除分类
        goodsCategoryMapper.deleteByGIdLogic(id);
        //2 逻辑删除属性
        goodsAttributeMapper.deleteByGIdLogic(id);
        //3 逻辑删除文件
        fileService.deleteByGIdLogic(id);
        //测试事务
        //int i=1/0;
        //4 逻辑删除商品
        goodsMapper.delete(id);
    }

    // 根据商品id查询商品+图片信息
    @Override
    public Goods queryById(Integer id) {
        // 商品信息
        Goods goods = goodsMapper.queryById(id);
        // 图片信息
        List<File> files = fileService.queryByGid(id);
        List<File> files3 = fileService.queryByGid3(id);
        // 商品+图片
        goods.setFileList(files);
        goods.setFileList3(files3);
        return goods;
    }
}
