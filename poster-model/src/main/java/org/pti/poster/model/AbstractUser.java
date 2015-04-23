package org.pti.poster.model;

import lombok.Data;
import org.pti.poster.model.user.GenericUserType;

@Data
public abstract class AbstractUser {

	protected GenericUserType type;

	protected String userId;

	protected String userNickName;

	protected String userName;

	public AbstractUser(GenericUserType type, String userId, String userNickName, String userName) {
		this.type = type;
		this.userId = userId;
		this.userNickName = userNickName;
		this.userName = userName;
	}

}
