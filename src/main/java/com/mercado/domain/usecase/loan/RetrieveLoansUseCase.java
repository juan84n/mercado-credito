package com.mercado.domain.usecase.loan;

import java.sql.Timestamp;
import java.util.List;

import com.mercado.domain.models.Loan;
import com.mercado.domain.repository.LoanRepositoryI;

/**
 * @author juanfelipenarvaez
 * 
 * Caso de uso de dominio para obtener los prestamos
 *
 */
public class RetrieveLoansUseCase {
	
	private LoanRepositoryI loanRepository;
	
	
	
	public RetrieveLoansUseCase(LoanRepositoryI loanRepository) {
		this.loanRepository = loanRepository;
	}

	/**
	 * 
	 * Obtener todos los prestamos en un rango de fechas
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Loan> findAllLoan(Timestamp startDate, Timestamp endDate){
		return loanRepository.listLoan(startDate, endDate);
	}
}
