package com.mercado.infrastructure.persistence.entitites;


public class BalanceEntity {
	private Double payments;
	private LoanEntity loan;
		
	public BalanceEntity() {

	}
	
	public BalanceEntity(Double payments, LoanEntity loan) {
		if(payments == null ) {
			this.payments = 0.0;
		}else {
			this.payments = payments;	
		}
		this.loan = loan;
	}
	/**
	 * @return the payments
	 */
	public Double getPayments() {
		return payments;
	}
	/**
	 * @param payments the payments to set
	 */
	public void setPayments(Double payments) {
		if(payments == null ) {
			this.payments = 0.0;
		}else {
			this.payments = payments;	
		}
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
