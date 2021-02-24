package com.rabbitmq.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liushuai
 * @version 1.0.0
 * @Description Rabbitmq 配置
 * @createTime 2021年02月25日 00:08
 */
@Configuration
public class RabbitConfig {

    /**
     * 配置相关的消息确认回调函数
     * 回调函数触发情况：
     * ①消息推送到server，但是在server里找不到交换机
     * ②消息推送到server，找到交换机了，但是没找到队列
     * ③消息推送到sever，交换机和队列啥都没找到
     * ④消息推送成功
     * @{link CallBackController} 对应四种情况
     *
     * 结论：
     * ①这种情况触发的是 ConfirmCallback 回调函数
     * ②这种情况触发的是 ConfirmCallback和ReturnsCallback两个回调函数
     *      1.这种情况下，消息是推送成功到服务器了的，所以ConfirmCallback对消息确认情况是true；
     *      2.而在RetrunCallback回调函数的打印参数里面可以看到，消息是推送到了交换机成功了，
     *      但是在路由分发给队列的时候，找不到队列，所以报了错误 NO_ROUTE 。
     * ③这种情况触发的是 ConfirmCallback 回调函数。
     *      这种情况其实一看就觉得跟①很像，没错 ，③和①情况回调是一致的
     * ④这种情况触发的是 ConfirmCallback 回调函数。
     */
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("ConfirmCallback:    " + "相关数据：" + correlationData);
                System.out.println("ConfirmCallback:    " + "确认情况：" + b);
                System.out.println("ConfirmCallback:    " + "原因：" + s);
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("ReturnsCallback     " + "消息：" + returnedMessage.getMessage());
                System.out.println("ReturnsCallback     " + "回应码：" + returnedMessage.getReplyCode());
                System.out.println("ReturnsCallback     " + "回应消息：" + returnedMessage.getReplyText());
                System.out.println("ReturnsCallback     " + "交换机：" + returnedMessage.getExchange());
                System.out.println("ReturnsCallback     " + "路由键：" + returnedMessage.getRoutingKey());
            }
        });

        return rabbitTemplate;
    }

}
