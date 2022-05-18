package com.mercado.domain.usecase.targetrules;

import com.mercado.domain.models.TargetRules;
import com.mercado.domain.repository.TargetRulesRepositoryI;

/**
 * @author juanfelipenarvaez
 * 
 * Caso de uso para obtener las reglas
 *
 */
public class GetTargetRulesUseCase {
	
	private TargetRulesRepositoryI targetRulesRepository;

	public GetTargetRulesUseCase(TargetRulesRepositoryI targetRulesRepository) {
		this.targetRulesRepository = targetRulesRepository;
	}

	/**
	 * 
	 * Método para obtener acceder a los repositorios y obtener las reglas
	 * 
	 * @param type
	 * @return
	 */
	public TargetRules getTargetRulesByType(String type) {
		return this.targetRulesRepository.getRulesByType(type);
	}
	
}
