package com.mercado.infrastructure.adapters.payment;

import com.mercado.domain.models.Balance;
import com.mercado.infrastructure.adapters.loan.LoanTransformer;
import com.mercado.infrastructure.persistence.entitites.BalanceEntity;

public class BalanceTransfomer {
	
	public static Balance balanceEntityToBalance(BalanceEntity balanceEntity) {
		Balance balance = new Balance();
		balance.setLoan(LoanTransformer.loanEntityToLoan(balanceEntity.getLoan()));
		balance.setPayments(balanceEntity.getPayments());
		
		return balance;
	}
	
	public static BalanceEntity balanceToBalanceEntity(Balance balance) {
		BalanceEntity balanceEntity = new BalanceEntity();
		balanceEntity.setLoan(LoanTransformer.loanToLoanEntity(balance.getLoan()));
		balanceEntity.setPayments(balance.getPayments());
		
		return balanceEntity;
	}
}
