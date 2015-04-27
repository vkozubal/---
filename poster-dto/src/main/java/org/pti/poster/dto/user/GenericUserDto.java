package org.pti.poster.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.pti.poster.dto.AbstractDtoEntity;
import org.pti.poster.model.user.AbstractUser;
import org.pti.poster.model.user.GenericUserType;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericUserDto extends AbstractUser implements AbstractDtoEntity {

	@Getter
	protected List<String> errorMessages;

	public GenericUserDto() {
		errorMessages = new ArrayList<>();
	}

	@JsonIgnore
	@Override
	public GenericUserType getType() {
		return type;
	}

}
