package com.changtai.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: zhaoct
 * @Date: 2018-06-18 10:12
 * @Description:
 */
public class JMSProducer {

    public static void main(String[] args) {

        ConnectionFactory connectionFactory ; //连接工厂
        Connection connection = null; //连接
        Session session; //会话 接收消息或者发送消息的线程
        Destination destination; //消息的目的地
        MessageProducer messageProducer; //消息生产者

        try {
            connectionFactory = new ActiveMQConnectionFactory();
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            messageProducer = session.createProducer(destination);

            //发送消息
            for(int i=0; i<10; i++){
                TextMessage message = session.createTextMessage("发送消息:" + i);
                System.out.println("发送消息:" + i);
                messageProducer.send(message);
            }

            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
