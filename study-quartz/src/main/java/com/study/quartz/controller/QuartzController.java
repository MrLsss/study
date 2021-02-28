package com.study.quartz.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.quartz.config.JobEntity;
import com.study.quartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年02月28日 17:28
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @PostMapping("/add")
    public String add(@RequestBody JobEntity job) {
        boolean b = quartzService.addJob(job);
        return !b ? "创建定时任务失败" : "创建定时任务成功:" + job.getJobId() + job.getJobName();
    }

    @PostMapping("/run")
    public String run(@RequestBody JobEntity job) {
        boolean b = quartzService.runJob(job);
        return !b ? "启动定时任务失败" : "启动定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @PostMapping("/update")
    public String update(@RequestBody JobEntity job) {
        boolean b = quartzService.updateJob(job);
        return !b ? "修改定时任务失败" : "修改定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @PostMapping("/pause")
    public String pause(@RequestBody JobEntity job) {
        boolean b = quartzService.pauseJob(job);
        return !b ? "暂停定时任务失败" : "暂停定时任务成功" +job.getJobId()+job.getJobName();
    }

    @PostMapping("/resume")
    public String resumeJob(@RequestBody JobEntity job) {
        boolean result = quartzService.resumeJob(job);
        if (!result) {
            return "唤醒定时任务失败";
        }
        return "唤醒定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @PostMapping("/delete")
    public String deleteJob(@RequestBody JobEntity job) {
        boolean result = quartzService.deleteJob(job);
        if (!result) {
            return "删除定时任务失败";
        }
        return "删除定时任务成功:"+job.getJobId()+job.getJobName();
    }

    @GetMapping("/query")
    public String queryJob(@RequestBody JobEntity job) {
        JSONObject result = quartzService.queryJob(job);
        if (null == result) {
            return "不存在对应的任务:"+job.getJobId()+job.getJobName();
        }
        return result.toString();
    }

}
