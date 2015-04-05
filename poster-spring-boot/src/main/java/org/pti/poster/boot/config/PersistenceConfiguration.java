package org.pti.poster.boot.config;

import org.pti.poster.model.person.Person;
import org.pti.poster.repository.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

@Configuration
@EnableJpaRepositories
public class PersistenceConfiguration {

	@Autowired
	private PersonRepository personRepository;

	@PostConstruct
	private void addPersons() {
		Person john = new Person();
		john.setFirstName("John");
		Person mary = new Person();
		mary.setFirstName("Mary");

		personRepository.save(john);
		personRepository.save(mary);
	}

}
