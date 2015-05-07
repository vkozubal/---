package org.pti.poster.service.user;

import org.pti.poster.dto.user.GenericUserDto;

public interface UserService {

	GenericUserDto findUserById(String id);

	GenericUserDto createUser(GenericUserDto userDto);
}
