package com.mercado.domain.models;

public class ResponseLoan {
	private long loan_id;
	private double installment;
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
	 * @return the installment
	 */
	public double getInstallment() {
		return installment;
	}
	/**
	 * @param installment the installment to set
	 */
	public void setInstallment(double installment) {
		this.installment = installment;
	}
	
}
