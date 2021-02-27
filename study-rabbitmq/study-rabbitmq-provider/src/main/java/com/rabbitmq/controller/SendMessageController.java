package com.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author liushuai
 * @version 1.0.0
 * @Description <p></p>
 * @createTime 2021年02月24日 23:18
 */
@RestController
@RequestMapping("/mq")
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public JSONObject sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> json = new HashMap<>();
        json.put("messageId",messageId);
        json.put("messageData",messageData);
        json.put("createTime",createTime);
        // 开启事务
//        rabbitTemplate.setChannelTransacted(true);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting1", json);
        JSONObject res = new JSONObject();
        res.put("data", "发送成功！");
        res.put("success", true);
        return res;
    }

    @GetMapping("/sendDirectMessage1")
    public JSONObject sendDirectMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> json = new HashMap<>();
        json.put("messageId",messageId);
        json.put("messageData",messageData);
        json.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting2", json);
        JSONObject res = new JSONObject();
        res.put("data", "发送成功！");
        res.put("success", true);
        return res;
    }

    @GetMapping("/sendTopicMessage1")
    public JSONObject sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: MAN";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        JSONObject json = new JSONObject();
        json.put("messageId",messageId);
        json.put("messageData",messageData);
        json.put("createTime",createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", json);
        JSONObject res = new JSONObject();
        res.put("data", "发送成功！");
        res.put("success", true);
        return res;
    }

    @GetMapping("/sendTopicMessage2")
    public JSONObject sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: WOMAN is all";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        JSONObject json = new JSONObject();
        json.put("messageId",messageId);
        json.put("messageData",messageData);
        json.put("createTime",createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", json);
        JSONObject res = new JSONObject();
        res.put("data", "发送成功！");
        res.put("success", true);
        return res;
    }

    @GetMapping("/sendFanoutMessage")
    public JSONObject sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        JSONObject json = new JSONObject();
        json.put("messageId",messageId);
        json.put("messageData",messageData);
        json.put("createTime",createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, json);
        JSONObject res = new JSONObject();
        res.put("data", "发送成功！");
        res.put("success", true);
        return res;
    }



}
