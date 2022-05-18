package com.mercado.domain.models;

import java.sql.Timestamp;

public class Payment {
	
	private long id;
	private double amount;
	private Loan loan;
	private double missingAmount;
	private Timestamp date;
	
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
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
	/**
	 * @return the missingAmount
	 */
	public double getMissingAmount() {
		return missingAmount;
	}
	/**
	 * @param missingAmount the missingAmount to set
	 */
	public void setMissingAmount(double missingAmount) {
		this.missingAmount = missingAmount;
	}
	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}

