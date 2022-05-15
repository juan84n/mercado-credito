package com.mercado.domain.repository;

import java.util.List;

import com.mercado.domain.models.TargetRules;

public interface TargetRulesRepositoryI {
	public TargetRules getRulesByType(String type);
	public List<TargetRules> listRules();
	public TargetRules updateRules(TargetRules targetRules);
}
