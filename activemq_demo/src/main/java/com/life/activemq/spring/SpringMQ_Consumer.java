package com.life.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/13 10:25 下午
 */
@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Consumer springMQ_consumer = ac.getBean("springMQ_Consumer", SpringMQ_Consumer.class);
        String receiveMessage = (String)springMQ_consumer.jmsTemplate.receiveAndConvert();

        System.out.printf("***消费者收到信息："+receiveMessage);
    }
}
