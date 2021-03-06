package com.xiaoxu;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class JmsConsumer {

    public static final String MQ_URL = "tcp://localhost:61616";

    public static final String QUEUE_NAME = "myQueue";

    @Test
    public void test(){
        System.out.println("消费者2号");
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;

        try {
            // 1.创建消息连接工厂
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
            // 2.开启连接
            connection = activeMQConnectionFactory.createConnection();
            connection.start();
            // 3.创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 4.指明目的地
            Queue queue = session.createQueue(QUEUE_NAME);
            // 5.创建消费者
            consumer = session.createConsumer(queue);
            // 6.接收消息
            while(true){
                TextMessage receive = (TextMessage) consumer.receive();
                if(receive != null){
                    System.out.println("接收到的消息是：" + receive.getText());
                    continue;
                }
                break;
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            // 7.关闭资源
            if(consumer != null) {
                try {
                    consumer.close();
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








