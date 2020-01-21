package com.atl.dao.projection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atl.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<EmployeeInfo> findBy();

	List<EmployeeInfo> findBySalaryBetween(int salaryMin, int salaryMax);

	List<EmployeeInfo> findByDepartmentLocation(String location);
}
