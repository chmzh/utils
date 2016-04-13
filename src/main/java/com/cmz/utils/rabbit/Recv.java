package com.cmz.utils.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;  
  
public class Recv  
{  
    //队列名称  
    private final static String QUEUE_NAME = "hello";  
  
    public static void main(String[] argv) throws java.io.IOException,  
            java.lang.InterruptedException, TimeoutException  
    {  
        //打开连接和创建频道，与发送端一样  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.20.162");  
        
        factory.setPort(5672);
        factory.setUsername("cmz");
        factory.setPassword("pwd");
        
        Connection connection = factory.newConnection();  
        final Channel channel = connection.createChannel();  
        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。  
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);  
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");  
          
        channel.basicQos(1);
        //创建队列消费者  
        //QueueingConsumer consumer = new QueueingConsumer(channel);  
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                throws IOException {
              String message = new String(body, "UTF-8");
              System.out.println(" [x] Received '" + message + "'");
              channel.basicAck(envelope.getDeliveryTag(), false);
            }
          };
          channel.basicConsume(QUEUE_NAME, true, consumer);
  
    }  
} 
