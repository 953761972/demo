package com.example.demo.repository;

import com.example.demo.utils.MongoDBUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

/**
 * @Author XZQ
 * @Date 2021/10/30 09:24
 **/
@Repository
public class MongoRepository{

    public String save(String db, String collection, MultipartFile file) throws IOException {
        MongoDatabase database=MongoDBUtils.getDB(db);
        MongoCollection<Document> col= database.getCollection(collection);
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102))
                .append("file",file.getBytes());
        col.insertOne(doc);
        return doc.get("_id").toString();
    }

    public String get(String db,String collection,String _id){
        MongoDatabase database=MongoDBUtils.getDB(db);
        MongoCollection<Document> col= database.getCollection(collection);
        ObjectId id=new ObjectId(_id);
        BasicDBObject obj=new BasicDBObject("_id",id);
        Binary b=(Binary)col.find(obj).first().get("file");
        String content= Base64.getEncoder().encodeToString(b.getData());
        System.out.println(content);
        return content;
    }
}
