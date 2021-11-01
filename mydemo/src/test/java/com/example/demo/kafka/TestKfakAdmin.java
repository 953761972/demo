package com.example.demo.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Author XZQ
 * @Date 2021/9/4 00:50
 **/
public class TestKfakAdmin {
    //删除不掉
    public static void main(String[] args) {
        delete(1020l);
        update("test",0,4);
    }
    public static void delete(long beforeOffset ){
        // 1.创建kafkaAdminClient
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        AdminClient kafkaAdminClient = KafkaAdminClient.create(properties);

        Map<TopicPartition, RecordsToDelete> recordsToDelete = new HashMap<>();
        String topic = "test";
        Integer partition = 0;
        TopicPartition topicPartition = new TopicPartition(topic, partition);
        RecordsToDelete recordsToDelete1 = RecordsToDelete.beforeOffset(beforeOffset);
        recordsToDelete.put(topicPartition,recordsToDelete1);
        // 3.执行删除
        DeleteRecordsResult result = kafkaAdminClient.deleteRecords(recordsToDelete);
        Map<TopicPartition, KafkaFuture<DeletedRecords>> lowWatermarks = result.lowWatermarks();
        try {
            for (Map.Entry<TopicPartition, KafkaFuture<DeletedRecords>> entry : lowWatermarks.entrySet()) {
                System.out.println(entry.getKey().topic() + " " + entry.getKey().partition() + " " + entry.getValue().get().lowWatermark());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        kafkaAdminClient.close();
    }

    public static void update(String topic,int partition,long offset){
        //修改offset
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "GROUPID1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", 1);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topic));
        Set<TopicPartition> assignment = new HashSet<>();

        while(assignment.size()<=0){
            consumer.poll(Duration.ofSeconds(1));
            assignment=consumer.assignment();
        }
        for(TopicPartition set:assignment){
            consumer.seek(set,offset);
            System.out.println("分区 " + set + " 从 " + offset + " 开始消费");
        }
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            // 消费记录
            System.out.println("开始消费");
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.offset() + ":" + record.value() + ":" + record.partition());
                System.exit(0);
            }
        }
    }
}
