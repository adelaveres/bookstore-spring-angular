package com.bookstore.services;

import java.util.List;

import com.bookstore.model.Book;

public interface BookService {
	List<Book> findAllBooks();
	List<Book> findBooksByTitle(String searchedTitle);
	List<Book> findBooksByAuthor(String searchedAuthor);
	List<Book> findBooksByGenre(String searchedGenre);
	Book findBookById(int id);
	boolean existsBook(Book book);
	void addBook(Book b);
	void deleteBook(Book b);
	void updateBook(Book existing, Book toUpdate);
	List<Book> findOutOfStockBooks();
}
