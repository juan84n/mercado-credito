package com.mercado.infrastructure.persistence.entitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "target_rules")
public class TargetRulesEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="MIN_AMOUNT")
	private int minAmount;
	@Column(name="MAX_AMOUNT")
	private int maxAmount;
	@Column(name="MIN_CANT")
	private int minCant;
	@Column(name="MAX_CANT")
	private int maxCant;
	@Column(name="TYPE")
	private String type;
	@Column(name="RATE")
	private double rate;
	@Column(name="MAX_LOAN")
	private long maxLoan;
	
	/**
	 * @return the minAmount
	 */
	public int getMinAmount() {
		return minAmount;
	}

	/**
	 * @param minAmount the minAmount to set
	 */
	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * @return the maxAmount
	 */
	public int getMaxAmount() {
		return maxAmount;
	}

	/**
	 * @param maxAmount the maxAmount to set
	 */
	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * @return the minCant
	 */
	public int getMinCant() {
		return minCant;
	}

	/**
	 * @param minCant the minCant to set
	 */
	public void setMinCant(int minCant) {
		this.minCant = minCant;
	}

	/**
	 * @return the maxCant
	 */
	public int getMaxCant() {
		return maxCant;
	}

	/**
	 * @param maxCant the maxCant to set
	 */
	public void setMaxCant(int maxCant) {
		this.maxCant = maxCant;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	/**
	 * @return the maxLoan
	 */
	public long getMaxLoan() {
		return maxLoan;
	}



	/**
	 * @param maxLoan the maxLoan to set
	 */
	public void setMaxLoan(long maxLoan) {
		this.maxLoan = maxLoan;
	}
	
}
