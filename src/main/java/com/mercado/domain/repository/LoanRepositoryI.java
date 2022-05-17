package com.mercado.domain.repository;

import java.sql.Timestamp;
import java.util.List;

import com.mercado.domain.models.Loan;

public interface LoanRepositoryI {
	public Loan requestLoan(Loan loan);
	public List<Loan> listLoan(Timestamp startDate, Timestamp endDate);
	public List<Loan> getLoansByUser(long user_id, Timestamp startDate);
	public Loan getLoanById(long loan_id);
}
