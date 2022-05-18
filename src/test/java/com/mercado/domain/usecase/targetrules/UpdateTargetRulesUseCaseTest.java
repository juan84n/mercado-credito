package com.mercado.domain.usecase.targetrules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.models.TargetRules;
import com.mercado.domain.repository.TargetRulesRepositoryI;

@RunWith(MockitoJUnitRunner.class)
public class UpdateTargetRulesUseCaseTest {
	
	@Mock
	TargetRulesRepositoryI targetRulesRepository;
	
	TargetRules targetRules = new TargetRules(0, 100000, 0, 2, "NEW", 0.15, 500000);
	
	UpdateTargetRulesUseCase updateTargetRulesUseCase;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		updateTargetRulesUseCase = new UpdateTargetRulesUseCase(targetRulesRepository);
		
		when(targetRulesRepository.updateRules(Mockito.any(TargetRules.class)))
		.thenAnswer(i -> targetRules);
		
	}
	
	@Test
	public void updateTargetRules() {
		targetRules.setRate(0.20);
		assertEquals(this.updateTargetRulesUseCase.updateTargetRules(targetRules).getRate(), 0.20);
		
	}
	
	@Test
	public void updateTargetRulesFailType() {
		try {
			this.updateTargetRulesUseCase.updateTargetRules(targetRules);
		}
		catch(BusinessException be) {
			assertEquals(be.getMessage(), "No se puede motificar el tipo debe ser (NEW, FREQUENT o PREMIUM)");	
		}	
	}
	
	@Test
	public void updateTargetRulesFailCant() {
		try {
			targetRules.setMinCant(10);
			targetRules.setMaxCant(3);
			this.updateTargetRulesUseCase.updateTargetRules(targetRules);
		}
		catch(BusinessException be) {
			assertEquals(be.getMessage(), "La cantidad máxima no puede ser menor a la mínima");	
		}	
	}
	
	@Test
	public void updateTargetRulesFailAmount() {
		try {
			targetRules.setMinAmount(10);
			targetRules.setMaxAmount(3);
			this.updateTargetRulesUseCase.updateTargetRules(targetRules);
		}
		catch(BusinessException be) {
			assertEquals(be.getMessage(), "El valor máximo no puede ser menor que el mínimo");	
		}	
	}
	
	@Test
	public void updateTargetRulesFailLoan() {
		try {
			targetRules.setMaxLoan(3);
			targetRules.setMaxAmount(10);
			this.updateTargetRulesUseCase.updateTargetRules(targetRules);
		}
		catch(BusinessException be) {
			assertEquals(be.getMessage(), "El valor máximo no puede ser mayor al valor máximo del préstamo");	
		}	
	}
	

}
