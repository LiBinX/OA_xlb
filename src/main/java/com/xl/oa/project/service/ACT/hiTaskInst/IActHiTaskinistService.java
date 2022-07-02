package com.xl.oa.project.service.ACT.hiTaskInst;

import com.xl.oa.project.po.ActHiTaskInst;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface IActHiTaskinistService{
    /**
     *
     * @描述:  删除批量
     *
     * @params:
     * @return:
     * @date: 2022/4/23 16:01
     */
    int deleteByPrimaryKeys(String[] ids) throws Exception;

    /**
     *
     * @描述: 主键查询
     *
     * @params:
     * @return:
     * @date: 2022/4/23 16:01
     */
    ActHiTaskInst selectByPrimaryKey(String id);

    /**
     *
     * @描述: 根据审批人查询历史审批记录
     *
     * @params:
     * @return:
     * @date: 2022/4/23 16:01
     */
    List<ActHiTaskInst> selectActHiTaskInstList(ActHiTaskInst actHiTaskInst);

}
