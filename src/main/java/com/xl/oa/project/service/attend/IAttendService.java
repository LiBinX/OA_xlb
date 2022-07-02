package com.xl.oa.project.service.attend;

import com.xl.oa.project.po.Attend;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface IAttendService{
    int deleteByPrimaryKeys(Integer[] id);

    int insertSelective(Attend record) throws Exception;

    Attend selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Attend record);
    List<Attend> selectAttendList(Attend attend);

    Attend selectSaveDayIsAttend(String userId);

}
