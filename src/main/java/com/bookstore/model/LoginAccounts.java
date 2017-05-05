package com.bookstore.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "login_accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginAccounts {
	
	@XmlElement(name = "account")
	private List<LoginAccount> loginAccounts = null;

	public List<LoginAccount> getLoginAccounts() {
		return loginAccounts;
	}

	public void setLoginAccounts(List<LoginAccount> loginAccounts) {
		this.loginAccounts = loginAccounts;
	}
	
	
}
