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

import com.mercado.domain.exceptions.BusinessException;
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
	Loan loan = new Loan();
	List<Payment> listPayments = new ArrayList<>();
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		registerPaymentUseCase = new RegisterPaymentUseCase(paymentRepository, loanRepository);
		
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

	@Test
	public void registerPaymentAlreadyPayed() {
		payment.setAmount(900);
		payment.setDate(null);
		payment.setId(1);
		payment.setMissingAmount(0);
		payment.setLoan(loan);
		try {
			this.registerPaymentUseCase.registerPayment(payment);
		}
		catch(BusinessException bx) {
			assertEquals(bx.getMessage(), "La deuda ya fue pagada");
		}
		
	}
	
	@Test
	public void registerPaymentPayOverLoan() {
		Payment payment2 = new Payment();
		payment2.setAmount(2000);
		payment2.setDate(null);
		payment2.setId(1);
		payment2.setMissingAmount(0);
		payment2.setLoan(loan);
		try {
			this.registerPaymentUseCase.registerPayment(payment2);
		}
		catch(BusinessException bx) {
			assertEquals(bx.getMessage(), "Este pago sobrepasa el monto de la deuda");
		}
		
	}
}
