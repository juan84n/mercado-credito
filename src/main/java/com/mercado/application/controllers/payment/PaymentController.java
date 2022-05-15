package com.mercado.application.controllers.payment;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.domain.models.Payment;
import com.mercado.domain.models.ResponsePayment;
import com.mercado.domain.usecase.payment.GetBalanceUseCase;
import com.mercado.domain.usecase.payment.RegisterPaymentUseCase;
import com.mercado.infrastructure.shared.Utils;

@RestController
public class PaymentController {

	@Autowired
	private RegisterPaymentUseCase registerPaymentUseCase;
	
	@Autowired
	private GetBalanceUseCase getBalanceUseCase;
	
	
	@PostMapping(value="/register-payment")
	public ResponseEntity<ResponsePayment> registerPayment(@RequestBody Payment payment) {
		return ResponseEntity.accepted().body(registerPaymentUseCase.registerPayment(payment));
	}
	
	@GetMapping(value="/loan-balance", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> loanBalance(@RequestParam long loan_id, @RequestParam String endDate ){
	    Timestamp timestamp = Utils.stringToTimestamp(endDate);
	    double balance = this.getBalanceUseCase.getBalanceByLoanId(loan_id, timestamp);
		return ResponseEntity.accepted().body("{ \"balance\":\""+ balance +"\"}");
		
	}
	
	@GetMapping(value="/all-loan-balance", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> listLoansBalance(@RequestParam(required=false) String target,
			@RequestParam(required=false) String endDate ){
		
		Timestamp timestamp = null;
		if(endDate != null && !endDate.equals("")) {
			timestamp = Utils.stringToTimestamp(endDate);
		}
	    double balance = this.getBalanceUseCase.getBalanceByTargetOrDate(target, timestamp);
		return ResponseEntity.accepted().body("{ \"balance\":\""+ balance +"\"}");
	}
	
	
}
