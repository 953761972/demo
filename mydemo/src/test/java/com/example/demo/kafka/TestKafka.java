package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author XZQ
 * @Date 2021/9/3 23:37
 **/
public class TestKafka {

    public static void main(String[] args) {
        String topic="test";
        Integer Partition=0;
        long offset=0l;
        Producer(topic,Partition);
        consumer("testid",topic,Partition,offset);
    }
    public static void Producer(String topic,Integer partition){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        String key="111";
        String value="222";
        Runnable r=new Runnable() {

            @Override
            public void run() {
                System.out.println("生产者启动。。。");
                try {
                    Future<RecordMetadata> fu=null;
                    for(int i=0;i<100;i++){
                        //fu= producer.send(new ProducerRecord<String, String>(topic,partition,key,value+i));
                        fu= producer.send(new ProducerRecord<String, String>(topic,key,value+i));
                        System.out.println(Thread.currentThread().getName()+":分区【"+fu.get().partition()+"】发送成功,序号：("+i+")："+fu.get().toString());
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t=new Thread(r);
        t.setName("生产者");
        t.start();
    }
    public static void consumer(String groupid,String topic,Integer partition,long offset){
        Properties props = new Properties();
        //TopicPartition p = new TopicPartition(topic, partition);//声明只消费分区号为partition的分区
        TopicPartition p = new TopicPartition(topic, partition);//声明只消费分区号为partition的分区
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", groupid);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", 1);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(topic));
        //consumer.assign(Arrays.asList(p));
        //订阅之后，我们再从kafka中拉取数据:

        Set<TopicPartition> assignment = new HashSet<>();

        while(assignment.size()<=0){
            consumer.poll(Duration.ofSeconds(1));
            assignment=consumer.assignment();
        }
        for(TopicPartition set:assignment){
            consumer.seek(set,offset);
            System.out.println("GroupID:"+groupid+",topic:"+ topic+",分区 :" + set + " 从 " + offset + " 开始消费");
        }

        Runnable r=new Runnable() {
            int i=0;
            @Override
            public void run() {
                System.out.println("消费者启动开始接收数据。。。");
                for(;;){
                    ConsumerRecords<String, String> msgList=consumer.poll(Duration.ofSeconds(1));
                    msgList.records(topic).iterator();
                    if(null!=msgList&&msgList.count()>0){
                        for (ConsumerRecord<String, String> record : msgList) {
                            System.out.println(Thread.currentThread().getName()+":序号："+i+++"=======接收成功: key = " + record.key() + ", value = " + record.value()+" offset==="+record.offset());
                        }
                    }

                }
            }
        };
        Thread t=new Thread(r);
        t.setName("消费者");
        t.start();
    }
}
