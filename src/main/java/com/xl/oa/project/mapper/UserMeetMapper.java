package com.xl.oa.project.mapper;

import com.xl.oa.project.po.UserMeet;

import java.util.List;

public interface UserMeetMapper {

    int deleteByMeetIdKeys(Integer[] id);


    int insertSelective(List<UserMeet> lsit);



}