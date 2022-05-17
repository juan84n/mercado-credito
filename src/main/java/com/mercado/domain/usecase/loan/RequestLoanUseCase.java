package com.mercado.domain.usecase.loan;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mercado.domain.exceptions.BusinessException;
import com.mercado.domain.models.Loan;
import com.mercado.domain.models.ResponseLoan;
import com.mercado.domain.models.TargetRules;
import com.mercado.domain.models.User;
import com.mercado.domain.repository.LoanRepositoryI;
import com.mercado.domain.repository.TargetRulesRepositoryI;
import com.mercado.domain.repository.UserRepositoryI;
import com.mercado.domain.shared.Utils;
import com.mercado.domain.exceptions.Status;

/**
 * @author juanfelipenarvaez
 * 
 * Caso de uso del dominio para manejar las peticion de un préstamo
 *
 */
public class RequestLoanUseCase {
	
	private LoanRepositoryI loanRepository;
	private UserRepositoryI userRepository;
	private TargetRulesRepositoryI targetRulesRepository;

	public RequestLoanUseCase(LoanRepositoryI loanRepository, UserRepositoryI userRepository, TargetRulesRepositoryI targetRulesRepository) {
		this.loanRepository = loanRepository;
		this.userRepository = userRepository;
		this.targetRulesRepository = targetRulesRepository;
	}
	
	
	/**
	 * 
	 * Solicitar un préstamo
	 * 
	 * @param loan
	 * @return
	 */
	public ResponseLoan requestLoan(Loan loan) {
		if(loan.getAmount() <=0 || loan.getTerm() <= 0 || loan.getUser_id() <= 0) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "La información es incorrecta amount, term y user_id son obligatorios");
		}
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		Timestamp prevYearTime = new Timestamp(cal.getTime().getTime());
		
		User user = this.userRepository.getById(loan.getUser_id());
		
		List<Loan> loans = this.loanRepository.getLoansByUser(loan.getUser_id(), prevYearTime);
		
		TargetRules targetRule = this.getRules(loans, loan);
		
		if(user.getTarget() == null) {
			throw new BusinessException(Status.NOT_FOUND.getCode(), "User not found");
		}
		
		loan.setTarget(targetRule.getType());
		loan.setRate(targetRule.getRate());
		Timestamp timestamp = new java.sql.Timestamp(new Date().getTime());
		loan.setDate(timestamp);
		Loan loanResponse = loanRepository.requestLoan(loan);
		ResponseLoan response = new ResponseLoan();
		response.setLoan_id(loanResponse.getId());
		response.setInstallment(Utils.getLoanByFormule(loan));

		return response;
	}
	
	/**
	 * 
	 * Obtener las reglas
	 * 
	 * @param loans
	 * @param loan
	 * @return
	 */
	private TargetRules getRules(List<Loan> loans, Loan loan) {
		List<TargetRules> targetRules = this.targetRulesRepository.listRules();		
		TargetRules targetRule =  targetRules.stream().filter(rule -> this.getRulesByTarget(rule, loan, loans))
				.findFirst().orElse(new TargetRules());
		
		if(targetRule.getType() == null) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "No estás habilitado para solicitar un préstamo por el monto indicado");
		}
		return targetRule;
	}
	
	/**
	 * 
	 * Saber a que target pertenece el préstamo
	 * 
	 * @param rule
	 * @param loan
	 * @param loans
	 * @return
	 */
	private boolean getRulesByTarget(TargetRules rule, Loan loan, List<Loan> loans) {
		
		/*Se le suma 1 la cantidad ya que el préstamo que se esta solicitando hace parte a cantidad de préstamos pedidos*/
		int cantLoans = (loans.size() + 1);
		long amountTotal = loans.stream().mapToLong(loanMap -> loanMap.getAmount())
				.reduce(0, (a, b) -> a + b);
		
		boolean maximum = loan.getAmount() <= rule.getMaxLoan();
		
		if (rule.getMaxCant() == 0) {
			return cantLoans > rule.getMinCant() && amountTotal >= rule.getMinAmount() && maximum;
		}
		
		boolean quantity = true;
		boolean total = true;
		
		switch(rule.getType()) {
			case "NEW":
				quantity = cantLoans > rule.getMinCant() && cantLoans < rule.getMaxCant();
				total = amountTotal < rule.getMaxAmount();
				break;
			case "FREQUENT":
				quantity = cantLoans >= rule.getMinCant() && cantLoans < rule.getMaxCant();
				total = amountTotal > rule.getMinAmount() && amountTotal < rule.getMaxAmount();
				break;
			case "PREMIUM":
				quantity = cantLoans > rule.getMaxCant();
				total = amountTotal > rule.getMaxAmount();
				break;
		}
		
		return quantity && total && maximum;
	}


	/**
	 * @return the loanRepository
	 */
	public LoanRepositoryI getLoanRepository() {
		return loanRepository;
	}

	/**
	 * @return the userRepository
	 */
	public UserRepositoryI getUserRepository() {
		return userRepository;
	}

	/**
	 * @return the targetRulesRepository
	 */
	public TargetRulesRepositoryI getTargetRulesRepository() {
		return targetRulesRepository;
	}
	
}
