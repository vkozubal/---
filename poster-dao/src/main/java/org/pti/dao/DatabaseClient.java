package org.pti.dao;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseClient {

    private static MongoClient mongoClient;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public DatabaseClient(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
//    localhost:49153
//    @PostCons

    private void init() {
        mongoClient = new MongoClient("localhost", 27017);
        MongoClient mongo = new MongoClient("localhost", 27017);
        List<String> dbs = mongo.getDatabaseNames();
        System.out.println(dbs); // [journaldev, local, admin]
        MongoCollection<Document> mongoCollection = mongo.getDatabase("test").getCollection("users");
    }

/**
 * using the sharded mongodb cluster
 * MongoClient mongoClient = new MongoClient(Arrays.asList(
 * new ServerAddress("localhost", 27017),
 * new ServerAddress("localhost", 27018),
 * new ServerAddress("localhost", 27019)));
 * */
}
