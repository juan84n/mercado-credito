package com.mercado.domain.models;

public class ReponseLoan {
	private long loan_id;
	private long installment;
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
	public long getInstallment() {
		return installment;
	}
	/**
	 * @param installment the installment to set
	 */
	public void setInstallment(long installment) {
		this.installment = installment;
	}
	
}
