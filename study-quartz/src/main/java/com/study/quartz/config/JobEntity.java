package com.study.quartz.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description: 定时任务使用的动态参数实体类
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年02月28日 15:43
 */
@Data
public class JobEntity {

    /**
     * 任务唯一ID
     */
    private String jobId;

    /**
     * 定时任务示例的Class路径
     */
    private String className;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 定时任务名称
     */
    private String jobName;

    /**
     * 定时任务所属组
     */
    private String jobGroup;

    /**
     * 触发器名称
     */
    private String triggerName;

    /**
     * 触发器组
     */
    private String triggerGroup;

    /**
     * 备注
     */
    private String description;

    /**
     * 携带参数
     */
    private JSONObject data;

    /**
     * 预留的数据库字段 如果任务信息选择手动自己存入数据库的话,会使用到
     */
    private Boolean pauseStatus;  //是否暂停
    private Boolean deleteStatus; //是否有效
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    @Override
    public String toString() {
        return "JobEntity{" +
                "jobId='" + jobId + '\'' +
                ", className='" + className + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", triggerName='" + triggerName + '\'' +
                ", triggerGroup='" + triggerGroup + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                ", pauseStatus=" + pauseStatus +
                ", deleteStatus=" + deleteStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
