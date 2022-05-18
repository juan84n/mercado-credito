package com.mercado.infrastructure.adapters.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.domain.models.User;
import com.mercado.domain.repository.UserRepositoryI;
import com.mercado.infrastructure.persistence.dao.UserDaoI;
import com.mercado.infrastructure.persistence.entitites.UserEntity;


/**
 * @author juanfelipenarvaez
 * 
 * Adaptador que implementa el repositorio del dominio de usuarios
 *
 */
@Service
public class UserAdapter implements UserRepositoryI {
	
	@Autowired
	private UserDaoI userDao;
	
	/**
	 * 
	 * Implementación de repo para obtener un usuario por id
	 *@Param loan
	 *@return loan
	 *
	 */
	@Override
	public User getById(long id) {
		return UserTransformer.userEntityToUser(userDao.findById(id).orElse(new UserEntity()));
	}

}
