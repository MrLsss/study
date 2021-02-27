package com.rabbitmq.receiver;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author liushuai
 * @version 1.0.0
 * @Description <p></p>
 * @createTime 2021年02月25日 00:03
 */
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {

    @RabbitHandler
    public void handler(JSONObject json) {
        System.out.println("FanoutReceiverB 消费者收到消息：" + json.toJSONString());
    }

}
