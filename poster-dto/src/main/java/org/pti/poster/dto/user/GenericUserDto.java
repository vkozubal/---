package org.pti.poster.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.user.AbstractUser;
import org.pti.poster.model.user.GenericUserType;

public abstract class GenericUserDto extends AbstractUser {

	@JsonIgnore
	@Override
	public GenericUserType getType() {
		return type;
	}

}
