package com.shlcok.my_rabbitmq.direct;/*
    @author Shlock
    @create date 2020-07-07 21:23    
*/

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shlcok.my_rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String msg = "Hello Direct insert!!";
        channel.basicPublish(EXCHANGE_NAME,"insert",null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
