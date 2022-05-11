package com.mercado.domain.models;

public class User {
	
	private int id;
	private String name;
	private UserEnum target;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
