package com.bookstore.model;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LoginAccountsList {
	private static final String path= "login.xml";
	private File file;
	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;
	private LoginAccounts loginAccounts;
	
	private List<LoginAccount> loginAccountsList;
	
	public LoginAccountsList(){
		try {    
            file = new File(path);    
            jaxbContext = JAXBContext.newInstance(LoginAccounts.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            loginAccounts = (LoginAccounts) jaxbUnmarshaller.unmarshal(file);
			loginAccountsList = loginAccounts.getLoginAccounts();
		}catch(JAXBException e) {
      	  e.printStackTrace(); 
		}  
	}
	
	public List<LoginAccount> getAccountsList(){
		return loginAccountsList;
	}
	
	public void setAccountsList(List<LoginAccount> newList){
		this.loginAccountsList = newList;
	}
	
}
