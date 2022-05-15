package com.mercado.application.controllers.targetRules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.domain.models.TargetRules;
import com.mercado.domain.usecase.targetRules.UpdateTargetRulesUseCase;

@RestController
public class TargetRulesController {
	
	@Autowired
	UpdateTargetRulesUseCase updateTargetRulesUseCase;
	
	
	@PutMapping(value="/update-target-rules")
	public ResponseEntity<TargetRules> updateTargetRules(@RequestBody TargetRules targetRules){
		return ResponseEntity.accepted().body(this.updateTargetRulesUseCase.updateTargetRules(targetRules));
	}
}
