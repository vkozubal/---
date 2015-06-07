package org.pti.poster.service.interfaces;

import org.pti.poster.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigInteger;
import java.util.List;

public interface IPersonService  extends UserDetailsService{

    BigInteger addUser(final Person person);

    void remove(final BigInteger id);
    
    Person save(Person person);

    void save(List<Person> person);

    List<Person> findAll();
}
