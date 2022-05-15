package com.mercado.domain.models;

public class Balance {
	
	private double payments;
	private Loan loan;	
	
	public Balance() {
	}
	
	public Balance(long payments, Loan loan) {
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
