package com.mssm.mapper;

import com.mssm.domain.File;

import java.util.List;

public interface FileMapper {
    /**
     * 添加文件
     * @param file 文件信息
     */
    public void addFile(File file);

    /**
     * 根据商品id查询图片信息
     * @param gid
     * @return
     */
    public List<File> queryByGid(Integer gid);

    /**
     * 根据商品id查询图片信息
     * @param gid
     * @return
     */
    public List<File> queryByGid3(Integer gid);

    /**
     * 根据商品gid删除图片s
     * @param gid
     */
    public void deleteByGId(Integer gid);

    /**
     * 根据fileid删除file(物理)
     * @param fid
     */
    public void deleteByFId(Integer fid);
}
