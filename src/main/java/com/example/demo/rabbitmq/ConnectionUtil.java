package com.example.demo.rabbitmq;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

public class ConnectionUtil {


    @Value("${spring.rabbitmq.host}")
    private static String host;

    @Value("${spring.rabbitmq.port}")
    private static int port;

    @Value("${spring.rabbitmq.username}")
    private static  String username;

    @Value("${spring.rabbitmq.password}")
    private static String password;
    public static Connection getConnection(){

        //创建连接工厂

        ConnectionFactory connectionFactory=new ConnectionFactory();

        //设置参数

        connectionFactory.setHost(host);//主机ip

        connectionFactory.setPort(port);//虚拟主机名

        connectionFactory.setUsername(username);//账号

        connectionFactory.setPassword(password);//密码

        //创建连接
        Connection newConnection = null;
        try {
             newConnection = connectionFactory.newConnection();

        }catch (Exception e){
           e.printStackTrace();
        }
        return newConnection;

    }
}
