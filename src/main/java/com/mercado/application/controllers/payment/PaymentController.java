package com.mercado.application.controllers.payment;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.Payment;
import com.mercado.domain.models.ResponsePayment;
import com.mercado.domain.usecase.payment.GetBalanceUseCase;
import com.mercado.domain.usecase.payment.RegisterPaymentUseCase;

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
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    endDate += " 23:59:59";
	    Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(endDate);
		} catch (ParseException e) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "El formato de la fecha es incorrecto");
		}
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    double balance = this.getBalanceUseCase.getBalanceByLoanId(loan_id, timestamp);
		return ResponseEntity.accepted().body("{ \"balance\":\""+balance+"\"}");
		
	}
}
