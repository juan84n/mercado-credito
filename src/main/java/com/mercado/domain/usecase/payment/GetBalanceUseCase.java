package com.mercado.domain.usecase.payment;

import java.sql.Timestamp;
import java.util.List;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.Balance;
import com.mercado.domain.repository.PaymentRepositoryI;

public class GetBalanceUseCase {

	private PaymentRepositoryI paymentRepository;

	
	public GetBalanceUseCase(PaymentRepositoryI paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	public double getBalanceByLoanId(long loan_id, Timestamp date) {
		Balance balance = this.paymentRepository.getPaymentsByLoanId(loan_id, date);

		if(balance.getLoan().getDate().getTime() > date.getTime()) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "En esta fecha el préstamo no estaba activo");
		}
		
		double totalPayment = balance.getPayments();
		return balance.getLoan().getAmount() - totalPayment;
	}
	
	public double getBalanceByTargetOrDate(String target, Timestamp date) {
		if(target != null && target.equals("") && date == null) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "Debe enviar alguno de los valores");
		}
		List<Balance> balance = this.paymentRepository.getBalanceByTargetOrDate(target, date);
		
		double totalPayment = balance.stream().mapToDouble(bal -> bal.getPayments()).reduce(0, (a, b) -> a + b);
		double totalAmount = balance.stream().mapToDouble(bal -> bal.getLoan().getAmount()).reduce(0, (a, b) -> a + b);
		return totalAmount - totalPayment;
	}
}
