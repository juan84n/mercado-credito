package com.mercado.domain.usecase.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mercado.domain.models.Loan;
import com.mercado.domain.models.Payment;
import com.mercado.domain.models.ResponsePayment;
import com.mercado.domain.repository.LoanRepositoryI;
import com.mercado.domain.repository.PaymentRepositoryI;

@RunWith(MockitoJUnitRunner.class)
public class RegisterPaymentUseCaseTest {
	
	RegisterPaymentUseCase registerPaymentUseCase;
	
	@Mock
	PaymentRepositoryI paymentRepository;
	
	@Mock
	LoanRepositoryI loanRepository;
	
	Payment payment = new Payment();
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		registerPaymentUseCase = new RegisterPaymentUseCase(paymentRepository, loanRepository);
		Loan loan = new Loan();
		
		loan.setAmount(1000);
		loan.setDate(null);
		loan.setId(1);
		loan.setRate(0.15);
		loan.setTarget("NEW");
		loan.setTerm(12);
		loan.setUser_id(1);
		
		payment.setAmount(100);
		payment.setDate(null);
		payment.setId(1);
		payment.setMissingAmount(0);
		payment.setLoan(loan);
		
		ResponsePayment responsePayment = new ResponsePayment();
		
		responsePayment.setDebt(50);
		responsePayment.setId(1);
		responsePayment.setLoan_id(loan.getId());
		
		List<Payment> listPayments = new ArrayList<>();
		
		listPayments.add(payment);
		
		when(loanRepository.getLoanById(1))
        .thenAnswer(i -> loan);
		
		when(paymentRepository.getListPayments(1))
        .thenAnswer(i -> listPayments);
		
		when(paymentRepository.doPayment(Mockito.any(Payment.class), Mockito.any(Double.class)))
        .thenAnswer(i -> responsePayment);

	}
	
	@Test
	public void registerPayment() {
		ResponsePayment rp = this.registerPaymentUseCase.registerPayment(payment);
		assertEquals(rp.getDebt(), 50);
		assertEquals(payment.getMissingAmount(), 0);
		
	}
	
	@Test
	public void registerPaymentWithMissingAmount() {
		payment.setAmount(10);
		this.registerPaymentUseCase.registerPayment(payment);
		assertEquals(payment.getMissingAmount(), 13.8);
		
	}

}
