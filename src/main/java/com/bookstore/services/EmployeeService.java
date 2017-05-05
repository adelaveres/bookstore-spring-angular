package com.bookstore.services;

import java.util.List;

import com.bookstore.model.Employee;

public interface EmployeeService {
	List<Employee> findAllEmployees();
	boolean identicalCNP(String cnp);
	void addEmployee(Employee e);
	Employee findById(int id);
	void deleteEmployee(Employee employee);
	void updateEmployee(Employee existing, Employee toUpdate);
}
