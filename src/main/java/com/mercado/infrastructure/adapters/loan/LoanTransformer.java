package com.mercado.infrastructure.adapters.loan;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.models.Loan;
import com.mercado.infrastructure.persistence.entitites.LoanEntity;
import com.mercado.domain.exceptions.Status;

public class LoanTransformer {
	
	public static LoanEntity loanToLoanEntity(Loan loan) {
		LoanEntity loanEntity = new LoanEntity();
		loanEntity.setId(loan.getId());
		loanEntity.setAmount(loan.getAmount());
		Date date  = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
					.parse(loan.getDate());
		} catch (ParseException e) {
			throw new BusinessException(Status.INTERNAL_SERVER_ERROR.getCode(), "Problema al transformar las fechas");
		}  
		loanEntity.setDate(new java.sql.Date(date.getTime()));
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
		SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		String dateFormat = isoDate.format(loanEntity.getDate());
		loan.setDate(dateFormat);
		loan.setRate(loanEntity.getRate());
		loan.setTarget(loanEntity.getTarget());
		loan.setTerm(loanEntity.getTerm());
		loan.setUser_id(loanEntity.getUser_id());
		
		return loan;
	}
}
