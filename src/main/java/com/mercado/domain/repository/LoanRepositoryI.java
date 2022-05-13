package com.mercado.domain.repository;

import java.util.List;

import com.mercado.domain.models.Loan;
import com.mercado.domain.models.ResponseDebt;

public interface LoanRepositoryI {
	public Loan requestLoan(Loan loan);
	public List<Loan> listLoan(String startDate, String endDate);
	public ResponseDebt getDebt(String endDate, long loan_id);
	public List<Loan> getLoansByUser(long user_id);
}
