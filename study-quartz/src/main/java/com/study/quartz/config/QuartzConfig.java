package com.study.quartz.config;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description: 调度工厂，线程池属性等等属性配置项
 *  使用的jar包提供的默认配置，也可以自己在配置文件中配置
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年02月28日 15:51
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private JobFactory jobFactory;

    /**
     * 调度类FactoryBean
     */
    @Bean("schedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setSchedulerName("xxx test Scheduler");
        // 延时启动
        schedulerFactoryBean.setStartupDelay(1);
        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true
        schedulerFactoryBean.setAutoStartup(true);
        // 设置调度类quartz属性
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        // 设置jobFactory
        schedulerFactoryBean.setJobFactory(jobFactory);

        // 可以自定义quartz相关的参数
        /*
        // quartz参数
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "RuoyiScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        // 线程池配置
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        // JobStore配置
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        // 集群配置
        prop.put("org.quartz.jobStore.isClustered", "true");
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");

        // sqlserver 启用
        // prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        */
//        schedulerFactoryBean.setQuartzProperties(prop);
        return schedulerFactoryBean;
    }

    /**
     * 解析quartz.properties文件，填充属性
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        //若不做额外配置,会有默认的配置文件加载 在jar org.quartz里面 有一份quartz.properties
        //propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();;
        return propertiesFactoryBean.getObject();
    }

    /**
     * 初始化quartz监听器
     */
    @Bean
    public QuartzInitializerListener initializerListener() {
        return new QuartzInitializerListener();
    }

    /**
     * 根据调度类工厂bean获取调度
     */
    @Bean("scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
