package org.pti.poster.repository.user;

import org.pti.poster.model.user.GenericUser;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Repository("inMemoryUserRepository")
public class InMemoryUserRepository implements UserRepository {

	public final static UserRepositoryType TYPE = UserRepositoryType.INMEMORY;
	Map<String, GenericUser> allUsers;

	@PostConstruct
	public void init() {
		allUsers = new LinkedHashMap<>();
	}

	@Override
	public GenericUser getUserById(String id) {
		return allUsers.get(id);
	}

	@Override
	public GenericUser saveUser(GenericUser user) {
		GenericUser savedUser = new GenericUser();
		String id = UUID.randomUUID().toString();


		savedUser.setType(user.getType());
		savedUser.setUserId(id);
		savedUser.setUserNickName(user.getUserNickName());
		savedUser.setUserName(user.getUserName());

		allUsers.put(id, savedUser);
		return savedUser;
	}
}
