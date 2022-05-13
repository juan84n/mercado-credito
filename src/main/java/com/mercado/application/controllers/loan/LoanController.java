package com.mercado.application.controllers.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mercado.domain.models.Loan;
import com.mercado.domain.models.ResponseLoan;
import com.mercado.domain.usercase.loan.RequestLoanUseCase;
import com.mercado.domain.usercase.loan.RetrieveLoansUseCase;

@RestController
public class LoanController {
	
	@Autowired
	RequestLoanUseCase loanUseCase;
	
	@Autowired
	RetrieveLoansUseCase retrieveLoansUseCase;
	
	@PostMapping(value = "/request-loan")
	public ResponseEntity<ResponseLoan> requestLoan(@RequestBody Loan loan) {
		return ResponseEntity.accepted().body(loanUseCase.requestLoan(loan));
	}
	
	@GetMapping(value= "/list-loan")
	public ResponseEntity<List<Loan>> listLoan(@RequestParam(required=false, defaultValue="") String startDate,
			@RequestParam(required=false, defaultValue="") String endDate ) {
		return ResponseEntity.accepted().body(retrieveLoansUseCase.findAllLoan(startDate, endDate));
	}

}
