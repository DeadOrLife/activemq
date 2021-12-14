package com.life.activemq.spring;

import com.sun.tools.jdi.resources.jdi;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Component;
import sun.management.resources.agent;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/13 11:00 下午
 */
@Component
public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message != null && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                System.out.printf("监听器收到消息："+textMessage);
                System.out.printf("监听器收到消息："+textMessage.getText());

//                ****整合消息发生结束监听器收到消息：ActiveMQTextMessage
//                {commandId = 5, responseRequired = true, messageId = ID:BeanWorlds-MacBook-Pro.local-57193-1639408256490-1:2:1:1:1, originalDestination = null, originalTransactionId = null, producerId = ID:BeanWorlds-MacBook-Pro.local-57193-1639408256490-1:2:1:1, destination = queue://spring-active-queue, transactionId = null, expiration = 0, timestamp = 1639408256881, arrival = 0, brokerInTime = 1639408257073, brokerOutTime = 1639408257077, correlationId = null, replyTo = null, persistent = true, type = null, priority = 4, groupID = null, groupSequence = 0, targetConsumerId = null, compressed = false, userID = null, content = org.apache.activemq.util.ByteSequence@490c9abb, marshalledProperties = null, dataStructure = null, redeliveryCounter = 0, size = 0, properties = null, readOnlyProperties = true, readOnlyBody = true, droppable = false, jmsXGroupFirstForConsumer = false, text = ***spring和activemq整合}
            }
        }catch (Exception e){

        }
    }
}
