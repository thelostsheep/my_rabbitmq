package com.shlcok.my_rabbitmq.simplest;/*
    @author Shlock
    @create date 2020-07-07 21:23    
*/

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.shlcok.my_rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //通过连接获取隧道
        Channel channel = connection.createChannel();
        //通过隧道创建一个队列
        //使用方法
            // queueDeclare(queue,durable,exclusive,autoDelete,arguments)
            //queue：队列名
            //durable：是否持久化
            //exclusive：创建的队列是否只给当前队列使用
            //autoDelete：是否启动自动删除
            //arguments：参数队列的其他参数( 构造参数 )
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //创建发送的消息
        String msg = "Hello RabbitMQ!!";
        //exchange：发送到哪一个路由器
        //队列名
        //消息的其他属性
        //消息
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        //关闭资源
        channel.close();
        connection.close();
    }
}
