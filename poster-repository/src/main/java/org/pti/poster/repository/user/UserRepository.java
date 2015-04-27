package org.pti.poster.repository.user;


import org.pti.poster.model.user.GenericUser;

public interface UserRepository {

	public GenericUser getUserById(String id);

	public GenericUser saveUser(GenericUser user);

}
