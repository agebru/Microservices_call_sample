package com.rest.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int intCode;
	private String strCode;
	
	public UserId() {		
	}
	
	public UserId(int intCode,String strCode){
		this.intCode=intCode;
		this.strCode=strCode;
	}

	public int getIntCode() {
		return intCode;
	}

	public void setIntCode(int intCode) {
		this.intCode = intCode;
	}

	public String getStrCode() {
		return strCode;
	}

	public void setStrCode(String strCode) {
		this.strCode = strCode;
	}
	
	

}
