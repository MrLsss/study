//package com.rabbitmq.receiver;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author liushuai
// * @version 1.0.0
// * @Description 消息接收监听类
// * @createTime 2021年02月24日 23:26
// */
//@Component
//public class DirectReceiver {
//
//    @RabbitListener(queues = "TestDirectQueue1") // 监听的队列名称
//    @RabbitHandler
//    public void handler(JSONObject testMessage) {
//        System.out.println("DirectReceiver消费者收到TestDirectQueue1消息：" + testMessage.toJSONString());
//    }
//
//    @RabbitListener(queues = "TestDirectQueue2")
//    @RabbitHandler
//    public void handler2(JSONObject testMessage) {
//        System.out.println("DirectReceiver消费者收到TestDirectQueue2消息：" + testMessage.toJSONString());
//    }
//
//}
