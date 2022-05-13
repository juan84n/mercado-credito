package com.mercado.infrastructure.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercado.infrastructure.persistence.entitites.TargetRulesEntity;

@Repository
public interface TargetRulesDaoI  extends CrudRepository<TargetRulesEntity, Long> {

}
