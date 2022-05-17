package com.mercado.domain.usecase.loan;

import java.sql.Timestamp;
import java.util.List;

import com.mercado.domain.models.Loan;
import com.mercado.domain.repository.LoanRepositoryI;

public class RetrieveLoansUseCase {
	
	private LoanRepositoryI loanRepository;
	
	
	
	public RetrieveLoansUseCase(LoanRepositoryI loanRepository) {
		this.loanRepository = loanRepository;
	}

	public List<Loan> findAllLoan(Timestamp startDate, Timestamp endDate){
		return loanRepository.listLoan(startDate, endDate);
	}
}
