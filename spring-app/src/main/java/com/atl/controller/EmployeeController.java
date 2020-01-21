package com.atl.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atl.dao.projection.EmployeeInfo;
import com.atl.dao.projection.EmployeeRepository;
import com.atl.model.Department;
import com.atl.model.Employee;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value = { "","/", "/create" })
	public List<Employee> saveEmployees() {
		return employeeRepository.saveAll(createEmployees());
	}

	@GetMapping("/all")
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@GetMapping("/all-projection")
	public List<Employee> findWithDepartments() {

		List<EmployeeInfo> list = employeeRepository.findBy();
		for (EmployeeInfo ei : list) {
			EmployeeInfo.DeptInfo di = ei.getDepartment();
			System.out.printf("Name: %s, Salary: %s, Dept: %s%n", ei.getName(), ei.getSalary(), di.getDeptName());
		}

		List<EmployeeInfo> emps = employeeRepository.findBy();
		List<Employee> EmpList = new ArrayList<>();
		for (EmployeeInfo einfo : emps) {
			Employee emp = new Employee();
			emp.setName(einfo.getName());
			emp.setSalary(einfo.getSalary());
			// set Department
			Department dept = new Department();
			dept.setDeptName(einfo.getDepartment().getDeptName());
			emp.setDepartment(dept);
			EmpList.add(emp);

		}

		return EmpList;
	}

	

	@GetMapping("/all-projection/{from}/{to}")
	public List<Employee> findSalaryBetween(@PathVariable("from") int from, @PathVariable("to") int to) {
		List<EmployeeInfo> findBySalaryBetween = employeeRepository.findBySalaryBetween(from, to);
		List<Employee> empList = new ArrayList<>();
		for (EmployeeInfo einfo : findBySalaryBetween) {
			Employee tempEmp = new Employee();
			tempEmp.setName(einfo.getName());
			tempEmp.setSalary(einfo.getSalary());
			// Set Department
			Department d = new Department();
			d.setDeptName(einfo.getDepartment().getDeptName());
			tempEmp.setDepartment(d);
			empList.add(tempEmp);

		}
		return empList;
	}

	
	
	@GetMapping("/all-projection/{location}")
	public List<Employee> findByDepartmentLocation(@PathVariable("location") String location) {
		List<EmployeeInfo> findBySalaryBetween = employeeRepository.findByDepartmentLocation(location);
		List<Employee> empList = new ArrayList<>();
		for (EmployeeInfo einfo : findBySalaryBetween) {
			Employee tempEmp = new Employee();
			tempEmp.setName(einfo.getName());
			tempEmp.setSalary(einfo.getSalary());
			// Set Department
			Department d = new Department();
			d.setDeptName(einfo.getDepartment().getDeptName());
			tempEmp.setDepartment(d);
			empList.add(tempEmp);

		}
		return empList;
	}

	private List<Employee> createEmployees() {
		return Arrays.asList(Employee.of("Diana", Department.of("Admin", "NY"), 3000),
				Employee.of("Mike", Department.of("IT", "TX"), 35000),
				Employee.of("Rose", Department.of("Sales", "NC"), 4000),
				Employee.of("Sara", Department.of("Admin", "TX"), 3500),
				Employee.of("Joe", Department.of("IT", "TX"), 3000),
				Employee.of("Charlie", Department.of("IT", "NY"), 4500));
	}

}
