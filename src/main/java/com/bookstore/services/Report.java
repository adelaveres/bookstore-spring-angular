package com.bookstore.services;

import java.util.List;

import com.bookstore.model.Book;

public interface Report {
	void generateReport(List<Book> booksList);
}
