package org.pti.poster.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.user.GenericUserType;

import java.util.List;

public class NewUserDto extends GenericUserDto {

	public NewUserDto() {
		type = GenericUserType.UNREGISTERED_USER;
		userId = "";
	}

	@JsonIgnore
	@Override
	public String getUserId() {
		return userId;
	}

	@JsonIgnore
	@Override
	public List<String> getErrorMessages() {
		return super.getErrorMessages();
	}
}
