package com.mercado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mercado.domain.repository.LoanRepositoryI;
import com.mercado.domain.repository.TargetRulesRepositoryI;
import com.mercado.domain.repository.UserRepositoryI;
import com.mercado.domain.usercase.loan.RequestLoanUseCase;
import com.mercado.domain.usercase.loan.RetrieveLoansUseCase;
import com.mercado.domain.usercase.targetRules.GetTargetRulesUseCase;

@Configuration
public class AppConfig {
	
	@Autowired
	LoanRepositoryI loanRepository;
	
	@Autowired
	UserRepositoryI userRepository;
	
	@Autowired
	TargetRulesRepositoryI targetRulesRepository;
	
    @Bean
    public RequestLoanUseCase loanUseCase() {
        return new RequestLoanUseCase(loanRepository, userRepository, targetRulesRepository);
    }
    
    @Bean
    public RetrieveLoansUseCase retrieveloanUseCase() {
        return new RetrieveLoansUseCase(loanRepository);
    }
    
    @Bean
    public GetTargetRulesUseCase getTargetRulesUseCase() {
        return new GetTargetRulesUseCase(targetRulesRepository);
    }
}
