# Quartz定时任务学习

## 简介
> Quartz是OpenSymphony开源组织在Job scheduling领域又一个开源项目，完全由Java开发，可以用来执行定时任务，类似于java.util.Timer。但是相较于Timer， Quartz增加了很多功能：
> - 持久性作业 - 就是保持调度定时的状态;
> - 作业管理 - 对调度作业进行有效的管理;

### 举例
大部分公司都会用到定时任务这个功能。
拿火车票购票来说，当你下单后，后台就会插入一条待支付的task(job)，一般是30分钟，超过30min后就会执行这个job，去判断你是否支付，未支付就会取消此次订单；当你支付完成之后，后台拿到支付回调后就会再插入一条待消费的task（job），Job触发日期为火车票上的出发日期，超过这个时间就会执行这个job，判断是否使用等。

在我们实际的项目中，当Job过多的时候，肯定不能人工去操作，这时候就需要一个任务调度框架，帮我们自动去执行这些程序。

## Quartz任务调度框架

### 如何实现一个任务调度框架

1. 首先需要定义一个定时功能的接口，可以称之为Job（或Task），例如定时发送邮件的Job（Task），发送消息的Job（Task）等。接口实现如下：

    ![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/Quartz/Quartz%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1%E5%AD%A6%E4%B9%A0-1.png)

2. 有了任务之后，还需要一个能够实现触发任务去执行的触发器，触发器Trigger最基本的功能是指定Job的执行时间、执行间隔、运行次数等。

    ![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/Quartz/Quartz%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1%E5%AD%A6%E4%B9%A0-2.png)

3. 有了Job和Trigger后，怎么将两者结合起来呢？怎样指定Trigger去执行指定的Job呢？这时就需要一个Schedule，来负责这个功能的实现。

    ![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/Quartz/Quartz%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1%E5%AD%A6%E4%B9%A0-3.png)

### Quartz的实现

### Quartz有四个核心概念


- **Job**：是一个接口，只定义一个方法 execute（JobExecutionContext context），在实现接口的 execute 方法中编写所需要定时执行的 Job（任务），JobExecutionContext 类提供了调度应用的一些信息；Job 运行时的信息保存在 JobDataMap 实例中。
- **JobDetail**：Quartz 每次调度 Job 时，都重新创建一个 Job 实例，因此它不接受一个 Job 的实例，相反它接收一个 Job 实现类（JobDetail，描述 Job 的实现类及其他相关的静态信息，如 Job 名字、描述、关联监听器等信息），以便运行时通过 newInstance() 的反射机制实例化 Job。
- **trigger**：是一个类，描述触发 Job 执行的时间触发规则，主要有 SimpleTrigger 和 CronTrigger 这两个子类。当且仅当需调度一次或者以固定时间间隔周期执行调度，SimpleTrigger 是最适合的选择；而 CronTrigger 则可以通过 Cron 表达式定义出各种复杂时间规则的调度方案：如工作日周一到周五的 15：00 ~ 16：00 执行调度等。
- **Scheduler**：调度器就相当于一个容器，装载着任务和触发器，该类是一个接口，代表一个 Quartz 的独立运行容器，Trigger 和 JobDetail 可以注册到 Scheduler 中，两者在 Scheduler 中拥有各自的组及名称，组及名称是 Scheduler 查找定位容器中某一对象的依据，Trigger 的组及名称必须唯一，JobDetail 的组和名称也必须唯一（但可以和 Trigger 的组和名称相同，因为它们是不同类型的）。Scheduler 定义了多个接口方法，允许外部通过组及名称访问和控制容器中 Trigger 和 JobDetail。

如下图：

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/Quartz/Quartz%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1%E5%AD%A6%E4%B9%A0-4.png)

*Job 为作业的接口，为任务调度的对象；JobDetail 用来描述 Job 的实现类及其他相关的静态信息；Trigger 做为作业的定时管理工具，一个 Trigger 只能对应一个作业实例，而一个作业实例可对应多个触发器；Scheduler 做为定时任务容器，是 Quartz 最上层的东西，它提携了所有触发器和作业，使它们协调工作，每个 Scheduler 都存有 JobDetail 和 Trigger 的注册，一个 Scheduler 中可以注册多个 JobDetail 和多个 Trigger。*

## springboot整合Quartz

1. 首先引入依赖

```xml
<!-- quartz -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```

2. 创建定时任务使用的动态参数 实体类

```java
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
```

3. 创建Job的实例工厂（使用默认的也许会出现spring的@Autowired无法注入的情况）

```java
@Component
public class JobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
```

4. Quartz配置类

```java
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
        schedulerFactoryBean.setSchedulerName("xxx test Scheduler");
        // 设置调度类quartz属性
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        // 设置jobFactory
        schedulerFactoryBean.setJobFactory(jobFactory);

        return schedulerFactoryBean;
    }

    /**
     * 解析quartz.properties文件，填充属性
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
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
```

5. 创建一个随着项目启动就启动的定时任务 

```java
@Slf4j
@Configuration
public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private QuartzService quartzService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        JobEntity job = new JobEntity();

        job.setJobId(UUID.randomUUID().toString().replaceAll("-", ""));

        job.setClassName("com.study.quartz.job.MyJobAuto");//注意,这里的路径请改成你自己的
        job.setCronExpression("0/3 * * * * ?"); // 每3秒执行一次
        job.setJobName("AutoJob");
        job.setJobGroup("AutoJobGroup");
        job.setTriggerName("AutoJobTrigger");
        job.setTriggerGroup("AutoJobTriggerGroup");
        job.setDescription("AutoJob-随项目启动");
        //可以将任务跟数据库挂钩,做个任务管理模块,获取需要自启动的任务,记录各个参数等
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("testKey", "测试即启动");
        job.setData(jsonObject);
        quartzService.addJob(job);
        log.info("即触发定时任务已经开始执行.. .");
        log.info("************application已经启动完毕************");
    }
}
```

其中quartzService.addJob(job)方法：

```java
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
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
            log.info("定时任务[{}]创建成功，开始执行", jobUnique);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
```

启动项目：

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/Quartz/Quartz%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1%E5%AD%A6%E4%B9%A0-5.jpg)

如何动态调度定时任务，可以参考代码中的实现。

## Quartz参数详解

下面就程序中出现的几个参数，Quartz中的核心参数。

- Job和JobDetail
- JobExecutionContext
- JobDataMap
- Trigger、SimpleTrigger、CronTrigger

### Job和JobDetail

Job是Quartz中的一个接口，只有一个execute方法，在execute方法中编写业务逻辑。

源码如下：

```java
package org.quartz;

public interface Job {
    void execute(JobExecutionContext var1) throws JobExecutionException;
}
```

JobDetail用来绑定Job，为Job实例提供许多属性。

- name

- group

- jobClass

- jobDataMap

JobDetail绑定指定的Job，每次Scheduler调度执行一个Job的时候，首先会拿到对应的Job，然后创建Job实例，再去执行Job中的execute()的内容，任务执行结束后，关联的Job对象实例会被释放，且会被JVM GC清除。

为什么设计成JobDetail + Job，不直接使用Job

> JobDetail定义的是任务数据，而真正的执行逻辑是在Job中。
>
> 这是因为任务是有可能并发执行的，如果Scheduler直接使用Job，就会存在对同一个Job实例并发访问的问题。而JobDetail & Job方式，Scheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以避免并发访问的问题。

### JobExecutionContext

JobExecutionContext中包含了Quartz运行时的环境以及Job本身的详细数据信息。
当Scheduler调度执行一个Job的时候，就会将JobExecutionContext传递给该Job的execute()中，Job就可以通过JobExecutionContext对象获取信息。

  ### JobDataMap

JobDataMap实现了JDK的Map接口，可以以Key-Value的形式存储数据。
JobDetail、Trigger都可以使用JobDataMap来设置一些参数或信息：

```java
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
```

Job执行execute()方法的时候，JobExecutionContext可以获取到jobDataMap中的信息：

```java
@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JSONObject jsonObject = (JSONObject) jobDataMap.get("myValue");
    }
```

### Trigger、SimpleTrigger、CronTrigger

- **Trigger**

  Trigger是Quartz的触发器，会去通知Scheduler何时去执行对应Job。

  ```java
  new Trigger().startAt():表示触发器首次被触发的时间; 
  new Trigger().endAt():表示触发器结束触发的时间;
  ```

- **SimpleTrigger**
  SimpleTrigger可以实现在一个指定时间段内执行一次作业任务或一个时间段内多次执行作业任务。
  下面的程序就实现了程序运行5s后开始执行Job，执行Job 5s后结束执行：

```java
Date startDate = new Date();
startDate.setTime(startDate.getTime() + 5000);

Date endDate = new Date();
endDate.setTime(startDate.getTime() + 5000);

Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                  .usingJobData("trigger1", "这是jobDetail1的trigger")
                  .startNow()//立即生效
                  .startAt(startDate)
                  .endAt(endDate)
                  .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                  .withIntervalInSeconds(1)//每隔1s执行一次
                  .repeatForever()).build();//一直执行
```

- **CronTrigger**

  CronTrigger功能非常强大，是基于日历的作业调度，而SimpleTrigger是精准指定间隔，所以相比SimpleTrigger，CroTrigger更加常用。CroTrigger是基于Cron表达式的，先了解下Cron表达式：
  由7个子表达式组成字符串的，格式如下：

  `[秒] [分] [小时] [日] [月] [周] [年]`

  Cron表达式的语法比较复杂
  1. * 30 10 ? * 1/5 * : 表示（从后往前看）
  指定年份 的 周一到周五 指定月 不指定日 上午10时 30分 指定秒

  2. 00 00 00 ？ * 10,11,12 1#5 2018 : 表示2018年10、11、12月的第一周的星期五这一天的0时0分0秒去执行任务。
  
  可通过在线生成Cron表达式的工具：http://cron.qqe2.com/ 来生成自己想要的表达式。

示例代码：

```java
public static void main(String[] args) throws SchedulerException, InterruptedException {
  // 1、创建调度器Scheduler
  SchedulerFactory schedulerFactory = new StdSchedulerFactory();
  Scheduler scheduler = schedulerFactory.getScheduler();
  // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
  JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class)
    .usingJobData("jobDetail1", "这个Job用来测试的")
    .withIdentity("job1", "group1").build();
  // 3、构建Trigger实例,每隔1s执行一次
  Date startDate = new Date();
  startDate.setTime(startDate.getTime() + 5000);

  Date endDate = new Date();
  endDate.setTime(startDate.getTime() + 5000);

  CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
    .usingJobData("trigger1", "这是jobDetail1的trigger")
    .startNow()//立即生效
    .startAt(startDate)
    .endAt(endDate)
    .withSchedule(CronScheduleBuilder.cronSchedule("* 30 10 ? * 1/5 2018"))
    .build();

  //4、执行
  scheduler.scheduleJob(jobDetail, cronTrigger);
  System.out.println("--------scheduler start ! ------------");
  scheduler.start();
  System.out.println("--------scheduler shutdown ! ------------");

}
```

