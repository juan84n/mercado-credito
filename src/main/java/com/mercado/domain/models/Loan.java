package com.mercado.domain.models;

import java.text.SimpleDateFormat;

public class Loan {
	
	private long id;
	private long amount_total;
	private int cant;
	private double rate;
	private long max;
	private int user_id;
	private int term;
	private SimpleDateFormat date;
	private UserEnum target;
	
	
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
	 * @return the date
	 */
	public SimpleDateFormat getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(SimpleDateFormat date) {
		this.date = date;
	}
	/**
	 * @return the term
	 */
	public int getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) {
		this.term = term;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the amount_total
	 */
	public long getAmount_total() {
		return amount_total;
	}
	/**
	 * @param amount_total the amount_total to set
	 */
	public void setAmount_total(long amount_total) {
		this.amount_total = amount_total;
	}
	/**
	 * @return the cant
	 */
	public int getCant() {
		return cant;
	}
	/**
	 * @param cant the cant to set
	 */
	public void setCant(int cant) {
		this.cant = cant;
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
	/**
	 * @return the target
	 */
	public UserEnum getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(UserEnum target) {
		this.target = target;
	}
	
}
