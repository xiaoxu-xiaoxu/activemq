package com.xiaoxu;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class SpringMQConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void test(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application.xml");
        SpringMQConsumer consumer = ioc.getBean(SpringMQConsumer.class);

        String message = (String) consumer.jmsTemplate.receiveAndConvert();

        System.out.println(message);
    }

}
