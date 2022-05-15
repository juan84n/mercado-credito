package com.mercado.domain.usecase.payment;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.Balance;
import com.mercado.domain.repository.LoanRepositoryI;
import com.mercado.domain.repository.PaymentRepositoryI;

public class GetBalanceUseCase {

	private PaymentRepositoryI paymentRepository;
	private LoanRepositoryI loanRepository;
	
	
	public GetBalanceUseCase(PaymentRepositoryI paymentRepository, LoanRepositoryI loanRepository) {
		this.paymentRepository = paymentRepository;
		this.loanRepository = loanRepository;
	}
	
	public double getBalanceByLoanId(long loan_id, Timestamp date) {
		Balance balance = this.paymentRepository.getPaymentsByLoanId(loan_id, date);
		Date dateFormat  = null;
		try {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
					.parse(balance.getLoan().getDate());
		} catch (ParseException e) {
			throw new BusinessException(Status.INTERNAL_SERVER_ERROR.getCode(), "Problema al transformar las fechas");
		}  
		if(dateFormat.getTime() > date.getTime()) {
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
