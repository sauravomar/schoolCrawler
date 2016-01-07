package com.edlogy.exception;

public class EdlogyException extends RuntimeException {
	
	private String reason;
	
	public EdlogyException(String string) {
		super(string);
	}
	
	public EdlogyException(String string,String reason) {
		super(string);
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
