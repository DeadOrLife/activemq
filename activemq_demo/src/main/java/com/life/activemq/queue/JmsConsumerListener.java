package com.life.activemq.queue;

import lombok.Cleanup;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/11 8:16 下午
 */
public class JmsConsumerListener {

    //activeMQ地址
    private static final String ACTIVEMQ_URL = "tcp://122.112.214.197:61616";
    //queue队列名称
    private static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException, IOException {

        //1.创建activeMq连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.创建连接并启动
        @Cleanup Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3.创建session
        @Cleanup Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建队列
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.创建消费者
        @Cleanup MessageConsumer consumer = session.createConsumer(queue);

        //监听的方式消费消息
        //异步非阻塞方式（监听器onMessage()）
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message != null && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("***消费者接收到消息"+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        //关闭资源
//        producer.close();
//        session.close();
//        connection.close();
        System.out.println("****消息接收完成");
    }
}
