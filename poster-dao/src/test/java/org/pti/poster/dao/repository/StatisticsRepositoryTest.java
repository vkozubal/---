package org.pti.poster.dao.repository;

import com.mongodb.DBObject;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pti.poster.dao.TestData;
import org.pti.poster.dao.config.MongoConfiguration;
import org.pti.poster.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(classes = {MongoConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticsRepositoryTest extends TestCase {

    @Autowired IStatisticsRepository statisticsRepository;
    @Autowired PersonRepository personRepository;
    @Autowired MongoOperations mongoOps;

    @Before
    public void testGetStatByRoles() throws Exception {
        if (mongoOps.collectionExists(Person.class)) {
            mongoOps.dropCollection(Person.class);
        }
        personRepository.save(TestData.mockedUsers(100));
    }

    @Test /* useless test */
    public void testMapReduceFunction() {
        Iterable<DBObject> statByRoles = statisticsRepository.getStatByRoles();
        for (DBObject dbObject : statByRoles) {
            assertNotNull(dbObject);
        }
    }
}