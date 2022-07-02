package com.xl.oa.project.service.operlog;

import com.xl.oa.project.po.OperLog;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface IOperLogService{
    int deleteByPrimaryKeys(Integer[] id);

    int insertSelective(OperLog record);

    OperLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperLog record);

    List<OperLog> selectOperLogList(OperLog operLog);

}
