package com.atl.dao.projection;

public interface EmployeeInfo {
	String getName();

	int getSalary();

	DeptInfo getDepartment();
	
	interface DeptInfo {
        String getDeptName();
    }
}
