package com.mercado.domain.models;

public class Payment {
	
	private long amount;
	private Loan loan;
	/**
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}
	/**
	 * @return the loan
	 */
	public Loan getLoan() {
		return loan;
	}
	/**
	 * @param loan the loan to set
	 */
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	
}

