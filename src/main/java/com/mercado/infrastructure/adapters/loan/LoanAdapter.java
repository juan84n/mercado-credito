package com.mercado.infrastructure.adapters.loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.Loan;
import com.mercado.domain.models.ResponseDebt;
import com.mercado.domain.repository.LoanRepositoryI;
import com.mercado.infrastructure.persistence.dao.LoanDaoI;
import com.mercado.infrastructure.persistence.entitites.LoanEntity;

@Service
public class LoanAdapter implements LoanRepositoryI {
	
	@Autowired
	private LoanDaoI loanDao;
	

	@Override
	public Loan requestLoan(Loan loan) {

		LoanEntity loanEntity = loanDao.save(LoanTransformer.loanToLoanEntity(loan));
		return LoanTransformer.loanEntityToLoan(loanEntity);
	}

	@Override
	public List<Loan> listLoan(String startDate, String endDate) {
		if(startDate.isEmpty() || endDate.isEmpty()) {
			return this.loanDao.findAll()
					.stream()
					.map(LoanTransformer::loanEntityToLoan)
					.collect(Collectors.toList());	
		}
		
        SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDParsed = null;
        
        SimpleDateFormat endDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date endDParsed = null;

		try {
	        startDParsed = startDateFormat.parse(startDate);
			endDParsed = endDateFormat.parse(endDate);
			
	        java.sql.Date startDateSql = new java.sql.Date(startDParsed.getTime());
	        java.sql.Date endDateSql = new java.sql.Date(endDParsed.getTime());
	        
			return this.loanDao.listLoanByDates(startDateSql, endDateSql)
					.stream()
					.map(LoanTransformer::loanEntityToLoan)
					.collect(Collectors.toList());	
			
		} catch (ParseException e) {
			throw new BusinessException(Status.INTERNAL_SERVER_ERROR.getCode(), "El formato de las fechas debe ser 'yyyy-MM-dd' " + e.getMessage());
		}
        
	}

	@Override
	public ResponseDebt getDebt(String endDate, long loan_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> getLoansByUser(long user_id) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		java.sql.Date prevYear = new java.sql.Date(cal.getTime().getTime());
		return loanDao.findByUserId(user_id, prevYear).stream()
				.map(LoanTransformer::loanEntityToLoan)
				.collect(Collectors.toList());
	}

	@Override
	public Loan getLoanById(long loan_id) {
		LoanEntity loanEntity = loanDao.getById(loan_id);
		if(loanEntity.getId() == 0) {
			throw new BusinessException(Status.NOT_FOUND.getCode(), "El préstamo con id " + loan_id + " no existe");
		}
		
		return LoanTransformer.loanEntityToLoan(loanEntity);
	}

}
