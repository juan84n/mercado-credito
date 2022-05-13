package com.mercado.infrastructure.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import com.mercado.infrastructure.persistence.entitites.UserEntity;


public interface UserDaoI extends CrudRepository<UserEntity, Long> {

}
