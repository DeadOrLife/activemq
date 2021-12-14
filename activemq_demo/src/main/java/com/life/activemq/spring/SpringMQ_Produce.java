package com.life.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/13 10:25 下午
 */
@Service
public class SpringMQ_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Produce springMQ_produce = ac.getBean("springMQ_Produce", SpringMQ_Produce.class);
        springMQ_produce.jmsTemplate.send(session->{
            TextMessage textMessage = session.createTextMessage("***spring和activemq整合");
            return textMessage;
        });
        System.out.printf("****整合消息发生结束");
    }
}
