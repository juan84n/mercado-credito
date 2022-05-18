package com.mercado.infrastructure.adapters.targetRules;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.domain.models.TargetRules;
import com.mercado.domain.repository.TargetRulesRepositoryI;
import com.mercado.infrastructure.persistence.dao.TargetRulesDaoI;
import com.mercado.infrastructure.persistence.entitites.TargetRulesEntity;

/**
 * @author juanfelipenarvaez
 * 
 * Adaptador que implementa el repositorio del dominio de target rules
 *
 */
@Service
public class TargetRulesAdapter implements TargetRulesRepositoryI {
	
	@Autowired
	TargetRulesDaoI targetRulesDao;

	/**
	 * 
	 * Implementación de repo para obtener las reglas por tipo
	 *@Param 
	 *@return 
	 *
	 */
	@Override
	public TargetRules getRulesByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * Implementación de repo para obtener todas las reglas
	 *@Param 
	 *@return 
	 *
	 */
	@Override
	public List<TargetRules> listRules() {
		return StreamSupport.stream(this.targetRulesDao.findAll().spliterator(), false)
				.map(TargetRulesTransformer::TargetRulesEntityToTargetRules)
			    .collect(Collectors.toList());
				
	}

	/**
	 * 
	 * Implementación de repo para actualizar las reglas
	 *@Param 
	 *@return 
	 *
	 */
	@Override
	public TargetRules updateRules(TargetRules targetRules) {
		
		TargetRulesEntity targetRulesEntity = this.targetRulesDao.findByType(targetRules.getType());
		targetRulesEntity.setMaxAmount(targetRules.getMaxAmount());
		targetRulesEntity.setMaxCant(targetRules.getMaxCant());
		targetRulesEntity.setMaxLoan(targetRules.getMaxLoan());
		targetRulesEntity.setMinAmount(targetRules.getMaxAmount());
		targetRulesEntity.setMinCant(targetRules.getMinCant());
		targetRulesEntity.setRate(targetRules.getRate());
		
		return TargetRulesTransformer.TargetRulesEntityToTargetRules(targetRulesDao.save(targetRulesEntity));
	}

}
