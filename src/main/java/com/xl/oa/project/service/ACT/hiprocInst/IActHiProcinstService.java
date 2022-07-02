package com.xl.oa.project.service.ACT.hiprocInst;

import com.xl.oa.project.po.ActHiProcinst;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface IActHiProcinstService{
    /**
     * @ 描述 批量删除
     * @ param id
     * @date 2022/4/23 11:59
     */
    int deleteByPrimaryKeys(String[] id);

    /**
     * @ 描述 根据主键查询
     * @date 2022/4/23 12:01
     */
    ActHiProcinst selectByPrimaryKey(String id);

    /**
     *
     * @描述: 根据实例id查询 判断改实例是否已经结束
     *
     * @params:
     * @return：
     * @date： 2022/4/23 15:18
     */
    ActHiProcinst selectByProcInstId(String procInstId);


    /**
     * @ 描述 列表
     * @date 2022/4/23 12:02
     */
    List<ActHiProcinst> selectActHiProcinstList(ActHiProcinst actHiProcinst);
}
