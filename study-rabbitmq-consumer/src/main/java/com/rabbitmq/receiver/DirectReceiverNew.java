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
//public class DirectReceiverNew {
//
//
//    /**
//     * 当配置多个监听绑定到同一个直连交换机的同一队列
//     * 结果：轮训消费，不会重复消费
//     */
//    @RabbitHandler
//    public void handler(JSONObject testMessage) {
//        System.out.println("DirectReceiverNew消费者收到消息：" + testMessage.toJSONString());
//    }
//}
