package com.mercado.infrastructure.adapters.targetRules;

import com.mercado.domain.models.TargetRules;
import com.mercado.infrastructure.persistence.entitites.TargetRulesEntity;

public class TargetRulesTransformer {
	
	public static TargetRules TargetRulesEntityToTargetRules(TargetRulesEntity targetRulesEntity) {
		
		TargetRules targetRules = new TargetRules();
		targetRules.setMaxAmount(targetRulesEntity.getMaxAmount());
		targetRules.setMaxCant(targetRulesEntity.getMaxCant());
		targetRules.setMaxLoan(targetRulesEntity.getMaxLoan());
		targetRules.setMinAmount(targetRulesEntity.getMinAmount());
		targetRules.setMinCant(targetRulesEntity.getMinCant());
		targetRules.setRate(targetRulesEntity.getRate());
		targetRules.setType(targetRulesEntity.getType());
		
		return targetRules;
	}
}
