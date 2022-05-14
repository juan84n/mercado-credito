package com.mercado.domain.usecase.payment;

import java.sql.Timestamp;

import com.mercado.domain.models.Loan;
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
		Loan loan = this.loanRepository.getLoanById(loan_id);
		double totalPayment = this.paymentRepository.getTotalPaymentByLoanId(loan_id, date);
		return loan.getAmount() - totalPayment;
	}
}
