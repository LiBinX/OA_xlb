package com.xl.oa.project.mapper;

import com.xl.oa.project.po.ScheduleUser;

import java.util.List;

public interface ScheduleUserMapper {
    int deleteByPrimaryKeys(Integer[] id);


    int insertSelective(List<ScheduleUser> userList);

}