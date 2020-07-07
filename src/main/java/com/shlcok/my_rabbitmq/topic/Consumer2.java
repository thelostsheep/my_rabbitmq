package com.shlcok.my_rabbitmq.topic;/*
    @author Shlock
    @create date 2020-07-07 21:24    
*/

import com.rabbitmq.client.*;
import com.shlcok.my_rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {
    private final static String QUEUE_NAME = "topic_exchange_queue_1";
    private final static String EXCHANGE_NAME = "topic_exchange_test";


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //将队列绑定交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "*.insert");
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body = " + new String(body));
            }
        };
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
    }
}
