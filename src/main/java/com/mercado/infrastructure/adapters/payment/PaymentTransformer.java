package com.mercado.infrastructure.adapters.payment;

import com.mercado.domain.models.Payment;
import com.mercado.infrastructure.adapters.loan.LoanTransformer;
import com.mercado.infrastructure.persistence.entitites.PaymentEntity;

public class PaymentTransformer {

	public static Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
		Payment payment = new Payment();
		payment.setAmount(paymentEntity.getAmount());
		payment.setId(paymentEntity.getId());
		payment.setLoan(LoanTransformer.loanEntityToLoan(paymentEntity.getLoan()));
		payment.setDate(paymentEntity.getDate());
		payment.setMissingAmount(paymentEntity.getMissingAmount());
		return payment;
		
	}
	
	public static PaymentEntity paymentToPaymentEntity(Payment payment) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setAmount(payment.getAmount());
		paymentEntity.setId(payment.getId());
		paymentEntity.setLoan(LoanTransformer.loanToLoanEntity(payment.getLoan()));
		paymentEntity.setDate(payment.getDate());
		paymentEntity.setMissingAmount(payment.getMissingAmount());
		return paymentEntity;
		
	}
}
