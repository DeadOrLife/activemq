package com.life.activemq.queue;

import lombok.Cleanup;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/11 8:16 下午
 */
public class JmsConsumer {

    //activeMQ地址
    private static final String ACTIVEMQ_URL = "tcp://122.112.214.197:61616";
    //queue队列名称
    private static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {

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
        while (true){
            //同步阻塞方式（receive()）
            //订阅者或接收者调用MessageConsumer的receive()方法来接收消息，
            //receive方法在能够接收消息之前（或超时之前）将一直阻塞
            TextMessage textMessage= (TextMessage)consumer.receive();
            //4秒后如果没有消息可以消费，则不在进行消费（过时不候）
            //TextMessage textMessage= (TextMessage)consumer.receive(4000L);
            if (textMessage ==null){
                break;
            }else {
                System.out.println("***消费者接收到了消息："+textMessage.getText());
            }
        }
        //关闭资源
//        producer.close();
//        session.close();
//        connection.close();
        System.out.printf("****消息接收完成");
    }
}
