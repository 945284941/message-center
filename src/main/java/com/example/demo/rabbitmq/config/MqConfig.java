package com.example.demo.rabbitmq.config;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 读取RabbitMQ配置文件
 */
@Slf4j
@Configuration
public class MqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;




    public Connection getConn() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        try {
            factory.setHost(host);
            factory.setPort(port);
            factory.setUsername(username);
            factory.setPassword(password);
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取MQ连接失败...");
        }
        return connection;
    }
}
