package com.atl.exception;

public class ErrorMessage extends RuntimeException {
	private String message;
	private int id;
	private String code;
	
	
	
	public ErrorMessage(String message, int id, String code) {
		super(message);
		this.message = message;
		this.id = id;
		this.code = code;
	}

	public ErrorMessage() {}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
