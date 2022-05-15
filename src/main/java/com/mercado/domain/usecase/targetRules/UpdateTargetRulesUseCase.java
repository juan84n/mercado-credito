package com.mercado.domain.usecase.targetRules;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.TargetRules;
import com.mercado.domain.models.UserEnum;
import com.mercado.domain.repository.TargetRulesRepositoryI;

public class UpdateTargetRulesUseCase {
	private TargetRulesRepositoryI targetRulesRepository;

	public UpdateTargetRulesUseCase(TargetRulesRepositoryI targetRulesRepository) {
		this.targetRulesRepository = targetRulesRepository;
	}
	
	public TargetRules updateTargetRules(TargetRules targetRules) {
		if(!targetRules.getType().equals(UserEnum.NEW.getTarget()) &&
				!targetRules.getType().equals(UserEnum.FREQUENT.getTarget()) && 
				!targetRules.getType().equals(UserEnum.PREMIUM.getTarget())) {
			
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "No se puede motificar el typo debe ser (NEW, FREQUENT o PREMIUM)");
		}
		
		if((targetRules.getMaxAmount() < targetRules.getMinAmount())) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "El valor máximo no puede ser menor que el mínimo");
		}
		
		if((targetRules.getMaxCant() < targetRules.getMinCant())) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "La cantidad máxima no puede ser menor a la mínima");
		}
		
		if((targetRules.getMaxAmount() > targetRules.getMaxLoan())) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "El valor máximo no puede ser mayor al valor máximo del préstamo");
		}
		
		return this.targetRulesRepository.updateRules(targetRules);
	}

}
