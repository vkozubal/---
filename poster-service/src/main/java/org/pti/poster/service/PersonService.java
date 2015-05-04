package org.pti.poster.service;

import org.pti.poster.dao.repository.PersonRepository;
import org.pti.poster.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class PersonService implements UserDetailsService, IPersonService {

    @Autowired private PersonRepository personRepository;

    public BigInteger addUser(final Person person) {
        return personRepository.save(person).getId();
    }

    public UserDetails loadUserByUsername(final String username) {
        return personRepository.findByName(username);
    }

    public void remove(final BigInteger id) {
        personRepository.delete(id);
    }
}
