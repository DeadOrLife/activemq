package com.life.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/12 2:39 下午
 */
public class JmsConsumerListener_Topic {
    //activeMQ地址
    private static final String ACTIVEMQ_URL = "tcp://122.112.214.197:61616";
    //topic队列名称
    private static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {
        //1.创建MQ的连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.创建connection连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建destination Topic
        Topic topic = session.createTopic(TOPIC_NAME);
        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //7.异步阻塞方式 订阅消息
        consumer.setMessageListener(message -> {
            if (message !=null && message instanceof TextMessage){
                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("***消费者接收到Topic消息："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //10关闭资源
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
        System.out.println("****消息接收完成");
    }
}
