package com.mercado.domain.usecase.payment;

import java.util.Calendar;
import java.util.List;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.Loan;
import com.mercado.domain.models.Payment;
import com.mercado.domain.models.ResponsePayment;
import com.mercado.domain.repository.LoanRepositoryI;
import com.mercado.domain.repository.PaymentRepositoryI;
import com.mercado.domain.shared.Utils;

public class RegisterPaymentUseCase {
	
	private PaymentRepositoryI paymentRepository;
	private LoanRepositoryI loanRepository;

	public RegisterPaymentUseCase(PaymentRepositoryI paymentRepository, LoanRepositoryI loanRepository) {
		this.paymentRepository = paymentRepository;
		this.loanRepository = loanRepository;
	}
	
	public ResponsePayment registerPayment(Payment payment) {
		Loan loan = this.loanRepository.getLoanById(payment.getLoan().getId());
		List<Payment> payments = this.paymentRepository.getListPayments(payment.getLoan().getId());
		double totalAmount = payments.stream().mapToDouble(paymentMap -> paymentMap.getAmount())
				.reduce(0, (a, b) -> a + b);
		
		if(totalAmount >= loan.getAmount()) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "La deuda ya fue pagada");
		}
				
		payments.sort((a, b) -> {
			return b.getDate().compareTo(a.getDate());
		});
		
		Payment lastPayment = (payments.size() > 0) ? payments.get(0) : null;
		Calendar cal = Calendar.getInstance(); 
		payment.setDate(new java.sql.Timestamp(cal.getTimeInMillis()));
		
		double installment = Utils.getLoanByFormule(loan);
		double totalPayed = totalAmount + this.checkPayment(payment, installment, lastPayment, loan);;
		double debt = loan.getAmount() - totalPayed;
		
		if(payment.getAmount() >= loan.getAmount() || debt > loan.getAmount()) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "Este pago sobrepasa el monto de la deuda");
		}
		
		payment.setLoan(loan);
		
		return this.paymentRepository.doPayment(payment, debt);
	}
	
	private double checkPayment(Payment payment, double installment, Payment lastPayment, Loan loan) {
		
		if(lastPayment != null && lastPayment.getMissingAmount() > 0) {
			installment += lastPayment.getMissingAmount();
		}
		
		if(payment.getAmount() < installment) {
			double missingAmount = (installment - payment.getAmount());
			double amountKeep = (missingAmount * loan.getRate()) + missingAmount;
			payment.setMissingAmount(amountKeep);
		}
		
		return payment.getAmount();
	}
	

}
