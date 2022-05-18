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

import com.mercado.application.shared.Utils;
import com.mercado.domain.models.Payment;
import com.mercado.domain.models.ResponsePayment;
import com.mercado.domain.usecase.payment.GetBalanceUseCase;
import com.mercado.domain.usecase.payment.RegisterPaymentUseCase;

/**
 * @author juanfelipenarvaez
 *
 * Controlador que recibe las peticiones de los pagos
 *
 */
@RestController
public class PaymentController {

	@Autowired
	private RegisterPaymentUseCase registerPaymentUseCase;
	
	@Autowired
	private GetBalanceUseCase getBalanceUseCase;
	
	
	/**
	 * 
	 * Endpoint que recibe un pago
	 * 
	 * @param payment
	 * @return
	 */
	@PostMapping(value="/register-payment")
	public ResponseEntity<ResponsePayment> registerPayment(@RequestBody Payment payment) {
		return ResponseEntity.accepted().body(registerPaymentUseCase.registerPayment(payment));
	}
	
	/**
	 * 
	 * Endpoint que devuelve todos los pagos de un préstamo
	 * 
	 * @param loan_id
	 * @param endDate
	 * @return
	 */
	@GetMapping(value="/loan-balance", produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> loanBalance(@RequestParam long loan_id, @RequestParam String endDate ){
	    Timestamp timestamp = Utils.stringToTimestamp(endDate);
	    double balance = this.getBalanceUseCase.getBalanceByLoanId(loan_id, timestamp);
		return ResponseEntity.accepted().body("{ \"balance\":\""+ balance +"\"}");
		
	}
	
	/**
	 * 
	 * Endpoint que devuelve todos los pagos filtrados por fecha o sin filtrar
	 * 
	 * @param target
	 * @param endDate
	 * @return
	 */
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
