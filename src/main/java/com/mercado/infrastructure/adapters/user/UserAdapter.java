package com.mercado.infrastructure.adapters.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.domain.models.User;
import com.mercado.domain.repository.UserRepositoryI;
import com.mercado.infrastructure.persistence.dao.UserDaoI;
import com.mercado.infrastructure.persistence.entitites.UserEntity;

@Service
public class UserAdapter implements UserRepositoryI {
	
	@Autowired
	private UserDaoI userDao;
	
	@Override
	public User getById(long id) {
		return UserTransformer.userEntityToUser(userDao.findById(id).orElse(new UserEntity()));
	}

}
