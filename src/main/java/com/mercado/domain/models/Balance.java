package com.mercado.domain.models;

/**
 * @author juanfelipenarvaez
 * 
 * Modelo de dominio para obtener el balance
 *
 */
public class Balance {
	
	private Double payments;
	private Loan loan;	
	
	public Balance() {
	}
	
	public Balance(Double payments, Loan loan) {
		this.payments = payments;
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
