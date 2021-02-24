package com.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author liushuai
 * @version 1.0.0
 * @Description <p></p>
 * @createTime 2021年02月25日 00:15
 */
@RestController
@RequestMapping("/callback")
public class CallBackController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * ①消息推送到server，但是在server里找不到交换机
     */
    @GetMapping("/messageAck")
    public JSONObject messageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        JSONObject json = new JSONObject();
        json.put("messageId", messageId);
        json.put("messageData", messageData);
        json.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", json);

        JSONObject res = new JSONObject();
        res.put("data", "success");
        res.put("success", true);
        return res;
    }

    /**
     * ②消息推送到server，找到交换机了，但是没找到队列
     */
    @GetMapping("/messageAck2")
    public JSONObject messageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        JSONObject json = new JSONObject();
        json.put("messageId", messageId);
        json.put("messageData", messageData);
        json.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", json);

        JSONObject res = new JSONObject();
        res.put("data", "success");
        res.put("success", true);
        return res;
    }

}
