package com.mercado.infrastructure.adapters.loan;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.exceptions.Status;
import com.mercado.domain.models.Loan;
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
	public List<Loan> listLoan(Timestamp startDate, Timestamp endDate) {
		if(startDate == null || endDate == null) {
			return this.loanDao.findAll()
					.stream()
					.map(LoanTransformer::loanEntityToLoan)
					.collect(Collectors.toList());	
		}
        
		return this.loanDao.listLoanByDates(startDate, endDate)
				.stream()
				.map(LoanTransformer::loanEntityToLoan)
				.collect(Collectors.toList());	
        
	}

	@Override
	public List<Loan> getLoansByUser(long user_id, Timestamp startDate) {
		return loanDao.findByUserId(user_id, startDate).stream()
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
