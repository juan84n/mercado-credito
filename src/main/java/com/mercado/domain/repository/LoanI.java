package com.mercado.domain.repository;

import java.text.SimpleDateFormat;
import java.util.List;

import com.mercado.domain.models.Loan;
import com.mercado.domain.models.ReponseLoan;
import com.mercado.domain.models.ResponseDebt;
import com.mercado.domain.models.UserEnum;

public interface LoanI {
	public ReponseLoan requestLoan(Loan loan);
	public List<Loan> listLoan(SimpleDateFormat startDate, SimpleDateFormat endDate);
	public ResponseDebt getDebt(SimpleDateFormat endDate, long loan_id);
	public ResponseDebt allDebts(SimpleDateFormat endDate, UserEnum target);
}
