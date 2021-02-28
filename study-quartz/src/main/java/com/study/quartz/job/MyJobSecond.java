package com.study.quartz.job;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年02月28日 17:36
 */
@Slf4j
@Component
public class MyJobSecond implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("========= MyJobSecond任务开始执行 =========");

        String name = context.getJobDetail().getKey().getName();
        log.info("MyJobSecond任务[{}]正在执行", name);
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JSONObject jsonObject = (JSONObject) jobDataMap.get("myValue");
        log.info("MyJobSecond任务[{}]携带的参数[{}]", name, jsonObject.toString());

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("当前时间[{}],MyJobSecond任务[{}]的线程名[{}]",time,name,Thread.currentThread().getName());

        log.info("========= MyJobSecond任务执行结束 =========");
    }
}
