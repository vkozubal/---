package org.pti.poster.service.impl;

import com.google.common.collect.Lists;
import org.pti.poster.dao.repository.PersonRepository;
import org.pti.poster.model.Person;
import org.pti.poster.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired private PersonRepository personRepository;

    @Override
    public BigInteger addUser(final Person person) {
        return personRepository.save(person).getId();
    }

    @Override
    public Person loadUserByUsername(final String username) {
        return personRepository.findByName(username);
    }

    @Override
    public void remove(final BigInteger id) {
        personRepository.delete(id);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void save(List<Person> persons) {
        for (Person person : persons) {
            save(person);
        }
    }

    @Override
    public List<Person> findAll() {
        return Lists.newArrayList(personRepository.findAll());
    }
}
