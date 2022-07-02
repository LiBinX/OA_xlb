package com.xl.oa.project.service.ACT.task;


import com.xl.oa.project.po.ActTask;
import com.xl.oa.project.po.ApplyRoomForm;
import com.xl.oa.project.po.LeaveForm;

import java.util.List;
import java.util.Map;

/**
 * @author 毕业设计
 */
public interface IActTaskService{
    /**
     *
     * @描述 任务列表
     *
     * @date 2022/4/23 16:01
     */
    List<ActTask> selectACTTaskList(ActTask record);

    /**
     *
     * @描述: 请假审批
     *
     * @params:
     * @return:
     * @date: 2022/4/23 16:01
    */

    public void LeaveApproval(LeaveForm leaveForm, String taskId);

    public void RoomApproval(ApplyRoomForm applyRoomForm, String taskId) throws Exception;
    /**
     *
     * @描述:  删除任务
     *
     * @params:
     * @return:
     * @date: 2022/4/23 16:01
    */
    public int deletByProcInstS(String[] ids);


    /**
     *
     * @描述 通过任务Id 获取当前节点的所有信息
     *
     * @date 2022/4/23 16:01
     */
    Map<String,Object> getCurrentView(String taskId);
}
