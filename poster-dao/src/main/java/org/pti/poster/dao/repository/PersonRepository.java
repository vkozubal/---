package org.pti.poster.dao.repository;

import org.pti.poster.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface PersonRepository extends CrudRepository<Person, BigInteger> {

    Person findByName(String name);
}
