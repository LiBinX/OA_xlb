package com.xl.oa.project.service.attendCount;

import com.xl.oa.project.po.AttendCount;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface IAttendCountService{
    int deleteByPrimaryKeys(Integer[] id);

    void insertSelective();

    AttendCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AttendCount record);

    List<AttendCount> selectAttendCountList(AttendCount attend);

}
