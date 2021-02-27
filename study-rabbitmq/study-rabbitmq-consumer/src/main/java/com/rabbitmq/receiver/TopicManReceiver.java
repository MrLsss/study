package com.rabbitmq.receiver;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liushuai
 * @version 1.0.0
 * @Description
 * @createTime 2021年02月24日 23:43
 */
@Component
@RabbitListener(queues = "topic.man")
public class TopicManReceiver {

    @RabbitHandler
    public void handler(JSONObject json) {
        System.out.println("TopicManReceiver 消费者收到消息：" + json.toJSONString());
    }

}
