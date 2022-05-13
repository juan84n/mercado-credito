package com.mercado.domain.usercase.targetRules;

import com.mercado.domain.models.TargetRules;
import com.mercado.domain.repository.TargetRulesRepositoryI;

public class GetTargetRulesUseCase {
	
	private TargetRulesRepositoryI targetRulesRepository;

	public GetTargetRulesUseCase(TargetRulesRepositoryI targetRulesRepository) {
		this.targetRulesRepository = targetRulesRepository;
	}

	public TargetRules getTargetRulesByType(String type) {
		return this.targetRulesRepository.getRulesByType(type);
	}
	
}
