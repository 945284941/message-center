package com.example.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Send {

     public static boolean sendMessage(String userId, String message,String type){

         Connection connection= null;
         Channel channel = null;
         try {
                 connection = ConnectionUtil.getConnection();
              channel = connection.createChannel();

             channel.exchangeDeclare("xiaoxi", type, true,true,null);
              //发送
             channel.basicPublish("jiaohuanji", userId, null, message.getBytes());

             return true;
         } catch (IOException e) {
             e.printStackTrace();
         }
         finally {
             if(channel != null){
                 try {
                     channel.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(connection != null){
                 try {
                     connection.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }

         return false;

     }


}
