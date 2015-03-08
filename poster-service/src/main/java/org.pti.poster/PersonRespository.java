package org.pti.poster;


import org.pti.poster.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRespository {

    private MongoTemplate mongoTemplate;

//    @Autowired
    public PersonRespository() {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Person> getAllPersons() {
        return mongoTemplate.findAll(Person.class);
    }
}
