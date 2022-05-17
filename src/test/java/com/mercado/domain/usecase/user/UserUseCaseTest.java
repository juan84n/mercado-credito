package com.mercado.domain.usecase.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mercado.domain.models.User;
import com.mercado.domain.repository.UserRepositoryI;

@RunWith(MockitoJUnitRunner.class)
public class UserUseCaseTest {
	
	@Mock
	UserRepositoryI userRepository;
	
	UserUseCase userUseCase;
	
	User user = new User();
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		userUseCase = new UserUseCase(userRepository);
		
		user.setId(1);
		user.setName("prueba");
		user.setTarget("NEW");
		
		when(userRepository.getById(Mockito.anyLong()))
		.thenAnswer(i -> user);
		
	}
	
	@Test
	public void getUserById() {
		assertEquals(this.userUseCase.getUserById(1).getName(), "prueba");
		
	}

}
