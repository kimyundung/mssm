package com.mssm.service;

import com.mssm.domain.File;
import com.mssm.domain.Goods;

import java.util.List;

public interface FileService {
    /**
     * 添加图片集合
     * @param goods 图片集合
     */
    public void addFileList(Goods goods);

    /**
     * 根据商品id添加图片
     * @param fileInfo
     */
    public void addFile(File fileInfo);

    /**
     * 根据fileid删除file(物理)
     * @param fid
     */
    public void deleteFile(Integer fid);
}
