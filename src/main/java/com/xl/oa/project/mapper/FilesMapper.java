package com.xl.oa.project.mapper;


import com.xl.oa.project.po.Files;

import java.util.List;

public interface FilesMapper{
    /**
     * 批量删除
     * @param fileId
     * @return
     */
    int deleteByPrimaryKeys(String[] fileId);


    /**
     * 添加
     * @param record
     * @return
     */
    int insertSelective(Files record);

    /**
     * 主键查询
     * @param fileId
     * @return
     */
    Files selectByPrimaryKey(Integer fileId);

    /**
     * 修改下载数量
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Files record);



    /**
     *
     * @描述 列表
     *
     * @date 2022/4/28 12:07
     */
    List<Files> selectFileList(Files file);
}