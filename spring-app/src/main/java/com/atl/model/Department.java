package com.atl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {
	@Id
	@GeneratedValue
	private Integer id;
	private String deptName;
	private String location;

	public Department() {

	}

	public static Department of(String name, String location) {
		Department d = new Department();
		d.setDeptName(name);
		d.setLocation(location);
		return d;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + ", location=" + location + "]";
	}

}
