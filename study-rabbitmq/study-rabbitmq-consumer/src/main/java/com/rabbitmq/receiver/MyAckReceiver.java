package com.rabbitmq.receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author liushuai
 * @version 1.0.0
 * @Description 对应的手动确认消息监听类，MyAckReceiver.java（手动确认模式需要实现 ChannelAwareMessageListener）
 * @createTime 2021年02月25日 00:32
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {


    /**
     * ①自动确认， 这也是默认的消息确认情况。  AcknowledgeMode.NONE
     * RabbitMQ成功将消息发出（即将消息成功写入TCP Socket）中立即认为本次投递已经被正确处理，不管消费者端是否成功处理本次投递。
     * 所以这种情况如果消费端消费逻辑抛出异常，也就是消费端没有处理成功这条消息，那么就相当于丢失了消息。
     * 一般这种情况我们都是使用try catch捕捉异常后，打印日志用于追踪数据，这样找出对应数据再做后续处理。
     *
     * ② 根据情况确认， 这个不做介绍
     * ③ 手动确认 ， 这个比较关键，也是我们配置接收消息确认机制时，多数选择的模式。
     * 消费者收到消息后，手动调用basic.ack/basic.nack/basic.reject后，RabbitMQ收到这些消息后，才认为本次投递成功。
     * basic.ack用于肯定确认
     * basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展）
     * basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息
     *
     * 消费者端以上的3个方法都表示消息已经被正确投递，但是basic.ack表示消息已经被正确处理。
     * 而basic.nack,basic.reject表示没有被正确处理：
     *
     * reject:有时候一些场景是需要重新入列的。
     * channel.basicReject(deliveryTag, true);  拒绝消费当前消息，如果第二参数传入true，就是将数据重新丢回队列里，那么下次还会消费这消息。
     * 设置false，就是告诉服务器，我已经知道这条消息数据了，因为一些原因拒绝它，而且服务器也把这个消息丢掉就行。下次不想再消费这条消息了。
     * 使用拒绝后重新入列这个确认模式要谨慎，因为一般都是出现异常的时候，catch异常再拒绝入列，选择是否重入列。
     * 但是如果使用不当会导致一些每次都被你重入列的消息一直消费-入列-消费-入列这样循环，会导致消息积压。
     *
     * nack:这个也是相当于设置不消费某条消息。
     * channel.basicNack(deliveryTag, false, true);
     * 第一个参数依然是当前消息到的数据的唯一id;
     * 第二个参数是指是否针对多条消息；如果是true，也就是说一次性针对当前通道的消息的tagID小于当前这条消息的，都拒绝确认。
     * 第三个参数是指是否重新入列，也就是指不确认的消息是否重新丢回到队列里面去。
     *
     * 同样使用不确认后重新入列这个确认模式要谨慎，因为这里也可能因为考虑不周出现消息一直被重新丢回去的情况，导致积压。
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String s = message.toString();
            String[] split = s.split("'");
            System.out.println(split[1]);

            // 多个队列也变成手动确认，而且不同队列实现不同的业务处理。或者完全可以分开多个消费者项目去监听处理
            if ("TestDirectQueue".equals(message.getMessageProperties().getConsumerQueue())) {
                System.out.println("执行TestDirectQueue中的消息的业务处理流程......");
            }
            if ("fanout.A".equals(message.getMessageProperties().getConsumerQueue())) {
                System.out.println("执行fanout.A中的消息的业务处理流程......");
            }

            System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
            channel.basicAck(deliveryTag, true);//第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
//			channel.basicReject(deliveryTag, true);//第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
}
