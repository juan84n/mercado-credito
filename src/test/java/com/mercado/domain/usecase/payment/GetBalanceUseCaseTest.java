package com.mercado.domain.usecase.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mercado.application.shared.Utils;
import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.Balance;
import com.mercado.domain.models.Loan;
import com.mercado.domain.repository.PaymentRepositoryI;

@RunWith(MockitoJUnitRunner.class)
public class GetBalanceUseCaseTest {
	
	GetBalanceUseCase getBalanceUseCase;
	
	@Mock
	PaymentRepositoryI paymentRepository;
	
	Balance balance = new Balance();
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		getBalanceUseCase = new GetBalanceUseCase(paymentRepository);
		
		Loan loan = new Loan();
		Timestamp time = Utils.stringToTimestamp("2022-05-17");
		loan.setAmount(25000);
		loan.setDate(time);
		loan.setId(1);
		loan.setRate(0.15);
		loan.setTarget("NEW");
		loan.setTerm(12);
		loan.setUser_id(1);
		
		balance.setLoan(loan);
		balance.setPayments(20000.0);
		
		List<Balance> listBalance = new ArrayList<>();
		
		listBalance.add(balance);
		
		when(paymentRepository.getPaymentsByLoanId(Mockito.anyLong(), Mockito.any(Timestamp.class)))
		.thenAnswer(i -> balance);
		
		when(paymentRepository.getBalanceByTargetOrDate(nullable(String.class), nullable(Timestamp.class)))
		.thenAnswer(i -> listBalance);

	}
	
	@Test
	public void getBalanceByLoanId() {
		Timestamp time = Utils.stringToTimestamp("2022-05-17");
		assertEquals(this.getBalanceUseCase.getBalanceByLoanId(1, time), 5000);
		
	}
	
	@Test
	public void getBalanceByLoanIdFail() {
		Timestamp time = Utils.stringToTimestamp("2022-05-17");
		Timestamp timeOver = Utils.stringToTimestamp("2022-05-18");
		balance.getLoan().setDate(timeOver);
		try {
			this.getBalanceUseCase.getBalanceByLoanId(1, time);
		}
		catch(BusinessException be) {
			assertEquals(be.getCode(), Status.BAD_REQUEST.getCode());
		}
		
	}
	
	@Test
	public void getBalanceByTarget() {
		assertEquals(this.getBalanceUseCase.getBalanceByTargetOrDate("NEW", null), 5000);
		
	}
	
	@Test
	public void getBalanceByDate() {
		Timestamp time = Utils.stringToTimestamp("2022-05-17");
		assertEquals(this.getBalanceUseCase.getBalanceByTargetOrDate(null, time), 5000);
		
	}
	
	@Test
	public void getBalanceByDateAndTarget() {
		Timestamp time = Utils.stringToTimestamp("2022-05-17");
		assertEquals(this.getBalanceUseCase.getBalanceByTargetOrDate("NEW", time), 5000);
		
	}
	
	@Test
	public void getBalanceByDateAndTargetFail() {
		try {
			this.getBalanceUseCase.getBalanceByTargetOrDate(null, null);
		}
		catch(BusinessException be) {
			assertEquals(be.getCode(), Status.BAD_REQUEST.getCode());	
		}
		
	}

}
