package org.pti.poster.dao;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.ParameterizedType;

/**
 * uses test @{link TestMongoConfiguration} instead of {@link MongoConfiguration}
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestMongoConfiguration.class })
@ActiveProfiles(profiles = {"test"})
public abstract class AbstractEmbeddedMongoDBTest<T>{

    @Autowired protected MongoOperations mongoOps;

    private final Class collectionType;

    @SuppressWarnings("unchecked")
    public AbstractEmbeddedMongoDBTest() {
        this.collectionType = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }
    
    @Before
    public void cleanDatabase() {
        if (mongoOps.collectionExists(collectionType)) {
            mongoOps.dropCollection(collectionType);
        }
    }
}
