package com.mercado.infrastructure.adapters.user;

import com.mercado.domain.models.User;
import com.mercado.infrastructure.persistence.entitites.UserEntity;

public class UserTransformer {
	
	
	public static UserEntity userToUserEntity(User user) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setName(user.getName());
		userEntity.setTarget(user.getTarget());
		
		return userEntity;
	}
	
	public static User userEntityToUser(UserEntity userEntity) {
		
		User user = new User();
		user.setId(userEntity.getId());
		user.setName(userEntity.getName());
		user.setTarget(userEntity.getTarget());
		
		return user;
	}
}
