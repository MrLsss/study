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
//@RabbitListener(queues = "TestDirectQueue") // 监听的队列名称
//public class DirectReceiver {
//
//    @RabbitHandler
//    public void handler(JSONObject testMessage) {
//        System.out.println("DirectReceiver消费者收到消息：" + testMessage.toJSONString());
//    }
//}
