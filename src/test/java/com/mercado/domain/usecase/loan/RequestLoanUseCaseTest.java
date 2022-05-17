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
import com.mercado.domain.models.TargetRules;
import com.mercado.domain.models.User;
import com.mercado.domain.repository.LoanRepositoryI;
import com.mercado.domain.repository.TargetRulesRepositoryI;
import com.mercado.domain.repository.UserRepositoryI;

//@SpringBootTest
//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class RequestLoanUseCaseTest {
	
	RequestLoanUseCase requestLoanUseCase;
	
	@Mock
	LoanRepositoryI loanRepository;
	
	@Mock
	UserRepositoryI userRepository;
	
	@Mock
	TargetRulesRepositoryI targetRulesRepository;
	
	Loan loan = new Loan();
	User user = new User();
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		requestLoanUseCase = new RequestLoanUseCase(loanRepository, userRepository, targetRulesRepository);
		loan.setAmount(1000);
		loan.setDate(null);
		loan.setId(1);
		loan.setRate(0.15);
		loan.setTarget("NEW");
		loan.setTerm(12);
		loan.setUser_id(1);
		
		user.setId(1);
		user.setName("prueba");
		user.setTarget("NEW");
		
		List<Loan> listLoan = new ArrayList<>();
				
		listLoan.add(loan);
		
		when(requestLoanUseCase.getLoanRepository().requestLoan(loan))
        .thenAnswer(i -> loan);
		
		when(requestLoanUseCase.getUserRepository().getById(1))
        .thenAnswer(i -> user);
		
		when(requestLoanUseCase.getLoanRepository().getLoansByUser(1, null))
        .thenAnswer(i -> listLoan);
		
		when(requestLoanUseCase.getTargetRulesRepository().listRules())
        .thenAnswer(i -> initRules());
	}
	
	@Test
	public void requestLoan() {
		assertEquals(this.requestLoanUseCase.requestLoan(loan).getInstallment(), 22,0);
		
	}
	
	private List<TargetRules> initRules() {
		TargetRules tr1 = new TargetRules(0, 100000, 0, 2, "NEW", 0.15, 500000);
		TargetRules tr2 = new TargetRules(100000, 500000, 2, 5, "FREQUENT", 0.10, 1000000);
		TargetRules tr3 = new TargetRules(500000, -1, 5, -1, "PREMIUM", 0.05, 5000000);
		List<TargetRules> listTR = new ArrayList<>();
		listTR.add(tr1);
		listTR.add(tr2);
		listTR.add(tr3);
		
		return listTR;
	}
}
