package org.pti.poster.service;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.apache.commons.lang.RandomStringUtils;
import org.pti.poster.model.Constants;
import org.pti.poster.model.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Service
public class PersonService  implements UserDetailsService {

    private Collection<Person> users = new ArrayList<>();

    public Long addUser(final Person person) {
        person.setId(Long.valueOf(RandomStringUtils.randomNumeric(8)));
        users.add(person);
        return person.getId();

    }

    public void remove(final Long id) {
        users = Collections2.filter(users, new Predicate<Person>() {
            @Override
            public boolean apply(Person person) {
                return person.getId().equals(id);
            }
        });
    }

    public UserDetails loadUserByUsername(final String username) {

        Person person = new Person("user", "password");
        person.setRoles(new HashSet<Constants.SECURITY>() {{
            add(Constants.SECURITY.USER_ROLE);
        }});
        
        return person;
//        return Iterables.find(users, new Predicate<Person>() {
//            @Override
//            public boolean apply(Person person) {
//                return person.getUsername().equals(username);
//            }
//        });
    }
}
