package com.bookstore.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class EmployeesList {

	private static final String path= "employees.xml";
	private File file;
	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;
	Marshaller jaxbMarshaller;
	private Employees employees;

	private List<Employee> employeesList;
	
	public EmployeesList(){
		try {    
            file = new File(path);    
            jaxbContext = JAXBContext.newInstance(Employees.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			employees = (Employees) jaxbUnmarshaller.unmarshal(file);
			employeesList = employees.getEmployeesList();
		}catch(JAXBException e) {
      	  e.printStackTrace(); 
		}   
	}

	public List<Employee> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(List<Employee> employeesList) {
		this.employeesList = employeesList;
	}
	
	public void addEmployee(Employee employee){
		try{
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			employeesList.add(employee);
			
			Employees employees = new Employees();
		    employees.setEmployeesList(employeesList);
		    
		    jaxbMarshaller.marshal(employees, new FileOutputStream("employees.xml"));
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} 
	}
	
	public void deleteEmployee(Employee employee){
		try{
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			employeesList.remove(employee);
			
			Employees employees = new Employees();
		    employees.setEmployeesList(employeesList);
		    
		    jaxbMarshaller.marshal(employees, new FileOutputStream("employees.xml"));
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmployee(Employee existingEmployee, Employee employeeToUpdate){
		try{
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			int index = employeesList.indexOf(existingEmployee);
			employeesList.set(index, employeeToUpdate);
			
			Employees employees = new Employees();
			employees.setEmployeesList(employeesList);
			
			jaxbMarshaller.marshal(employees, new FileOutputStream("employees.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
}
