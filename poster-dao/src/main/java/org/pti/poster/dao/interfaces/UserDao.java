package org.pti.poster.dao.interfaces;

import org.pti.poster.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDao extends Dao<Person, Long>, UserDetailsService {

    Person findByName(String name);

}