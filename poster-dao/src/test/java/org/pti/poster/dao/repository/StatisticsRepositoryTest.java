package org.pti.poster.dao.repository;

import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;
import org.pti.poster.dao.AbstractEmbeddedMongoDBTest;
import org.pti.poster.dao.basic.TestData;
import org.pti.poster.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import static junit.framework.TestCase.assertNotNull;

public class StatisticsRepositoryTest extends AbstractEmbeddedMongoDBTest<Person> {

    @Autowired IStatisticsRepository statisticsRepository;
    @Autowired PersonRepository personRepository;
    @Autowired MongoOperations mongoOps;

    @Before
    public void testGetStatByRoles() throws Exception {
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