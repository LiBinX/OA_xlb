package com.xl.oa.common.exception.file;

import org.apache.commons.fileupload.FileUploadException;

import java.io.IOException;

/**
 * @author 毕业设计
 * 文件上传异常
 */
public class MyFileUploadException extends IOException{

    private String msg;

    public MyFileUploadException(String msg)
    {
        super(msg);
        this.msg=msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
