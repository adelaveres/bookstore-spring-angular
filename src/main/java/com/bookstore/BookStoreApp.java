package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.bookstore"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class BookStoreApp {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApp.class, args);
	}

}
