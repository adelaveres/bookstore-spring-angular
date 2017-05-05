package com.bookstore.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BooksList {
	private static final String path= "book_store.xml";
	private File file;
	private JAXBContext jaxbContext;
	private Unmarshaller jaxbUnmarshaller;
	private Marshaller jaxbMarshaller;
	private BookStore bookStore;

	private List<Book> booksList;
	
	public BooksList(){
		try {    
            file = new File(path);    
            jaxbContext = JAXBContext.newInstance(BookStore.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			bookStore = (BookStore) jaxbUnmarshaller.unmarshal(file);
			booksList = bookStore.getBooksList();
		}catch(JAXBException e) {
      	  e.printStackTrace(); 
		}   
	}
	
	public List<Book> getBooksList(){
		return this.booksList;
	}
	public void setBooksList(List<Book> newList){
		this.booksList = newList;
	}
	
	public void addBook(Book book){
		try{
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			booksList.add(book);
			
			BookStore bookStore = new BookStore();
		    bookStore.setBooksList(booksList);
		    
		    jaxbMarshaller.marshal(bookStore, new FileOutputStream("book_store.xml"));
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} 
	}
	
	public void deleteBook(Book book){
		try{
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			booksList.remove(book);
			
			BookStore bookStore = new BookStore();
		    bookStore.setBooksList(booksList);
		    
		    jaxbMarshaller.marshal(bookStore, new FileOutputStream("book_store.xml"));
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBook(Book existingBook, Book bookToUpdate){
		try{
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			int index = booksList.indexOf(existingBook);
			booksList.set(index, bookToUpdate);
			
			BookStore bookStore = new BookStore();
		    bookStore.setBooksList(booksList);
		    
		    jaxbMarshaller.marshal(bookStore, new FileOutputStream("book_store.xml"));
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
}
