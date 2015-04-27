package org.pti.poster.dto.user;

import org.pti.poster.model.user.GenericUserType;

public class UnregisteredUserDto extends GenericUserDto {

	public UnregisteredUserDto() {
		type = GenericUserType.UNREGISTERED_USER;
		userId = "";
	}
}
