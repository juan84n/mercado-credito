package com.mercado.domain.models;

public class ResponsePayment {
	private long id;
	private long loan_id;
	private double debt;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the loan_id
	 */
	public long getLoan_id() {
		return loan_id;
	}
	/**
	 * @param loan_id the loan_id to set
	 */
	public void setLoan_id(long loan_id) {
		this.loan_id = loan_id;
	}
	/**
	 * @return the debt
	 */
	public double getDebt() {
		return debt;
	}
	/**
	 * @param debt the debt to set
	 */
	public void setDebt(double debt) {
		this.debt = debt;
	}
	
	
}
