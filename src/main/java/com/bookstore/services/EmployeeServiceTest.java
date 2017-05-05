package com.bookstore.services;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.model.Employee;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;
	
	int initNoEmpl;
	
	@Before
	public void setUp(){
		initNoEmpl = employeeService.findAllEmployees().size();
	}
	
	@After
	public void tearDown(){
		int finalNoEmpl = employeeService.findAllEmployees().size();
		int diff = finalNoEmpl-initNoEmpl;
		int lastIdToDelete = employeeService.findAllEmployees().get(employeeService.findAllEmployees().size() - 1).getId();
		for(int i = 0; i<diff; i++){
			employeeService.deleteEmployee(employeeService.findById(lastIdToDelete));
			lastIdToDelete = lastIdToDelete-1;
		}
	}
	
	@Test
	public void findByIdTest(){
		assertNotNull(employeeService.findById(4));
	}
	
	@Test
	public void addEmployeeTest(){
		Integer id = employeeService.findAllEmployees().get(employeeService.findAllEmployees().size()-1).getId() + 1;
		employeeService.addEmployee(new Employee(id, "Mirela Costin", "4309430943095", "str. Ploiesti, nr. 284"));
		
		System.out.println("\nafter Add:\n");
		List<Employee>employees = employeeService.findAllEmployees();
		for(Employee e: employees){
			System.out.println(e.getId()+"\t"+e.getName());
		}
		
		assertNotNull(employeeService.findById(id));
	}
	
	@Test
	public void updateEmployeeTest(){
		Integer id = employeeService.findAllEmployees().get(employeeService.findAllEmployees().size()-1).getId() + 1;
		Employee existingEmployee = new Employee(id, "Marin Preda", "4009400940092", "str. Pitagora, nr. 314");
		Employee employeeToUpdate = new Employee(id, "Maria Petrescu", "4009400940092", "str. Pitagora, nr. 314");
		employeeService.addEmployee(existingEmployee);
		
		employeeService.updateEmployee(existingEmployee, employeeToUpdate);
		
		System.out.println("\nafter Update:\n");
		List<Employee>employees = employeeService.findAllEmployees();
		for(Employee e: employees){
			System.out.println(e.getId()+"\t"+e.getName());
		}
		
		assert(employeeService.findById(id).getName().equals("Maria Petrescu"));
	}
	
	@Test
	public void findAllEmployeesTest(){
		assertNotNull(employeeService.findAllEmployees());
	}
	
	@Test
	public void deleteEmployeeByIdTest(){
		Integer id = employeeService.findAllEmployees().get(employeeService.findAllEmployees().size()-1).getId() + 1;
		Employee employee = new Employee(id, "Liviu Petrachioaie", "1109110911094", "str. Turnisor, nr. 219");
		employeeService.addEmployee(employee);
		
		employeeService.deleteEmployee(employee);
		
		System.out.println("\nafter Delete:\n");
		List<Employee>employees = employeeService.findAllEmployees();
		for(Employee e: employees){
			System.out.println(e.getId()+"\t"+e.getName());
		}
		assertNull(employeeService.findById(id));
	}
	
	@Test
	public void identicalCnpTest(){
		Integer id = employeeService.findAllEmployees().get(employeeService.findAllEmployees().size()-1).getId() + 1;
		Employee employee = new Employee(id, "Liviu Petrachioaie", "1109110911094", "str. Turnisor, nr. 219");
		employeeService.addEmployee(employee);
		
		assertTrue(employeeService.identicalCNP(employeeService.findById(id).getPnc()));
	}
}

