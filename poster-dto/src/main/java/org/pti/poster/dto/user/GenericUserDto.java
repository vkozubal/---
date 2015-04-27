package org.pti.poster.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.AbstractUser;
import org.pti.poster.model.user.GenericUserType;

public abstract class GenericUserDto extends AbstractUser {

	@JsonIgnore
	@Override
	public GenericUserType getType() {
		return type;
	}

	@JsonIgnore
	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "#" + userId + ": " + userNickName;
	}

}
