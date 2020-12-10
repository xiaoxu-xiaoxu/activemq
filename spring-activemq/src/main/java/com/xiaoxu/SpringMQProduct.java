package com.xiaoxu;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQProduct {


    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void test(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("application.xml");

        SpringMQProduct product = (SpringMQProduct) ioc.getBean("springMQProduct");

        product.jmsTemplate.send(session -> session.createTextMessage("spring-mq-11111111"));
    }

}
