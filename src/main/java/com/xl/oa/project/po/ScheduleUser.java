package com.xl.oa.project.po;
/**
 *
 * @描述: 会议发起实体
 *
 * @date: 2022/4/25 14:17
 */
public class ScheduleUser {
    private Integer id;

    private Integer sId;

    private String suId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getSuId() {
        return suId;
    }

    public void setSuId(String suId) {
        this.suId = suId == null ? null : suId.trim();
    }
}