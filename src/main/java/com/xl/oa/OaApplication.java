package com.xl.oa;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author 毕业设计
 * @描述 启动类
 *
 * @date 2018/9/23 10:53
 */
@SpringBootApplication
@MapperScan("com.xl.oa.project.mapper")
//开启定时任务
@EnableScheduling
//开启缓存
@EnableCaching
public class OaApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(OaApplication.class, args);
    }

    private static void initLeaveProcess(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = new OaApplication().getClass().getClassLoader().getResourceAsStream("processes/leave.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = defaultProcessEngine.getRepositoryService()
                //部署对象
                .createDeployment()
                //部署名称
                .name("请假申请流程")
                //加载文件，一次只能加载一个 可以加载zip
                .addZipInputStream(zipInputStream)
                .deploy();
    }

    private static void initApplyProcess(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = new OaApplication().getClass().getClassLoader().getResourceAsStream("processes/apply.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = defaultProcessEngine.getRepositoryService()
                //部署对象
                .createDeployment()
                //部署名称
                .name("会议室申请流程")
                //加载文件，一次只能加载一个 可以加载zip
                .addZipInputStream(zipInputStream)
                .deploy();
    }
}
