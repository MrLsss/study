package com.study.quartz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.study.quartz.config.JobEntity;
import com.study.quartz.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年02月28日 16:02
 */
@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @Override
    public boolean addJob(JobEntity job) {
        try {
            JSONObject data = job.getData();
            log.info("当前任务携带的业务参数：{}", data.toJSONString());
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("myValue", data);
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName; // 任务唯一标识
            JobDetail jobDetail = JobBuilder
                    // 指定执行类
                    .newJob((Class<? extends Job>) Class.forName(job.getClassName()))
                    // 指定name和group
                    .withIdentity(jobUnique, job.getJobGroup())
                    .requestRecovery().withDescription(job.getDescription())
                    .setJobData(jobDataMap)
                    .build();
            // 创建表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            // 创建触发器
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(job.getTriggerName(), job.getTriggerGroup())
                    .withDescription(job.getDescription())
                    .withSchedule(cronScheduleBuilder).build();
//            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
//                    .usingJobData("trigger1", "这是jobDetail1的trigger")
//                    .startNow()//立即生效
//                    .startAt(startDate)
//                    .endAt(endDate)
//                    .withSchedule(CronScheduleBuilder.cronSchedule("* 30 10 ? * 1/5 2018"))
//                    .build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
            // 创建成功了并不马上执行
//            scheduler.pauseJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            scheduler.start();
            log.info("定时任务[{}]创建成功，开始执行", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean runJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            scheduler.triggerJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            log.info("定时任务[{}]执行成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTriggerGroup());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            // 重构表达式
            CronTrigger trigger = cronTrigger.getTriggerBuilder()
                    .withIdentity(triggerKey).withSchedule(cronScheduleBuilder)
                    .withDescription(job.getDescription()).build();
            scheduler.scheduleJob(trigger);
            log.info("定时任务[{}]更新成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean pauseJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            scheduler.pauseJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            log.info("定时任务[{}]暂停成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean resumeJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            scheduler.resumeJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            log.info("定时任务[{}]唤醒成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteJob(JobEntity job) {
        try {
            String jobId = job.getJobId();
            String jobName = job.getJobName();
            String jobUnique = jobId + jobName;
            scheduler.deleteJob(JobKey.jobKey(jobUnique, job.getJobGroup()));
            log.info("定时任务[{}]删除成功", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public JSONObject queryJob(JobEntity job) {
        TriggerKey triggerKey = new TriggerKey(job.getTriggerName(), job.getTriggerGroup());
        try {
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (cronTrigger == null) {
                return null;
            }
            JSONObject res = new JSONObject();
            res.put("expression", cronTrigger.getCronExpression());
            res.put("state", scheduler.getTriggerState(triggerKey));
            res.put("description", cronTrigger.getDescription());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
