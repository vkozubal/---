package org.pti.poster.dto.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.AbstractUser;
import org.pti.poster.model.user.GenericUserType;

@JsonFilter("postFilter")
public abstract class GenericUserDto extends AbstractUser {

	public GenericUserDto(GenericUserType type, String userId, String userNickName, String userName) {
		super(type, userId, userNickName, userName);
	}

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
