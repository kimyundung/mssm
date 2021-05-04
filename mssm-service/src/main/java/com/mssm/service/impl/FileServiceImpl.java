package com.mssm.service.impl;

import com.mssm.domain.File;
import com.mssm.domain.Goods;
import com.mssm.mapper.FileMapper;
import com.mssm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    /**
     * 循环编辑文件信息,并添加文件
     * @param goods
     */
    @Override
    public void addFileList(Goods goods) {
        goods.getFileList().addAll(goods.getFileList3());
        for(File file : goods.getFileList()){
            file.setGid(goods.getId());
            fileMapper.addFile(file);
        }
    }

    /**
     * 根据商品id添加图片
     * @param fileInfo
     */
    @Override
    public void addFile(File fileInfo) {
        fileMapper.addFile(fileInfo);
    }

    /**
     * 根据fileid删除file(物理)
     * @param fid
     */
    @Override
    public void deleteFile(Integer fid) {
        fileMapper.deleteByFId(fid);
    }


}
