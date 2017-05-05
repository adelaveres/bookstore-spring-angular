package com.bookstore.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.model.LoginAccount;
import com.bookstore.model.LoginAccountsList;


@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService{
	
	LoginAccountsList loginAccounts;
	List<LoginAccount> loginAccountsList;
	
	public LoginServiceImpl(){
		loginAccounts = new LoginAccountsList();
		loginAccountsList = loginAccounts.getAccountsList();
	}
	
	public LoginAccount findByUsername(String username){
		for(LoginAccount a: loginAccountsList){
			if(a.getUsername().equals(username))
				return a;
		}
		return null;
	}
}
