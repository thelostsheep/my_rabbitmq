package com.shlcok.my_rabbitmq.work;/*
    @author Shlock
    @create date 2020-07-07 21:23    
*/

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shlcok.my_rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String QUEUE_NAME = "work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i=0;i<50;i++){
            String msg = "Hello RabbitMQ!! num=" + i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }
        channel.close();
        connection.close();
    }
}
