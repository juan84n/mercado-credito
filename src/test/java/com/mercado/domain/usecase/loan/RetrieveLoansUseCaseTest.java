package com.mercado.domain.usecase.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import com.mercado.domain.models.Loan;

import com.mercado.domain.repository.LoanRepositoryI;


@RunWith(MockitoJUnitRunner.class)
public class RetrieveLoansUseCaseTest {
	
	RetrieveLoansUseCase retrieveLoansUseCase;
	
	@Mock
	LoanRepositoryI loanRepository;
		
	Loan loan = new Loan();
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		retrieveLoansUseCase = new RetrieveLoansUseCase(loanRepository);
		loan.setAmount(1000);
		loan.setDate(null);
		loan.setId(1);
		loan.setRate(0.15);
		loan.setTarget("NEW");
		loan.setTerm(12);
		loan.setUser_id(1);
				
		List<Loan> listLoan = new ArrayList<>();
				
		listLoan.add(loan);
		
		when(loanRepository.listLoan(null, null))
        .thenAnswer(i -> listLoan);
		
	}
	
	@Test
	public void requestLoan() {
		assertEquals(this.retrieveLoansUseCase.findAllLoan(null, null).get(0).getAmount(), 1000);
		
	}
}
