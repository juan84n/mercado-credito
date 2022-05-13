package com.mercado.infrastructure.adapters.targetRules;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.domain.models.TargetRules;
import com.mercado.domain.repository.TargetRulesRepositoryI;
import com.mercado.infrastructure.persistence.dao.TargetRulesDaoI;

@Service
public class TargetRulesAdapter implements TargetRulesRepositoryI {
	
	@Autowired
	TargetRulesDaoI targetRulesDao;

	@Override
	public TargetRules getRulesByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TargetRules> listRules() {
		return StreamSupport.stream(this.targetRulesDao.findAll().spliterator(), false)
				.map(TargetRulesTransformer::TargetRulesEntityToTargetRules)
			    .collect(Collectors.toList());
				
	}

}
