package com.bookstore.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.model.Employee;
import com.bookstore.model.EmployeesList;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	
	private EmployeesList employees;
	private List<Employee> employeesList;
	
	public EmployeeServiceImpl(){
		employees = new EmployeesList();
		employeesList = employees.getEmployeesList();
	}
	
	public List<Employee> findAllEmployees(){
		return this.employeesList;
	}
	
	public boolean identicalCNP(String cnp){
		for(Employee e: employeesList){
			if(e.getPnc().equals(cnp)){
				return true;
			}
		}
		return false;
	}
	
	public void addEmployee(Employee e){
		e.setId(employeesList.get(employeesList.size()-1).getId() + 1);
		employees.addEmployee(e);
	}
	
	public Employee findById(int id){
		for(Employee e: employeesList){
			if(e.getId()==id)
				return e;
		}
		return null;
	}
	
	public void deleteEmployee(Employee employee){
		employees.deleteEmployee(employee);
	}
	
	public void updateEmployee(Employee existing, Employee toUpdate){
		employees.updateEmployee(existing, toUpdate);
	}
}
