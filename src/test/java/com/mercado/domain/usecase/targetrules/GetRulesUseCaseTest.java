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


import com.mercado.domain.models.TargetRules;
import com.mercado.domain.repository.TargetRulesRepositoryI;


@RunWith(MockitoJUnitRunner.class)
public class GetRulesUseCaseTest {
	
	@Mock
	TargetRulesRepositoryI targetRulesRepository;
	
	TargetRules targetRules = new TargetRules(0, 100000, 0, 2, "NEW", 0.15, 500000);
	
	GetTargetRulesUseCase getTargetRulesUseCase;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		getTargetRulesUseCase = new GetTargetRulesUseCase(targetRulesRepository);
		
		when(targetRulesRepository.getRulesByType(Mockito.anyString()))
		.thenAnswer(i -> targetRules);
		
	}
	
	@Test
	public void getTargetRules() {
		assertEquals(this.getTargetRulesUseCase.getTargetRulesByType("NEW").getRate(), 0.15);
		
	}

}
