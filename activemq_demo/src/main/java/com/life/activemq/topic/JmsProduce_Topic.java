package com.life.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/12 2:39 下午
 */
public class JmsProduce_Topic {
    //activeMQ地址
    private static final String ACTIVEMQ_URL = "tcp://122.112.214.197:61616";
    //topic队列名称
    private static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        //1.创建MQ的连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.创建connection连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建session
        Session session = connection.
                createSession(false, Session.AUTO_ACKNOWLEDGE);
        session.rollback();
        //5.创建destination Topic
        Topic topic = session.createTopic(TOPIC_NAME);
        //6.创建消息生产者
        MessageProducer producer = session.createProducer(topic);
//        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //7.通过MessageProducer生产消息发送到MQ主题里面
        for (int i = 0; i < 3; i++) {
            //8.创建消息
            TextMessage textMessage = session.createTextMessage("TOPIC_NAME---" + i);
            //9.通过MessageProducer发生消息
            producer.send(textMessage);
            textMessage.acknowledge();
        }
        //10关闭资源
        producer.close();
        session.close();
        connection.close();
        System.out.printf("******TOPIC_NAME消息发布到MQ完成");
    }
}
