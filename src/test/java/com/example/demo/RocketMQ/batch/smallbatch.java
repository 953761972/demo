package com.example.demo.RocketMQ.batch;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/9/13 20:25
 **/
public class smallbatch {
    public static void main(String[] args) throws MQClientException {
        //Instantiate with a producer group name.
        MQProducer producer = new DefaultMQProducer("order");
        //Launch the instance.
        producer.start();
        String topic = "BatchTest";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
        try {
            producer.send(messages);
        } catch (Exception e) {
            e.printStackTrace();
            //handle the error
        }
    }
}
