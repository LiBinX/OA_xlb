package com.xl.oa.project.po;

import com.xl.oa.framework.web.po.BasePo;

import java.util.Date;
/**
 *
 * @描述: 便签实体
 *
 * @date: 2022/4/25 14:17
 */
public class Note extends BasePo{
    private Integer id;

    private String title;

    private String content;

    private String createBy;

    private Date createTime;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}