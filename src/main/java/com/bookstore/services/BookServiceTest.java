package com.bookstore.services;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.model.Book;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceTest {
	
	@Autowired
	BookService bookService;
	
	int initNoEmpl;
	
	@Before
	public void setUp(){
		initNoEmpl = bookService.findAllBooks().size();
	}
	
	@After
	public void tearDown(){
		int finalNoEmpl = bookService.findAllBooks().size();
		int diff = finalNoEmpl-initNoEmpl;
		int lastIdToDelete = bookService.findAllBooks().get(bookService.findAllBooks().size() - 1).getId();
		for(int i = 0; i<diff; i++){
			bookService.deleteBook(bookService.findBookById(lastIdToDelete));
			lastIdToDelete = lastIdToDelete-1;
		}
	}
	
	@Test
	public void findAllBooksTest(){
		assertNotNull(bookService.findAllBooks());
	}
	
	@Test
	public void findBooksByTitleTest(){
		assertNotNull(bookService.findBooksByTitle("The"));
	}
	
	@Test
	public void findBooksByAuthorTest(){
		assertNotNull(bookService.findBooksByAuthor("Dav"));
	}
	
	@Test 
	public void findBooksByGenreTest(){
		assertNotNull(bookService.findBooksByGenre("history"));
	} 
	
	@Test
	public void findBookByIdTest(){
		Integer id = bookService.findAllBooks().get(bookService.findAllBooks().size() - 1).getId();
		assertNotNull(bookService.findBookById(id));
	} 
	
	@Test
	public void existsBookTest(){
		Integer id = bookService.findAllBooks().get(bookService.findAllBooks().size() - 1).getId() + 1;
		Book book = new Book(id, "One more book", "Author1", "non-fictional", 3, 4.5);
		bookService.addBook(book);
		assertTrue(bookService.existsBook(book));
	}
	
	@Test
	public void addBookTest(){
		Integer id = bookService.findAllBooks().get(bookService.findAllBooks().size() - 1).getId() + 1;
		Book book = new Book(id, "One more book", "Author1", "non-fictional", 3, 4.5);
		bookService.addBook(book);
		assertTrue(bookService.existsBook(book));
	}
	
	@Test
	public void updateBookTest(){
		Integer id = bookService.findAllBooks().get(bookService.findAllBooks().size() - 1).getId() + 1;
		Book existingBook = new Book(id, "One more book", "Author1", "non-fictional", 3, 4.5);
		Book newBook = new Book(id, "A story to remember", "Author1", "non-fictional", 3, 4.5);
		bookService.addBook(existingBook);
		
		bookService.updateBook(existingBook, newBook);
		assertNotNull(bookService.findBookById(id).getTitle().equals("A story to remember"));
	}
	
	@Test
	public void deleteBookTest(){
		Integer id = bookService.findAllBooks().get(bookService.findAllBooks().size() - 1).getId() + 1;
		Book book = new Book(id, "One more book", "Author1", "non-fictional", 3, 4.5);
		bookService.addBook(book);
		
		bookService.deleteBook(book);
		assertNull(bookService.findBookById(id));
	}
	
	@Test
	public void outOfStockBooksTest(){
		Integer id = bookService.findAllBooks().get(bookService.findAllBooks().size() - 1).getId() + 1;
		Book book = new Book(id, "One more book", "Author1", "non-fictional", 0, 4.5);
		bookService.addBook(book);
		
		assertNotNull(bookService.findOutOfStockBooks());
	}
	
}


