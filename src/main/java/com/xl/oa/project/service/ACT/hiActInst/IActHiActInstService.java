package com.xl.oa.project.service.ACT.hiActInst;

import com.xl.oa.project.po.ActHiActinst;

import java.util.List;

/**
 * @author 毕业设计
 */
public interface IActHiActInstService{

    /**
     *
     * @描述: 删除活动历史信息
     *
     * @params:
     * @return:
     * @date: 2018/9/23 20:45
     */
    int deleteByProcInstId(String[] procInstId);

    /**
     *
     * @描述: 根据进程实例id获取当前 实例的活动信息
     *
     * @params:
     * @return:
     * @date: 2018/9/23 20:45
     */
    List<ActHiActinst> selectByByProcInstId(String procInstId);
}
