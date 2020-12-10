package com.xiaoxu;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class JmsProductTopic {

    public static final String MQ_URL = "tcp://localhost:61616";

    @Test
    public void test(){

        ActiveMQConnectionFactory activeMQConnectionFactory;
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            // 1.创建mq连接工厂
            activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
            // 2.开启连接
            connection = activeMQConnectionFactory.createConnection();
            connection.start();
            // 3.创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 4.创建目的地
            Topic topic = session.createTopic("myTopic");
            // 5.创建消息生产者
            producer = session.createProducer(topic);

            // 6.推送消息
            for(int i = 0; i < 3; i++){
                TextMessage textMessage = session.createTextMessage("msg---->" + i);
                producer.send(textMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 7.关闭资源
            if(producer != null) {
                try {
                    producer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
