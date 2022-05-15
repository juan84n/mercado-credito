package com.mercado.infrastructure.persistence.entitites;


public class BalanceEntity {
	private double payments;
	private LoanEntity loan;
		
	public BalanceEntity() {

	}
	
	public BalanceEntity(double payments, LoanEntity loan) {
		this.payments = payments;
		this.loan = loan;
	}
	/**
	 * @return the payments
	 */
	public double getPayments() {
		return payments;
	}
	/**
	 * @param payments the payments to set
	 */
	public void setPayments(double payments) {
		this.payments = payments;
	}
	/**
	 * @return the loan
	 */
	public LoanEntity getLoan() {
		return loan;
	}
	/**
	 * @param loan the loan to set
	 */
	public void setLoan(LoanEntity loan) {
		this.loan = loan;
	}
}
