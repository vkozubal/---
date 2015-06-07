package org.pti.poster.dao.repository;


import org.junit.Test;
import org.pti.poster.dao.basic.AbstractEmbeddedMongoDBTest;
import org.pti.poster.dao.basic.TestData;
import org.pti.poster.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class PersonCRUDTest extends AbstractEmbeddedMongoDBTest<Person> {

    @Autowired PersonRepository personRepository;

    @Test
    public void baseCrudOperationsTest() {
        int count = 100;
        personRepository.save(TestData.mockedUsers(count));
        assertEquals(personRepository.count(), count);
    }
}
