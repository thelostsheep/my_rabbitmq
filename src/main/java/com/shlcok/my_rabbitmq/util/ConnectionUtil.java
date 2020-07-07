package com.shlcok.my_rabbitmq.util;/*
    @author Shlock
    @create date 2020-07-07 19:51    
*/

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接工厂类
        ConnectionFactory factory = new ConnectionFactory();
        //配置连接参数
        factory.setHost("192.168.159.128");
        factory.setPort(5672);
        factory.setVirtualHost("/shopping");
        factory.setUsername("admin");
        factory.setPassword("123456");
        //通过连接工厂类创建连接
        return factory.newConnection();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        System.out.println("connection = " + connection);
        connection.close();
    }

}
