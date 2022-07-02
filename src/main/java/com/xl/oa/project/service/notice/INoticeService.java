package com.xl.oa.project.service.notice;

import com.xl.oa.project.po.Notice;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface INoticeService{
    /**
     *
     * @描述 批量删除
     *
     * @date 2022/4/23 16:01
     */
    int deleteByPrimaryKeys(Integer[] positionId) throws Exception;

    /**
     *
     * @描述插入
     *
     * @date 2022/4/23 16:01
     */
    int insertSelective(Notice record);

    /**
     *
     * @描述 根据id查询
     *
     * @date 2022/4/23 16:01
     */
    Notice selectByPrimaryKey(Integer id);

    /**
     *
     * @描述 修改
     *
     * @date 2022/4/23 16:01
     */
    int updateByPrimaryKeySelective(Notice record);

    List<Notice> selectNoticeList(Notice record);

}
