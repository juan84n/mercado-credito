package com.mercado.domain.models;

public enum UserEnum {
	NEW("NEW"),
	FREQUENT("FREQUENT"),
	PREMIUM("PREMIUM");
	
	private String target;
	
	private UserEnum(String target) {
		this.target = target;
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

}