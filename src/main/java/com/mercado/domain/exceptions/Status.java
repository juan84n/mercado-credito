package com.mercado.domain.exceptions;

public enum Status {
	BAD_REQUEST(400),
	INTERNAL_SERVER_ERROR(500),
	NOT_FOUND(404);
	
	private int code;

	private Status(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
