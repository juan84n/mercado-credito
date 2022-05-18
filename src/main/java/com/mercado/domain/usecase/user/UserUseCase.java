package com.mercado.domain.usecase.user;

import com.mercado.domain.models.User;
import com.mercado.domain.repository.UserRepositoryI;

/**
 * @author juanfelipenarvaez
 * 
 * Caso de uso para obtener usuarios
 *
 */
public class UserUseCase {
	
	private UserRepositoryI userRepository;

	public UserUseCase(UserRepositoryI userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * 
	 * Método que accede a través del repositorio un usuario por id
	 * @param id
	 * @return
	 */
	public User getUserById(long id) {
		return userRepository.getById(id);
	}
}
