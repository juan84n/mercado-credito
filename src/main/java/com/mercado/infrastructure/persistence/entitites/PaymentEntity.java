package com.mercado.infrastructure.persistence.entitites;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "payments")
public class PaymentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private double amount;
	@ManyToOne
	private LoanEntity loan;
	@Column(name="MISSING_AMOUNT")
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
	public LoanEntity getLoan() {
		return loan;
	}
	/**
	 * @param loan the loan to set
	 */
	public void setLoan(LoanEntity loan) {
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
