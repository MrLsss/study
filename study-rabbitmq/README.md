# RabbitMq学习总结
## RabbitMq介绍

MQ全称为Message Queue，即消息队列， RabbitMQ是由erlang语言开发，基于AMQP（Advanced Message
Queue 高级消息队列协议）协议实现的消息队列，它是一种应用程序之间的通信方法，消息队列在分布式系统开
发中应用非常广泛。RabbitMQ官方地址：http://www.rabbitmq.com/

## 使用MQ的好处

### 实现异步处理

同步的通信：发出一个调用请求之后，在没有得到结果之前，就不返回。由调用者主动等待这个调用的结果。

异步通信：调用在发出之后，这个调用就直接返回了，所以没有返回结果。也就是说，当一个异步过程调用发出后，调用者不会马上得到结果。而是在调用发出后，

被调用者通过状态、通知来通知调用者，或通过回调函数处理这个调用。

### 实现解耦

耦合是系统内部或者系统之间存在相互作用，相互影响和相互依赖。在我们的分布式系统中，一个业务流程涉及多个系统的时候，他们之间就会形成一个依赖关系。

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-1.png)

在传统的通信方式中，订单系统发生了退货的动作，那么要依次调用所有下游系统的 API，比如调用库存系统的 API 恢复库存，因为这张火车票还要释放出去给其他乘客购买；调用支付系统的 API，不论是支付宝微信还是银行卡，要把手续费扣掉以后，原路退回给消费者；调用通知系统 API 通知用户退货成功。

```java
// 伪代码 
public void returnGoods() { 
    stockService.updateInventory();
    payService.refund();
    noticeService.notice(); 
}
```

这个过程是串行执行的，如果在恢复库存的时候发生了异常，那么后面的代码都不会执行。由于这一系列的动作，恢复库存，资金退还，发送通知，本质上没有一个严格的先后顺序，也没有直接的依赖关系，也就是说，只要用户提交了退货的请求，后面的这些动作都是要完成的。库存有没有恢复成功，不影响资金的退还和发送通知。

使用MQ

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-2.png)

订单系统只需要把退货的消息发送到消息队列上，由各个下游的业务系统自己创建队列，然后监听队列消费消息。

在这种情况下订单系统里面就不需要配置其他系统的 IP、端口、接口地址了，因为它不需要关心消费者在网络上的什么位置，所以下游系统改 IP 没有任何影响。

甚至不需要关心消费者有没有消费成功，它只需要把消费发到消息队列的服务器上就可以了。这样，我们就实现了系统之间依赖关系的解耦。

### 实现流量削峰

在很多的电商系统里面，有一个瞬间流量达到峰值的情况，比如京东的 618，淘宝的双 11。普通的硬件服务器肯定支撑不了这种百万或者千万级别的并发量，就像 2012 年的小米一样，动不动服务器就崩溃。如果通过堆硬件的方式去解决，那么在流量峰值过去以后就会出现巨大的资源浪费。那要怎么办呢？如果说要保护我们的应用服务器和数据库，限流也是可以的，但是这样又会导致订单的丢失，没有达到我们的目的。

引入MQ，MQ是队列，一定有队列的特性，(先进先出)就可以先把所有的流量承接下来，转换成 MQ 消息发送到消息队列服务器上，业务层就可以根据自己的消费速率去处理这些消息，

处理之后再返回结果。就像我们在火车站排队一样，大家只能一个一个买票，不会因为人多就导致售票员忙不过来。如果要处理快一点，大不了多开几个窗口（增加几个消费者）。

### 总结

- 对于数据量大或者处理耗时长的操作，我们可以引入 MQ 实现异步通信，减少客户端的等待，提升响应速度。

- 对于改动影响大的系统之间，可以引入 MQ 实现解耦，减少系统之间的直接依赖。

- 对于会出现瞬间的流量峰值的系统，我们可以引入 MQ 实现流量削峰，达到保护应用和数据库的目的。

## RabbitMq中的模型概念

MQ的本质：消息队列，又叫做消息中间件。是指用高效可靠的消息传递机制进行与平台无关的数据交流，并基于数据通信来进行分布式系统的集成。通过提供消息传递和消息队列模型， 可以在分布式环境下扩展进程的通信

###  MQ的特点

1. 是一个独立运行的服务。生产者发送消息，消费者接收消费，需要先跟服务器建立连接。
2. 采用队列作为数据结构，有先进先出的特点。
3. 具有发布订阅的模型，消费者可以获取自己需要的消息。

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-3.png)



### 消息模型

所有 MQ 产品从模型抽象上来说都是一样的过程：消费者（consumer）订阅某个队列。生产者（producer）创建消息，然后发布到队列（queue）中，最后将消息发送到监听的消费者。

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-4.png)

### RabbitMq的基本概念

下图是RabbitMQ的基本结构：

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-5.png)

#### 组成部分说明

- Broker：消息队列服务，此进程包括两个部分：Exchange和Queue。
- Exchange：消息队列交换机，按一定的规则将消息路由转发到某个队列，对消息进行过虑。队列使用绑定键（Binding Key）跟交换机建立绑定关系。
- Queue：消息队列，存储消息的队列，消息到达队列并转发给指定的消费方，它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。
- Binding：绑定，用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则，所以可以将交换器理解成一个由绑定构成的路由表。
- Routing Key：路由关键字，Exchange根据这个关键字进行消息投递
- Virtual Host：虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。每个 vhost 本质上就是一个 mini 版的 RabbitMQ 服务器，拥有自己的队列、交换器、绑定和权限机制。vhost 是 AMQP 概念的基础，必须在连接时指定，RabbitMQ 默认的 vhost 是 / 。
- Message：消息，消息是不具名，它由消息头和消息体组成。消息体是不透明的，而消息头则由一系列的可选属性组成，这些属性包括**routing-key**（路由键）、**priority**（相对于其他消息的优先权）、**delivery-mode**（指出该消息可能需要持久性存储）等。
- Producer：消息生产者，即生产方客户端，生产方客户端将消息发送到MQ。
- Consumer：消息消费者，即消费方客户端，接收MQ转发的消息。
- Channel：信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的TCP连接内地虚拟连接，AMQP 命令都是通过信道发出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成。因为对于操作系统来说建立和销毁 TCP 都是非常昂贵的开销，所以引入了信道的概念，以复用一条 TCP 连接。
- Connection：无论是生产者发送消息，还是消费者接收消息，都必须跟Broker之间建立一个连接，这个是TCP长连接

#### 相关名词

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-6.jpg)包括：ConnectionFactory（连接管理器）、Channel（信道）、Exchange（交换器）、Queue（队列）、RoutingKey（路由键）、BindingKey（绑定键）。

- **ConnectionFactory**（连接管理器）：应用程序与Rabbit之间建立连接的管理器，程序代码中使用；

- **Channel**（信道）：消息推送使用的通道；

- **Exchange**（交换器）：用于接受、分配消息；

- **Queue**（队列）：用于存储生产者的消息；

- **RoutingKey**（路由键）：用于把生成者的数据分配到交换器上；

- **BindingKey**（绑定键）：用于把交换器的消息绑定到队列上；

## 消息发布接收流程

![image](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-6.png)

黄色的圈圈就是我们的消息推送服务，将消息推送到 中间方框里面也就是 rabbitMq的服务器，然后经过服务器里面的交换机、队列等各种关系（后面会详细讲）将数据处理入列后，最终右边的蓝色圈圈消费者获取对应监听的消息。

### 发送消息

1. 生产者和Broker建立TCP连接。
2. 生产者和Broker建立通道。
3. 生产者通过通道消息发送给Broker，由Exchange将消息进行转发。
4. Exchange将消息转发到指定的Queue（队列）

### 接收消息

1. 消费者和Broker建立TCP连接
2. 消费者和Broker建立通道
3. 消费者监听指定的Queue（队列）
4. 当有消息到达Queue时Broker默认将消息推送给消费者。
5. 消费者接收到消息。

## RabbitMQ交换机

### Direct exchange（直连交换机）

> 是根据消息携带的路由键（routing key）将消息投递给对应队列的,队列与直连类型的交换机绑定，需指定一个精确的绑定键，生产者发送消息时会携带一个路由键。只有当路由键与其中的某个绑定键完全匹配时，这条消息才会从交换机路由到满足路由关系的此队列上。

如下图：

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-7.png)

不同的队列通过对应的路由键，绑定到直连交换机上，消息生产者推送的消息必须带上相应的路由键（routing key），推送到直连交换机，然后由交换机根据路由键去寻找匹配的队列进行处理。

### Fanout exchange（广播交换机）

> 主题类型的交换机与队列绑定时，不需要指定绑定键。因此生产者发送消息到广播类型的交换机上，也不需要携带路由键。消息达到交换机时，所有与之绑定了的队列，都会收到相同的消息的副本。

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-8.png)

*这个交换机没有路由键概念，就算你绑了路由键也是无视的。 这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。*

### Topic exchange（主题交换机）

队列通过路由键绑定到交换机上，然后交换机根据消息里的路由值，将消息路由给一个或多个绑定队列，队列与主题类型的交换机绑定时，可以在绑定键中使用通配符。

两个通配符：

- #：**用来表示任意数量（零个或多个）单词**

* *：**用来表示一个单词 (必须出现的)**

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-9.png)

第一个队列支持路由键以 junior 开头的消息路由，后面可以有单词，也可以没有。

第二个队列支持路由键以 netty 开头，并且后面是一个单词的消息路由。

第三个队列支持路由键以 jvm 结尾，并且前面是一个单词的消息路由。

**当一个队列的绑定键为 "#"（井号） 的时候，这个队列将会无视消息的路由键，接收所有的消息。当 * (星号) 和 # (井号) 这两个特殊字符都未在绑定键中出现的时候，此时主题交换机就拥有的直连交换机的行为。所以主题交换机也就实现了扇形交换机的功能，和直连交换机的功能。**

## 消息确认机制（消息可靠性）

> 生产者发送消息出去之后，不知道到底有没有发送到RabbitMQ服务器， 默认是不知道的。而且有的时候我们在发送消息之后，后面的逻辑出问题了，我们不想要发送之前的消息了，需要撤回该怎么做。

解决方案：

1. AMQP 事务机制
2. Confirm 模式

### 事务模式

RabbitMQ中与事务机制有关的方法有三个：`txSelect(),` `txCommit()`以及`txRollback()`

- txSelect(): 用于将当前channel设置成transaction模式
- txCommit: 用于提交事务
- txRollback: 用于回滚事务

![](https://my-study-notes.oss-cn-beijing.aliyuncs.com/RabbitMq/RabbitMq%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93-10.png)

在事务模式里面，只有收到了服务端的 Commit-OK 的指令，才能提交成功。所以可以解决生产者和服务端确认的问题。但是事务模式有一个缺点，它是阻塞的，一条消息没有发送完毕，不能发送下一条消息，它会榨干 RabbitMQ 服务器的性能。所以不建议大家在生产环境使用。

Springboot整合RabbitMq实现

```java
public String send(String msg){
    for (int i = 0; i < 10; i++) {
        //在springboot设置事务
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.convertAndSend("faoutEmailQueue1",i);
    }
    return "success";
}
```

### 生产者确认Confirm 模式

配置文件：

```yml
spring:
  application:
    name: rabbitmq-provider
  rabbitmq:
    host: 192.168.31.58
    port: 5672
    username: guest
    password: guest
    #确认消息已发送到队列(Queue)
    publisher-returns: true
    #确认消息已发送到交换机(Exchange)
    publisher-confirm-type: correlated
```

生产者RabbitMq配置类：

```java
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                if (ack) {
                    System.out.println("生产者ACK成功:" + correlationData.getId());
                } else {
                    System.out.println("ConfirmCallback:    " + "相关数据：" + correlationData);
                    System.out.println("ConfirmCallback:    " + "确认情况：" + ack);
                    System.out.println("ConfirmCallback:    " + "原因：" + s);
                }
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
```

*注意事项： 但是在主线程发送消息的过程中，rabbitMQ服务器关闭，这时候主程序和 ConfirmCallback 线程都
会等待Connection恢复，然后重新启动 rabbitmq ，当应用程序重新建立 connection 之后，两个线程都会死锁*

#### 推送情况

上面写了两个回调函数，一个叫 ConfirmCallback ，一个叫 RetrunCallback。那么以上这两种回调函数都是在什么情况会触发呢？

推送消息存在四种情况：

1. 消息推送到server，但是在server里找不到交换机。

2. 消息推送到server，找到交换机了，但是没找到队列

3. 消息推送到sever，交换机和队列都没找到

4. 消息推送成功

具体测试代码可见[CallBackController](https://github.com/MrLsss/study/blob/main/study-rabbitmq/study-rabbitmq-provider/src/main/java/com/rabbitmq/controller/CallBackController.java)。

结论：

1. 这种情况触发的是 ConfirmCallback 回调函数

2. 这种情况触发的是 ConfirmCallback和ReturnsCallback两个回调函数

    1. 这种情况下，消息是推送成功到服务器了的，所以ConfirmCallback对消息确认情况是true；

    2. 而在ReturnCallback回调函数的打印参数里面可以看到，消息是推送到了交换机成功了，但是在路由分发给队列的时候，找不到队列，所以报了错误 NO_ROUTE 。

3. 这种情况触发的是 ConfirmCallback 回调函数。
    1. 这种情况其实一看就觉得跟①很像，没错 ，③和①情况回调是一致的

4. 这种情况触发的是 ConfirmCallback 回调函数。

**可以在回调函数根据需求做对应的扩展或者业务数据处理**

### 消费者消息确认

和生产者的消息确认机制不同，因为消息接收本来就是在监听消息，符合条件的消息就会消费下来。
所以，消息接收的确认机制主要存在三种模式：

1. 自动确认

   这也是默认的消息确认情况。  `AcknowledgeMode.NONE`
   RabbitMQ成功将消息发出（即将消息成功写入TCP Socket）中立即认为本次投递已经被正确处理，不管消费者端是否成功处理本次投递。
   所以这种情况如果消费端消费逻辑抛出异常，也就是消费端没有处理成功这条消息，那么就相当于丢失了消息。
   一般这种情况我们都是使用try catch捕捉异常后，打印日志用于追踪数据，这样找出对应数据再做后续处理。

2. 根据情况确认

3. 手动确认

   这个比较关键，也是我们配置接收消息确认机制时，多数选择的模式。

   消费者收到消息后，手动调用`basic.ack/basic.nack/basic.reject`后，RabbitMQ收到这些消息后，才认为本次投递成功。

    - **basic.ack**：用于肯定确认

    - **basic.nack**：用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展） （*相当于设置不消费某条消息*）

        - `channel.basicNack(deliveryTag, false, true);`：

          第一个参数是当前消息到的数据的唯一id;
          第二个参数是指是否针对多条消息；如果是true，也就是说一次性针对当前通道的消息的tagID小于当前这条消息的，都拒绝确认。
          第三个参数是指是否重新入列，也就是指不确认的消息是否重新丢回到队列里面去。

          *同样使用不确认后重新入列这个确认模式要谨慎，因为这里也可能因为考虑不周出现消息一直被重新丢回去的情况，导致积压。*

    - **basic.reject**：用于否定确认，但与basic.nack相比有一个限制，一次只能拒绝单条消息 （*一些场景是需要重新入列的*）

        - `channel.basicReject(deliveryTag, true)`：拒绝消费当前消息，如果第二参数传入true，就是将数据重新丢回队列里，那么下次还会消费这消息。设置false，就是告诉服务器，我已经知道这条消息数据了，因为一些原因拒绝它，而且服务器也把这个消息丢掉就行。 下次不想再消费这条消息了。

          *使用拒绝后重新入列这个确认模式要谨慎，因为一般都是出现异常的时候，catch异常再拒绝入列，选择是否重入列。*

          *但是如果使用不当会导致一些每次都被你重入列的消息一直消费-入列-消费-入列这样循环，会导致消息积压。*

   消费者端以上的3个方法都表示消息已经被正确投递，但是`basic.ack`表示消息已经被正确处理。而`basic.nack,basic.reject`表示没有被正确处理

springboot实现：

在消费者中，新建消息监听配置类（MessageListenerConfig）

```java
@Configuration
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private MyAckReceiver myAckReceiver;//消息接收处理类

    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        //container.setPrefetchCount(10); // 单位时间内接收的消息数量
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息

        //设置一个队列
        container.setQueueNames("TestDirectQueue");
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");
		
        // 有些业务场景需要监听的好几个队列都想变成手动确认模式，而且处理的消息业务逻辑不一样。这里可以通过add方法继续添加监听的队列
        container.addQueueNames("fanout.A");

        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));
        container.setMessageListener(myAckReceiver); // 设置消息监听类

        return container;
    }
}
```

手动确认消息监听类（MyAckReceiver，手动确认模式需要实现 ChannelAwareMessageListener）

```java
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 将message自行转换成传递进去的类型
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
```

## 并发

> 电商中秒杀请求，属于瞬间大流量，同一时刻会有大量的请求涌入到系统中，可能导致系统挂掉。应付这种瞬间大流量的其中一种方式，便是利用**消息队列**。
>
> 1. 利用消息队列先进先出的特性，将请求进行削峰；
> 2. 控制好消费端的消费速度，进行必要的限流。

### 一个listener对应多个consumer

默认情况一下，一个`listener`对应一个`consumer`，如果想对应多个，有两种方式。

#### 直接在`application.yml`文件中配置

```yml
spring:
  rabbitmq:
    listener:
      simple:
        concurrency: 5
        max-concurrency: 10
```

这个是个全局配置，应用里的任何队列对应的`listener`都至少有5个`consumer`，但是千万别这么做，因为一般情况下，一个`listener`对应一个`consumer`是够用的。只是针对部分场景，才需要一对多。

#### 直接在@RabbitListener上配置

```java
@Component
public class SpringBootMsqConsumer {
    @RabbitListener(queues = "xxx-queue",concurrency = "5-10")
    public void receive(Message message) {
        System.out.println("receive message:" + new String(message.getBody()));
    }
}
```

利用`@RabbitListener`中的`concurrency`属性进行指定，就表示最小5个，最大10个`consumer`。

## 限流

> 我们经过压测，来判断`consumer`的消费能力，如果单位时间内，`consumer`到达的消息太多，也可能把消费者压垮。
> 得到压测数据后，可以在`@RabbitListener`中配置`prefetch count`。

### PrefetchCount

```java
@Component
public class SpringBootMsqConsumer {
    @RabbitListener(queues = "spring-boot-direct-queue",concurrency = "5-10",containerFactory = "mqConsumerlistenerContainer")
    public void receive(Message message) {
        System.out.println("receive message:" + new String(message.getBody()));
    }
}
```

只需要在`@RabbitListener`中，用`containerFactory`指定一个监听器工厂类就行（containerFactory = "mqConsumerlistenerContainer"）

```java
@Configuration
public class RabbitMqConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean(name = "mqConsumerlistenerContainer")
    public SimpleRabbitListenerContainerFactory mqConsumerlistenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPrefetchCount(50); // consumer单位时间内接收到消息就是50条。
        return factory;
    }
}
```

同样也可以在上面`MessageListenerConfig`里面进行全局配置。

## 重试

```yml
spring:
  rabbitmq:
  ####连接地址
    host: 127.0.0.1
   ####端口号
    port: 5672
   ####账号
    username: guest
   ####密码
    password: guest
   ### 地址
    virtual-host: /
    listener:
        simple:
          retry:
          ####开启消费者重试
            enabled: true
           ####最大重试次数
            max-attempts: 5
          ####重试间隔次数
            initial-interval: 3000
```



## 消费者如果保证消息幂等性，不被重复消费

- 使用全局MessageID判断消费方使用同一个，解决幂等性。

- 或者使用业务逻辑保证唯一（比如订单号码）

## 消息过期时间 TTL(Time To Live)

1. 通过队列属性设置消息过期时间所有队列中的消息超过时间未被消费时，都会过期。

    ```java
    /**
     * 设置过期时间
     */
    @Bean("ttlQueue")
    public Queue ttlQueue(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-message-ttl", 10000);//队列中的消息未被消费10s后过期
        return new Queue("ttlQueue",true,false,false,map);
    }
    ```

2. 设置单条消息的过期时间

   在发送消息的时候指定消息属性。

    ```java
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
    messageProperties.setExpiration("4000"); // 消息的过期属性，单位ms
    messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
    Message message = new Message("这条消息4秒后过期".getBytes(), messageProperties);
    rabbitTemplate.send(RabbitMQConfig.FANOUT_EMAIL_QUEUE,message);
    // 随队列的过期属性过期，单位ms
    rabbitTemplate.convertSendAndReceive(RabbitMQConfig.FANOUT_EMAIL_QUEUE, "消息发送");
    ```

*如果同时指定了 Message TTL 和 Queue TTL，则小的那个时间生效。*




> 参考链接：https://www.cnblogs.com/Jeely/p/12388913.html
>
> 参考链接：https://blog.csdn.net/qq_35387940/article/details/100514134
>
> 参考链接：https://www.jianshu.com/p/090ed51006d5
