package com.mercado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mercado.domain.repository.LoanRepositoryI;
import com.mercado.domain.repository.PaymentRepositoryI;
import com.mercado.domain.repository.TargetRulesRepositoryI;
import com.mercado.domain.repository.UserRepositoryI;
import com.mercado.domain.usecase.loan.RequestLoanUseCase;
import com.mercado.domain.usecase.loan.RetrieveLoansUseCase;
import com.mercado.domain.usecase.payment.GetBalanceUseCase;
import com.mercado.domain.usecase.payment.RegisterPaymentUseCase;
import com.mercado.domain.usecase.targetRules.GetTargetRulesUseCase;
import com.mercado.domain.usecase.targetRules.UpdateTargetRulesUseCase;

@Configuration
public class AppConfig {
	
	@Autowired
	LoanRepositoryI loanRepository;
	
	@Autowired
	UserRepositoryI userRepository;
	
	@Autowired
	TargetRulesRepositoryI targetRulesRepository;
	
	@Autowired
	PaymentRepositoryI paymentRepository;
	
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
    
    @Bean
    public UpdateTargetRulesUseCase updateTargetRulesUseCase() {
        return new UpdateTargetRulesUseCase(targetRulesRepository);
    }
    
    @Bean
    public RegisterPaymentUseCase registerPaymentUseCase() {
        return new RegisterPaymentUseCase(paymentRepository, loanRepository);
    }
    
    @Bean
    public GetBalanceUseCase getBalanceUseCase() {
        return new GetBalanceUseCase(paymentRepository, loanRepository);
    }
    
}
