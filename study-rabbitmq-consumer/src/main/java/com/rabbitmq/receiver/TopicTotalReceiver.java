package com.rabbitmq.receiver;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liushuai
 * @version 1.0.0
 * @Description <p></p>
 * @createTime 2021年02月24日 23:45
 */
@Component
@RabbitListener(queues = "topic.woman")
public class TopicTotalReceiver {

    @RabbitHandler
    public void handler(JSONObject json) {
        System.out.println("TopicTotalReceiver 消费者收到信息：" + json.toJSONString());
    }
}
