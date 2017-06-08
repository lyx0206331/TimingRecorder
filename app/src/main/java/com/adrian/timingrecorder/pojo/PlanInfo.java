package com.adrian.timingrecorder.pojo;

/**
 * Created by ranqing on 2017/5/12.
 */

public class PlanInfo {
    private int id;
    private String name;    //计划名称
    private long createTime;    //创建时间
    private long planStartTime;  //计划开始时间
    private long planStopTime;  //计划结束时间
    private int status; //执行状态。0表示未执行，1表示已执行
    private String description; //计划描述

    public PlanInfo() {
    }

    public PlanInfo(int id, String name, long createTime, long planStartTime, long planStopTime, int status, String description) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.planStartTime = planStartTime;
        this.planStopTime = planStopTime;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(long planStartTime) {
        this.planStartTime = planStartTime;
    }

    public long getPlanStopTime() {
        return planStopTime;
    }

    public void setPlanStopTime(long planStopTime) {
        this.planStopTime = planStopTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
