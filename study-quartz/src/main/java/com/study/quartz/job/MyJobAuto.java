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
 * <p> Description: 随着项目启动就启动的定时任务
 * 需要监听项目启动，对应的项目启动监听类{@link com.study.quartz.listener.ApplicationStartListener}
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年02月28日 16:37
 */
@Slf4j
@Component
public class MyJobAuto implements Job {

    private void before() {
        log.info("******MyJobAuto任务开始执行******");
    }

    @Override
    public void execute(JobExecutionContext context) {
        before();
        // 定时任务处理的业务逻辑
        // ...

        String name = context.getJobDetail().getKey().getName();
        log.info("******MyJobAuto任务[{}]正在执行******", name);
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JSONObject jsonObject = (JSONObject) jobDataMap.get("myValue");
        log.info("MyJobAuto任务[{}]携带的参数[{}]", name, jsonObject.toString());
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("当前时间[{}],MyJobAuto任务[{}]的线程名[{}]", time, name, Thread.currentThread().getName());
        after();
    }

    private void after() {
        log.info("******MyJobAuto任务执行结束******");
    }
}
