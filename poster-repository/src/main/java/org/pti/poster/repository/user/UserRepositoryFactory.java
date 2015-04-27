package org.pti.poster.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userRepositoryFactory")
public class UserRepositoryFactory {

	@Autowired
	private InMemoryUserRepository inMemoryRepo;

	public InMemoryUserRepository getRepositoryOfType(UserRepositoryType type) {
		if (UserRepositoryType.INMEMORY.equals(type)) {
			return inMemoryRepo;
		}
		return null;
	}
}
