package com.xl.oa.project.controller;

import com.xl.oa.project.po.AttendCount;
import com.xl.oa.project.service.attendCount.IAttendCountService;
import com.xl.oa.project.service.workTime.IWorkTimeService;
import com.xl.oa.framework.web.controller.BaseController;
import com.xl.oa.framework.web.page.TableDataInfo;
import com.xl.oa.framework.web.po.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 毕业设计
 */
@Controller
@RequestMapping("/attendCount")
public class AttendCountController extends BaseController{
    private String prefix = "system/attendCount/";
    @Autowired
    IAttendCountService iAttendCountService;

    @Autowired
    IWorkTimeService iWorkTimeService;



    /**
     *
     * @描述: 跳转
     *
     * @params:
     * @return:
     * @date: 2022/4/25 18:12
     */

    @RequestMapping("/tolist")
    public String toList(Model model)
    {
        return prefix + "attendCount";
    }


    /**
     *
     * @描述 表格列表
     *
     * @date 2022/4/25 10:52
     */
    @RequestMapping("/tableList")
    @ResponseBody
    public TableDataInfo listPag(AttendCount attendCount)
    {
        startPage();
        List<AttendCount> attendCounts = iAttendCountService.selectAttendCountList(attendCount);
        return getDataTable(attendCounts);
    }


    /**
     *
     * @描述 批量删除
     *
     * @date 2022/4/25 11:53
     */
    @RequestMapping("/del")
    @RequiresPermissions("attendCount:del")
    @ResponseBody
    public AjaxResult del(Integer[] ids)
    {
        try
        {
            iAttendCountService.deleteByPrimaryKeys(ids);
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
        return success();
    }



}
