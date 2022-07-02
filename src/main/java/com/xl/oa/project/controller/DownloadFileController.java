package com.xl.oa.project.controller;

import com.xl.oa.project.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/download")
public class DownloadFileController {

    @Value("${ylrc.upload.file.path}")
    private String filePath;//文件保存位置

    @GetMapping("/file")
    public void downloadFile(String fileSrc, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName=filePath+fileSrc;
        String s = fileName.substring(fileName.lastIndexOf("/") + 1);//文件名
        if (fileName != null) { //设置文件路径
            File file = new File(fileName); // 如果文件名存在，则进行下载
            if (file.exists()) { // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream"); // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, s)); // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i); i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    System.out.println("Download the song failed!");
                } finally {
                    if (bis != null) {
                        try { bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
