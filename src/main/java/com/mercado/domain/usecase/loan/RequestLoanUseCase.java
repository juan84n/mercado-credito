package com.mercado.domain.usecase.loan;


import java.text.SimpleDateFormat;
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
	 * @param loan
	 * @return
	 */
	public ResponseLoan requestLoan(Loan loan) {
		if(loan.getAmount() <=0 || loan.getTerm() <= 0 || loan.getUser_id() <= 0) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "The data is incorrect");
		}
		
		User user = this.userRepository.getById(loan.getUser_id());
		List<Loan> loans = this.loanRepository.getLoansByUser(loan.getUser_id());
		TargetRules targetRule = this.getTargetRules(loans, loan);
		
		if(user.getTarget() == null) {
			throw new BusinessException(Status.NOT_FOUND.getCode(), "User not found");
		}
		
		SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		String dateFormat = isoDate.format(new Date());
		loan.setTarget(targetRule.getType());
		loan.setRate(targetRule.getRate());
		loan.setDate(dateFormat);
		Loan loanResponse = loanRepository.requestLoan(loan);
		ResponseLoan response = new ResponseLoan();
		response.setLoan_id(loanResponse.getId());
		response.setInstallment(Utils.getLoanByFormule(loan));

		return response;
	}
	
	/**
	 * @param loans
	 * @param loan
	 * @return
	 */
	private TargetRules getTargetRules(List<Loan> loans, Loan loan) {
		List<TargetRules> targetRules = this.targetRulesRepository.listRules();
		int cantLoans = loans.size();
		long amountTotal = loans.stream().mapToLong(loanMap -> loanMap.getAmount())
				.reduce(0, (a, b) -> a + b);
		
		TargetRules targetRule =  targetRules.stream().filter(rule -> {
			
			boolean maximum = loan.getAmount() <= rule.getMaxLoan();
			
			if (rule.getMaxCant() == -1) {
				return cantLoans > rule.getMinCant() && amountTotal >= rule.getMinAmount() && maximum;
			}
			
			boolean quantity = cantLoans >= rule.getMinCant() && cantLoans <= rule.getMaxCant();
			boolean total = amountTotal >= rule.getMinAmount() && amountTotal <= rule.getMaxAmount();
			
			return quantity && total && maximum;
			
		}).findFirst().orElse(new TargetRules());
		
		if(targetRule.getType() == null) {
			throw new BusinessException(Status.BAD_REQUEST.getCode(), "No estás habilitado para solicitar un préstamo por el monto indicado");
		}
		return targetRule;
	}
	
	
}
