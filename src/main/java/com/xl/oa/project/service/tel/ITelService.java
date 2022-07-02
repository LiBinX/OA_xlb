package com.xl.oa.project.service.tel;

import com.xl.oa.project.po.Tel;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface ITelService{
    int deleteByPrimaryKeys(Integer[] id);

    int insertSelective(Tel record);

    Tel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tel record);

    List<Tel> selectTelList(Tel tel);
}
