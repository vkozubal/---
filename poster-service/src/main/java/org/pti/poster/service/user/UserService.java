package org.pti.poster.service.user;

import org.pti.poster.assembler.GenericUserAssembler;
import org.pti.poster.dto.user.GenericUserDto;
import org.pti.poster.model.user.GenericUser;
import org.pti.poster.repository.user.UserRepository;
import org.pti.poster.repository.user.UserRepositoryFactory;
import org.pti.poster.repository.user.UserRepositoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepositoryFactory userRepositoryFactory;
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		userRepository = userRepositoryFactory.getRepositoryOfType(UserRepositoryType.INMEMORY);
	}

	public GenericUserDto findUserById(String id) {
		GenericUser queryResult = userRepository.getUserById(id);
		return GenericUserAssembler.toDto(queryResult);
	}

	public GenericUserDto createUser(GenericUserDto userDto) {
		GenericUser user = GenericUserAssembler.fromDto(userDto);
		GenericUser queryResult = userRepository.saveUser(user);
		return GenericUserAssembler.toDto(queryResult);
	}
}
