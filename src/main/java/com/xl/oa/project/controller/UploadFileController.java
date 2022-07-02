package com.xl.oa.project.controller;

import com.xl.oa.project.po.Files;
import com.xl.oa.project.service.file.IFileService;
import com.xl.oa.framework.web.po.AjaxResult;
import com.xl.oa.project.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.xl.oa.common.utils.file.UploadFile.getFileTimeId;
import static com.xl.oa.common.utils.shiro.ShiroUtils.getUser;
import static com.xl.oa.framework.web.po.AjaxResult.error;
import static com.xl.oa.framework.web.po.AjaxResult.success;
import static com.xl.oa.project.controller.FileController.getFileBean;

@Controller
@RequestMapping("/uploadFile")
public class UploadFileController {

    private static final Logger log = LoggerFactory.getLogger(UploadFileController.class);

    @Value("${ylrc.upload.file.path}")
    private String uploadPath;//文件保存位置

    @Autowired
    private IFileService iFileService;

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile file, Files fileBean) {


        // 获取文件名
        String fileName = file.getOriginalFilename();
        if (!fileName.isEmpty()) {
            String fileId = getFileTimeId();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            fileId  += suffixName;
            // 设置文件存储路径
            File filePath = new File(uploadPath);
            if (!filePath.exists()) {
                //若不存在文件夹，则创建一个文件夹
                filePath.mkdir();
            }
            filePath = new File(uploadPath + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
            /*String path = filePath +"/"+ fileName;
            File dest = new File(path);*/
            // 检测是否存在目录
            if (!filePath.exists()) {
                filePath.mkdir();// 新建文件夹
            }
            String filename = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffixName;
            try {
                file.transferTo(new File(uploadPath+"/"+filename));// 文件写入
            } catch (IOException e) {
                e.printStackTrace();
            }
            iFileService.insertSelective(getFileBean(file, filename, fileBean,getUser().getName()));
            return success();

        }
        return error("上传失败！请再试试！");
    }

}
