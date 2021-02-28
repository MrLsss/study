package com.study.quartz.service;

import com.alibaba.fastjson.JSONObject;
import com.study.quartz.config.JobEntity;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年02月28日 15:58
 */
public interface QuartzService {

    /**
     * 创建job
     */
    boolean addJob(JobEntity job);

    /**
     * 执行job
     */
    boolean runJob(JobEntity job);

    /**
     * 修改job
     */
    boolean updateJob(JobEntity job);

    /**
     * 暂停job
     */
    boolean pauseJob(JobEntity job);

    /**
     * 唤醒job
     */
    boolean resumeJob(JobEntity job);

    /**
     * 删除job
     */
    boolean deleteJob(JobEntity job);

    /**
     * 获取job
     */
    JSONObject queryJob(JobEntity job);

}
