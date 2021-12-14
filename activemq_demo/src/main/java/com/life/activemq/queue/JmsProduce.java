package com.life.activemq.queue;

import lombok.Cleanup;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 创建消息
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/3 7:57 下午
 */
public class JmsProduce {

    //activeMQ地址
    private static final String ACTIVEMQ_URL = "tcp://122.112.214.197:61616";
    //queue队列名称
    private static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        //1.创建连接工厂，给按照给定的url地址，采用默认的用户名与密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过连接工厂，获取connection并启动访问
        @Cleanup Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3.创建回话session
        //两个参数，第一个叫事务，第二个叫签收
        @Cleanup Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地（具体是队列还是主题）
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.创建消息的生产者
        @Cleanup MessageProducer producer = session.createProducer(queue);
        //6.通过使用messageProducer 生产3条消息到MQ队列里面
        for (int i = 0; i < 3; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            //8.通过messageProducer发生msg
            producer.send(textMessage);
        }
        //关闭资源
//        producer.close();
//        session.close();
//        connection.close();
        System.out.printf("****消息发布到MQ完成");
    }
}
