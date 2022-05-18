package com.mercado.application.controllers.targetrules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.domain.models.TargetRules;
import com.mercado.domain.usecase.targetrules.UpdateTargetRulesUseCase;

/**
 * @author juanfelipenarvaez
 *
 *	Controlador para actualizr las reglas de un target
 */
@RestController
public class TargetRulesController {
	
	@Autowired
	UpdateTargetRulesUseCase updateTargetRulesUseCase;
	
	
	/**
	 * 
	 * Endpoint para actualizar las reglas de un target
	 * 
	 * @param targetRules
	 * @return
	 */
	@PutMapping(value="/update-target-rules")
	public ResponseEntity<TargetRules> updateTargetRules(@RequestBody TargetRules targetRules){
		return ResponseEntity.accepted().body(this.updateTargetRulesUseCase.updateTargetRules(targetRules));
	}
}
