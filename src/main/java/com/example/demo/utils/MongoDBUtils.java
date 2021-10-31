package com.example.demo.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;

/**
 * @Author XZQ
 * @Date 2021/10/30 09:34
 **/
public class MongoDBUtils {
    private static String ip="localhost";
    private static int port=27017;

    public static MongoClientOptions getOptions(){
        MongoClient mongoClient = new MongoClient(ip, port);
        MongoClientOptions options = mongoClient.getMongoClientOptions();
        return options;
    }

    public static MongoDatabase getDB(String db){
        MongoClient mongoClient = new MongoClient(ip, port);
        MongoDatabase database = mongoClient.getDatabase(db);
        return database;
    }
}
