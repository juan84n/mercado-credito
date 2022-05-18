package com.mercado.infrastructure.adapters.loan;


import com.mercado.domain.models.Loan;
import com.mercado.infrastructure.persistence.entitites.LoanEntity;

public class LoanTransformer {
	
	public static LoanEntity loanToLoanEntity(Loan loan) {
		LoanEntity loanEntity = new LoanEntity();
		loanEntity.setId(loan.getId());
		loanEntity.setAmount(loan.getAmount());
		loanEntity.setDate(loan.getDate());
		loanEntity.setRate(loan.getRate());
		loanEntity.setTarget(loan.getTarget());
		loanEntity.setTerm(loan.getTerm());
		loanEntity.setUser_id(loan.getUser_id());
		
		return loanEntity;
	}

	public static Loan loanEntityToLoan(LoanEntity loanEntity) {
		
		Loan loan = new Loan();
		loan.setId(loanEntity.getId());
		loan.setAmount(loanEntity.getAmount());
		loan.setDate(loanEntity.getDate());
		loan.setRate(loanEntity.getRate());
		loan.setTarget(loanEntity.getTarget());
		loan.setTerm(loanEntity.getTerm());
		loan.setUser_id(loanEntity.getUser_id());
		
		return loan;
	}
}
