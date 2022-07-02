package com.xl.oa.project.service.schedule;

import com.xl.oa.project.po.Schedule;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface IScheduleService{
    int deleteByPrimaryKeys(Integer[] id);

    int insertSelective(Schedule record, String[] userIds);

    Schedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Schedule record,String[] userIds);

    List<Schedule> selectScheduleList(Schedule schedule);

    int updateComplete(Schedule schedule);
}
