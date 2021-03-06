package com.mercado.application.controllers.loan;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.application.shared.Utils;
import com.mercado.domain.models.Loan;
import com.mercado.domain.models.ResponseLoan;
import com.mercado.domain.usecase.loan.RequestLoanUseCase;
import com.mercado.domain.usecase.loan.RetrieveLoansUseCase;

/**
 * @author juanfelipenarvaez
 * 
 * Controlador encargado de recibir las 
 * peticiones para los prestamos
 *
 */
@RestController
public class LoanController {
	
	@Autowired
	RequestLoanUseCase loanUseCase;
	
	@Autowired
	RetrieveLoansUseCase retrieveLoansUseCase;
	
	/**
	 * 
	 * Petición de préstamo
	 * 
	 * @param loan
	 * @return
	 */
	@PostMapping(value = "/request-loan")
	public ResponseEntity<ResponseLoan> requestLoan(@RequestBody Loan loan) {
		return ResponseEntity.accepted().body(loanUseCase.requestLoan(loan));
	}
	
	/**
	 * 
	 * Obtener los préstamos ya sea filtrado por fechas o no
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GetMapping(value= "/list-loan")
	public ResponseEntity<List<Loan>> listLoan(@RequestParam(required=false, defaultValue="") String startDate,
			@RequestParam(required=false, defaultValue="") String endDate ) {
		
		Timestamp startDateTime = null;
		Timestamp endDateTime = null;
		
		if(!startDate.equals("") || !endDate.equals("")) {
			startDateTime = Utils.stringToTimestamp(startDate);
			endDateTime = Utils.stringToTimestamp(endDate);
		}
		return ResponseEntity.accepted().body(retrieveLoansUseCase.findAllLoan(startDateTime, endDateTime));
	}

}
