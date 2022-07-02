package com.xl.oa.project.service.file;

import com.xl.oa.project.po.Files;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface IFileService{
    /**
     *
     * @描述 添加
     *
     * @date 2022/4/23 16:01
     */
    public int insertSelective(Files file);

    /**
     *
     * @描述 删除
     *
     * @date 2022/4/23 16:01
     */
    public int deleteByPrimaryKeys(String[] ids);

    /**
     *
     * @描述 列表
     *
     * @date 2022/4/23 16:01
     */
    List<Files> selectFileList(Files file);

    void downloadCountAddOne(Files files);
}
