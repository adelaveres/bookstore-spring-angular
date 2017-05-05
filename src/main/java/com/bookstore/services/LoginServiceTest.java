package com.bookstore.services;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest {

	@Autowired
	LoginService loginService;
	
	@Test
	public void findByUsernameTest(){
		assertNotNull(loginService.findByUsername("admin"));
	}
}
