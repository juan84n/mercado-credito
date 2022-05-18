package com.mercado.domain.models;

public class TargetRules {
	private int minAmount; ;
	private int maxAmount;
	private int minCant;
	private int maxCant;
	private String type;
	private double rate;
	private long maxLoan;
	

	public TargetRules(int minAmount, int maxAmount, int minCant, int maxCant, String type, double rate, long maxLoan) {
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.minCant = minCant;
		this.maxCant = maxCant;
		this.type = type;
		this.rate = rate;
		this.maxLoan = maxLoan;
	}
	
	

	public TargetRules() {

	}


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
