package com.cmz.utils.rabbit;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;  
  
public class Send  
{  
    //队列名称  
    private final static String QUEUE_NAME = "hello";  
  
    public static void main(String[] argv) throws java.io.IOException, TimeoutException  
    {  
        /** 
         * 创建连接连接到MabbitMQ 
         */  
        ConnectionFactory factory = new ConnectionFactory();  
        //设置MabbitMQ所在主机ip或者主机名  
        factory.setHost("192.168.20.162");  
        
        factory.setPort(5672);
        factory.setUsername("cmz");
        factory.setPassword("pwd");
        
        //创建一个连接  
        Connection connection = factory.newConnection();  
        //创建一个频道  
        Channel channel = connection.createChannel();  
        //channel.queueDelete(QUEUE_NAME);
        //指定一个队列  
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);  
        //发送的消息  
        String message = "hello world!";  
        //往队列中发出一条消息  
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());  
        System.out.println(" [x] Sent '" + message + "'");  
        //关闭频道和连接  
        channel.close();  
        connection.close();  
     }  
}  
