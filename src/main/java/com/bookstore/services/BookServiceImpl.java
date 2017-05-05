package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.model.Book;
import com.bookstore.model.BooksList;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{
	
	private BooksList books;
	private List<Book> booksList;
	
	public BookServiceImpl(){
		books = new BooksList();
		booksList = books.getBooksList();
	}
	
	public List<Book> findAllBooks(){
		return this.booksList;
	}
	
	public List<Book> findBooksByTitle(String searchedTitle){
		List<Book> searchedBooks = new ArrayList<Book>();
		Pattern p = Pattern.compile("\\A"+searchedTitle+"(.*)");
		
		if(!booksList.isEmpty()){
			for(Book book: booksList){
				Matcher m = p.matcher(book.getTitle());
				if (m.find())
					searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}
	
	public List<Book> findBooksByAuthor(String searchedAuthor){
		List<Book> searchedBooks = new ArrayList<Book>();
		Pattern p = Pattern.compile("\\A"+searchedAuthor+"(.*)");
		
		if(!booksList.isEmpty()){
			for(Book book: booksList){
				Matcher m = p.matcher(book.getAuthor());
				if (m.find())
					searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}
	
	public List<Book> findBooksByGenre(String searchedGenre){
		List<Book> searchedBooks = new ArrayList<Book>();
		Pattern p = Pattern.compile("\\A"+searchedGenre+"(.*)");
		
		if(!booksList.isEmpty()){
			for(Book book: booksList){
				Matcher m = p.matcher(book.getGenre());
				if (m.find())
					searchedBooks.add(book);
			}
		}
		return searchedBooks;
	}
	
	public Book findBookById(int id){
		if(!booksList.isEmpty()){
			for(Book b: booksList){
				if(b.getId()==id)
					return b;
			}
		}
		return null;
	}
	
	public boolean existsBook(Book book){
		for(Book b: booksList){
			if((b.getAuthor().equals(book.getAuthor())) && (b.getTitle().equals(book.getTitle())) ){
				return true;
			}
		}
		return false;
	}
	
	public void addBook(Book b){
		b.setId(booksList.get(booksList.size() - 1).getId() +1);
		books.addBook(b);
	} 
	
	public void deleteBook(Book b){
		books.deleteBook(b);
	}
	
	public void updateBook(Book existing, Book toUpdate){
		books.updateBook(existing, toUpdate);
	}
	
	public List<Book> findOutOfStockBooks(){
		List<Book> outOfStockList = new ArrayList<Book>();
		for(Book b: booksList){
			if(b.getQuantity()==0)
				outOfStockList.add(b);
		}
		return outOfStockList;
	}
}
