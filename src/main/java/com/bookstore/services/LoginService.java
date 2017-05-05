package com.bookstore.services;

import com.bookstore.model.LoginAccount;

public interface LoginService {
	
	LoginAccount findByUsername(String username);

}
