package org.pti.poster.assembler;

import org.pti.poster.dto.user.GenericUserDto;
import org.pti.poster.dto.user.RegisteredUserDto;
import org.pti.poster.dto.user.UnregisteredUserDto;
import org.pti.poster.model.AbstractUser;
import org.pti.poster.model.user.GenericUser;

import javax.inject.Singleton;

@Singleton
public class GenericUserAssembler extends AbstractAssembler {

	public static GenericUser fromDto(GenericUserDto userDto) {
		return convertFromDto(userDto);
	}

	public static GenericUserDto toDto(GenericUser user) {
		GenericUserDto result = null;

		try {
			result = convertToDto(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private static GenericUserDto convertToDto(GenericUser user) throws Exception {
		String className;

		switch (user.getType()) {
			case REGISTERED_USER:
				className = RegisteredUserDto.class.getName();
				break;
			case UNREGISTERED_USER:
				className = UnregisteredUserDto.class.getName();
				break;
			default:
				className = "";
				break;
		}

		GenericUserDto userDto = (GenericUserDto) getNewInstanceFor(className);
		copyFieldsFromTo(user, userDto);

		return userDto;
	}

	private static GenericUser convertFromDto(GenericUserDto userDto) {
		GenericUser user = new GenericUser();
		copyFieldsFromTo(userDto, user);

		return user;
	}

	private static void copyFieldsFromTo(AbstractUser from, AbstractUser to) {
		to.setType(from.getType());
		to.setUserId(from.getUserId());
		to.setUserName(from.getUserName());
		to.setUserNickName(from.getUserNickName());
	}

}
