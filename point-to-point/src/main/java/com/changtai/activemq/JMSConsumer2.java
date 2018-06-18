package com.changtai.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: zhaoct
 * @Date: 2018-06-18 10:53
 * @Description:
 */
public class JMSConsumer2 {

    public static void main(String[] args) {
        {

            ConnectionFactory connectionFactory ; //连接工厂
            Connection connection = null; //连接
            Session session; //会话 接收消息或者发送消息的线程
            Destination destination; //消息的目的地
            MessageConsumer messageConsumer; //消息消费者

            try {
                connectionFactory = new ActiveMQConnectionFactory();
                connection = connectionFactory.createConnection();
                connection.start();
                session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                destination = session.createQueue("FirstQueue");
                messageConsumer = session.createConsumer(destination);
                messageConsumer.setMessageListener((message) -> {
                    try {
                        System.out.println("收到的消息2:" + ((TextMessage)message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                });
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
