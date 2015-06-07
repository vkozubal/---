package org.pti.poster.dao.basic;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.pti.poster.dao.MongoConfiguration;
import org.pti.poster.dao.config.UnitTestMongoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.ParameterizedType;

/**
 * test mongo configuration instead of {@link MongoConfiguration} *
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UnitTestMongoConfiguration.class })
public abstract class AbstractEmbeddedMongoDBTest<T>{


    private final Class collectionType;

    @SuppressWarnings("unchecked")
    public AbstractEmbeddedMongoDBTest() {
        this.collectionType = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }
    
    @Autowired protected MongoOperations mongoOps;

    @Before
    public void cleanDatabase() {
        if (mongoOps.collectionExists(collectionType)) {
            mongoOps.dropCollection(collectionType);
        }
    }
}
