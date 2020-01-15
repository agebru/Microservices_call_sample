package com.atl.dto;

public class UserDTO {
	private int intCode;
	private String strCode;
	private String firstName;
	
	public UserDTO() {}

	
	public UserDTO(int intCode, String strCode, String firstName) {
		this.intCode = intCode;
		this.strCode = strCode;
		this.firstName = firstName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "UserDTO [intCode=" + intCode + ", strCode=" + strCode + ", firstName=" + firstName + "]";
	}
	
	

}
