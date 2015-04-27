package org.pti.poster.model.user;

import org.pti.poster.model.AbstractUser;

public class GenericUser extends AbstractUser {

	public GenericUser(GenericUserType type, String userId, String userNickName, String userName) {
		super(type, userId, userNickName, userName);
	}


}
