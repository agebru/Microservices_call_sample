package com.atl.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private UserId id;
	private String firstName;
	private String lastName;
	
	public User() {}

	
	public User(UserId id,String firstName,String lastName) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	public UserId getId() {
		return id;
	}
	
	

	public void setId(UserId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

}
