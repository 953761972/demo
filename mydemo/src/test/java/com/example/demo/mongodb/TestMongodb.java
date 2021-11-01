package com.example.demo.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonNull;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/**
 * @Author XZQ
 * @Date 2021/9/3 01:00
 **/
public class TestMongodb {
    public static void main(String[] args) {
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            System.out.println("Connect to database successfully");
            //mongoDatabase.createCollection("test");
            //System.out.println("集合创建成功");
            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            Document d = collection.findOneAndDelete(new Bson() {
                @Override
                public <TDocument> BsonDocument toBsonDocument(Class<TDocument> aClass, CodecRegistry codecRegistry) {
                    BsonDocument b = new BsonDocument();
                    b.put("aaa", new BsonArray().set(22, new BsonArray()));
                    return b;
                }
            });
            System.out.println(d.toJson());
            Bson b=new Bson() {
                @Override
                public <TDocument> BsonDocument toBsonDocument(Class<TDocument> aClass, CodecRegistry codecRegistry) {
                    BsonDocument b= new BsonDocument();
                    b.put("aa",new BsonArray().set(22,new BsonArray()));
                    return b;
                }
            };
            System.out.println(collection.createIndex(b));
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
