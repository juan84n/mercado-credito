package com.mercado.domain.repository;

import com.mercado.domain.models.User;

public interface UserRepositoryI {
	public User getById(long id);
}
