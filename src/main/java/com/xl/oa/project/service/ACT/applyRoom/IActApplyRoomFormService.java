package com.xl.oa.project.service.ACT.applyRoom;

import com.xl.oa.project.po.ApplyRoomForm;

/**
 * @author 毕业设计
 * 预约申请会议室 方法接口
 */
public interface IActApplyRoomFormService{

    /**
    * @ 描述 提交申请
    * @date 2022/4/21 16:09
    */
    public void apply(ApplyRoomForm applyRoom);



    /**
     *
     * @描述:  删除
     *
     * @params:
     * @return:
     * @date: 2022/4/26 15:26
    */
    int deleteByPrimaryKeys(String[] ids)throws Exception;

    /**
     *主键查询
     *
     * @mbggenerated
     */
    ApplyRoomForm selectByPrimaryKey(String id);

    /**
     * 修改状态
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ApplyRoomForm record);

}
