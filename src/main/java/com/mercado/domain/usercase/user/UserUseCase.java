package com.mercado.domain.usercase.user;

import com.mercado.domain.models.User;
import com.mercado.domain.repository.UserRepositoryI;

public class UserUseCase {
	
	private UserRepositoryI userRepository;

	public UserUseCase(UserRepositoryI userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUserById(long id) {
		return userRepository.getById(id);
	}
}
