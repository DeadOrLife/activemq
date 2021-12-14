package com.life.activemq.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @DESCRIPTION new class
 * @AUTHOR: jamesbean
 * @DATE: 2021/12/12 9:03 下午
 */
public class EmbedBroker {


    public static void main(String[] args) throws Exception {
        //ActiveMQ也支持在vm中通信基于嵌入式的broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }

}
