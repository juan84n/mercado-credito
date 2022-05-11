package com.mercado.domain.models;

public enum UserEnum {
	NEW("NEW", 0.15, 500000),
	FREQUENT("FREQUENT", 0.10, 1000000),
	PREMIUM("PREMIUM", 0.05, 5000000);
	
	private String target;
	private double rate;
	private long max;
	
	private UserEnum(String target, double rate, long max) {
		this.target = target;
		this.rate = rate;
		this.max = max;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
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
	 * @return the max
	 */
	public long getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(long max) {
		this.max = max;
	}
	

}