package com.xl.oa.project.controller.act;

import com.xl.oa.project.service.ACT.task.IActTaskService;
import com.xl.oa.project.service.leavForm.ILeavFormService;
import com.xl.oa.project.service.user.IUserService;
import com.xl.oa.framework.annotation.Operlog;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * @author 毕业设计
 */
@Controller
@RequestMapping("/task")
public class ActCommonController {

    private static final String prefix = "system/processImg";

    @Autowired
    IActTaskService iact_taskService;

    @Autowired
    RepositoryService rep;


    @Autowired
    HistoryService histiryservice;

    @Autowired
    ILeavFormService iLeavFormService;
    @Autowired
    IUserService iUserService;

    @Autowired
    RuntimeService runservice;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    HistoryService historyService;

    @Autowired
    TaskService taskService;


//    @RequestMapping("/flowChart/{procInstId}")
//    @Operlog(modal = "流程图", descr = "查看流程图")
//    public String img(@PathVariable("procInstId") String procInstId, Model model)
//    {
////流程定义Id
//        String deploymentId = "";
//
//        //通过实例id 获取任务
//        Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
//        if (task != null)
//        {
//            //通过任务Id获取当前实例任务的信息
//            Map<String, Object> currentView = iact_taskService.getCurrentView(task.getId());
//
//            //通过任务id获取流程定义
//            ProcessDefinition processDefinition = (ProcessDefinition) currentView.get("processDefinition");
//
//            //通过流程定义获取部署ID 通过部署ID获取图片
//            deploymentId = processDefinition.getDeploymentId();
//        }
//        else
//        {
//            //获取历史流程实例
//            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(
//                    procInstId).singleResult();
//            deploymentId = historicProcessInstance.getDeploymentId();
//        }
//
//
//        //资源流程文件的名称 (.bpmn  .png )  资源
//        List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
//
//        //获取图片名称
//        String pngName = "";
//        String pngNameSource = "";
//        for (String s : list)
//        {
//            if (s.indexOf(".png") >= 0)
//            {
//                pngNameSource  = s;
//                String pngZeroSubName = s.substring(0,s.indexOf(".")+1);
//                String pngLengthSubName = s.substring(s.lastIndexOf(".")+1,s.length());
//                pngName = pngZeroSubName+pngLengthSubName;
//            }
//        }
//
//        //拿到图片的输入流
//        InputStream in = repositoryService.getResourceAsStream(deploymentId, pngNameSource);
////        boolean hashFile = false;
//        boolean hashFile = true;
//
//        //判断服务器上是否有图片
//        try
//        {
////            hashFile = FtpUtil.isHashFile(pngName);
//            if (hashFile)
//            {
//                model.addAttribute("img", FtpUtil.filePath + "/" + pngName);
//                return prefix + "/img";
//            }
//            //没有有就上传
//            Map<String, InputStream> map = new HashMap<>();
//            map.put(pngName, in);
//            if (FtpUtil.uploadFile(map))
//            {
//                model.addAttribute("img", FtpUtil.filePath + "/" + pngName);
//                return prefix + "/img";
//            }
//
//        }
//        catch (IOException e)
//        {
//            return prefix + "/img";
//        }
//        return prefix + "/img";
//    }


    /**
     * @描述: 查看流程图
     * 如果实例结束就查看历史的
     * @params:
     * @return：
     * @date： 2022/4/23 13:47
     */
    @RequestMapping("/flowChart/{procInstId}")
    @Operlog(modal = "流程图", descr = "查看流程图")
    public void getFlowChart(@PathVariable("procInstId") String procInstId,
                             HttpServletResponse response) throws Exception {

//        //设置相应类型,告诉浏览器输出的内容为图片
//        response.setContentType("image/png");
//        //设置响应头信息，告诉浏览器不要缓存此内容
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expire", 0);
//
//
//        //流程定义Id
//        String deploymentId = "";
//
//        //通过实例id 获取任务
//        Task task = taskService.createTaskQuery().processInstanceId(procInstId).singleResult();
//        if (task != null)
//        {
//            //通过任务Id获取当前实例任务的信息
//            Map<String, Object> currentView = iact_taskService.getCurrentView(task.getId());
//
//            //通过任务id获取流程定义
//            ProcessDefinition processDefinition = (ProcessDefinition) currentView.get("processDefinition");
//
//            //通过流程定义获取部署ID 通过部署ID获取图片
//            deploymentId = processDefinition.getDeploymentId();
//        }
//        else
//        {
//            //获取历史流程实例
//            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(
//                    procInstId).singleResult();
//            deploymentId = historicProcessInstance.getDeploymentId();
//        }
//
//
//        //资源流程文件的名称 (.bpmn  .png )  资源
//        List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
//
//        //获取图片名称
//        String pngName = "";
//        for (String s : list)
//        {
//            if (s.indexOf(".png") >= 0)
//            {
//                pngName = s;
//            }
//            System.out.println(s);
//        }
//
//        //拿到图片的输入流
//        InputStream in = repositoryService.getResourceAsStream(deploymentId, pngName);
//
//
//        //将其输出页面
//        int len = 0;
//        byte[] buffer = new byte[1024];
//        OutputStream outputStream = response.getOutputStream();
//        try
//        {
//            while ((len = in.read(buffer)) != -1)
//            {
//                outputStream.write(buffer, 0, len);
//            }
//        }
//        catch (IOException e)
//        {
//            throw new Exception("查看流程图失败！");
//        }
//        in.close();
//        outputStream.close();

        ProcessInstance process = runservice.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        String processDefinitionId = StringUtils.EMPTY;
        if (process == null) {
            //查询已经结束的流程实例
            HistoricProcessInstance processInstanceHistory =
                    historyService.createHistoricProcessInstanceQuery()
                            .processInstanceId(procInstId).singleResult();

            if (processInstanceHistory == null) {
            } else {
                processDefinitionId = processInstanceHistory.getProcessDefinitionId();
            }

        } else {
            processDefinitionId = process.getProcessDefinitionId();
        }
        BpmnModel bpmnmodel = rep.getBpmnModel(processDefinitionId);
        if (process!=null){
        List<String> activeActivityIds = runservice.getActiveActivityIds(procInstId);
        DefaultProcessDiagramGenerator gen = new DefaultProcessDiagramGenerator();
        // 获得历史活动记录实体（通过启动时间正序排序，不然有的线可以绘制不出来）
        List<HistoricActivityInstance> historicActivityInstances = histiryservice.createHistoricActivityInstanceQuery()
                .executionId(procInstId).orderByHistoricActivityInstanceStartTime().asc().list();
        // 计算活动线
        List<String> highLightedFlows = getHighLightedFlows(
                (ProcessDefinitionEntity) ((RepositoryServiceImpl) rep)
                        .getDeployedProcessDefinition(process.getProcessDefinitionId()),
                historicActivityInstances);

        InputStream in = gen.generateDiagram(bpmnmodel, "png", activeActivityIds, highLightedFlows, "宋体", "宋体", null,
                null, 1.0);
//             InputStream in=gen.generateDiagram(bpmnmodel, "png",
//             activeActivityIds);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(in, output);
        in.close();
        output.close();
        }

    }

    public List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size(); i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得 到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            if ((i + 1) >= historicActivityInstances.size()) {
                break;
            }
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());// 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {// 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {// 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {// 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

}
