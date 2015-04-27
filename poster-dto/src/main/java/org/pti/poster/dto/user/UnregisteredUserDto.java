package org.pti.poster.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.user.GenericUserType;

public class UnregisteredUserDto extends GenericUserDto {

	public UnregisteredUserDto() {
		type = GenericUserType.UNREGISTERED_USER;
		userId = "";
	}

	@JsonIgnore
	@Override
	public String getUserId() {
		return userId;
	}
}
