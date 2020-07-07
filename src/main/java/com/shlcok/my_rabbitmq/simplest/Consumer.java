package com.shlcok.my_rabbitmq.simplest;/*
    @author Shlock
    @create date 2020-07-07 21:24    
*/

import com.rabbitmq.client.*;
import com.shlcok.my_rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义队列的消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body = " + new String(body));
//                int i =10/0;
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //  自动ACK 监听队列，第二个参数：是否自动进行消息确认。
            // channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
        //开启手动ACK
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
    }
}
