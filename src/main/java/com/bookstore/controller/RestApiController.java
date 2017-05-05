package com.bookstore.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bookstore.controller.RestApiController;
import com.bookstore.model.Book;
import com.bookstore.model.Employee;
import com.bookstore.model.LoginAccount;
import com.bookstore.services.BookService;
import com.bookstore.services.EmployeeService;
import com.bookstore.services.LoginService;
import com.bookstore.services.Report;
import com.bookstore.services.ReportFactory;
import com.bookstore.util.CustomErrorType;

@RestController
@RequestMapping("/api")

public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	LoginService loginService;
	@Autowired
	BookService bookService;
	@Autowired
	EmployeeService employeeService;
	
	
	// ------------------- Get Single LoginAccount------------------------------------------

	@RequestMapping(value = "/login/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> getLoginAccount(@PathVariable("username") String username) {
		 
		logger.info("Fetching Login Account for user {}", username);
		LoginAccount loginAccount = loginService.findByUsername(username);
		if (loginAccount == null) {
			logger.error("Login Account for user {} not found.", username);
			return new ResponseEntity<Object>(new CustomErrorType("Login Account for user " + username 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LoginAccount>(loginAccount, HttpStatus.OK);  
	}
		
			
			
	// ---------------------------- Login -------------------------------------------
	@RequestMapping(value = "/login/{pass}", method = RequestMethod.PUT)
	public ResponseEntity<?> login(@PathVariable("pass") String enteredPassword, @RequestBody LoginAccount loginAccount){
		logger.info("Logging in user: {}", loginAccount.getUsername());
		
		String username = loginAccount.getUsername();
		String realPassword = loginAccount.getPassword(); 
		
		if(!enteredPassword.equals(realPassword)){
			logger.error("Incorrect Password for user {}", username);
			return new ResponseEntity<Object>(new CustomErrorType("Incorrect Password for user " + username ),
					HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	// ------------------- Get All Books---------------------------------------------

	@RequestMapping(value = "/books/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {
		
		List<Book> books = bookService.findAllBooks();
		
		if (books.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	// ------------------- Get Books By Title---------------------------------------------

	@RequestMapping(value = "/books/title/{searchedTitle}", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listBooksByTitle(@PathVariable("searchedTitle") String searchedTitle) {
		
		List<Book> books = bookService.findBooksByTitle(searchedTitle);
		
		if (books.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	
	// ------------------- Get Books By Author---------------------------------------------

	@RequestMapping(value = "/books/author/{searchedAuthor}", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listBooksByAuthor(@PathVariable("searchedAuthor") String searchedAuthor) {
		
		List<Book> books = bookService.findBooksByAuthor(searchedAuthor);
		
		if (books.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	// ------------------- Get Books By Genre---------------------------------------------

	@RequestMapping(value = "/books/genre/{searchedGenre}", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listBooksByGenre(@PathVariable("searchedGenre") String searchedGenre) {
		
		List<Book> books = bookService.findBooksByGenre(searchedGenre);
		
		if (books.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	// ------------------- Get All Employees---------------------------------------------

	@RequestMapping(value = "/employees/", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> listAllEmployees() {
		
		List<Employee> employees = employeeService.findAllEmployees();
		
		if (employees.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}	
	
	// ------------------- Create an Employee ---------------------------------------------
	
	@RequestMapping(value = "/employees/", method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", employee);

		if (employeeService.identicalCNP(employee.getPnc())) { 
			logger.error("Unable to create. An Employee with pnc {} already exist", employee.getPnc());
			return new ResponseEntity<Object>(new CustomErrorType("Unable to create. An Employee with pnc " + 
					employee.getPnc() + " already exist."),HttpStatus.CONFLICT);
		}
		employeeService.addEmployee(employee);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/employees/{id}").buildAndExpand(employee.getPnc()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// ------------------- Create a Book ---------------------------------------------

	@RequestMapping(value = "/books/", method = RequestMethod.POST)
	public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Book : {}", book);

		if (bookService.existsBook(book)) { 
			logger.error("Unable to create. A book with same title and author already exist");
			return new ResponseEntity<Object>(new CustomErrorType("Unable to create. A book with same title and author already exist.")
					,HttpStatus.CONFLICT);
		}
		bookService.addBook(book);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/books/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// ------------------- Delete an Employee-----------------------------------------

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
		logger.info("Fetching & Deleting Employee with id {}", id);

		Employee employee = employeeService.findById(id);
		if (employee == null) {
			logger.error("Unable to delete. Employee with id {} not found.", id);
			return new ResponseEntity<Object>(new CustomErrorType("Unable to delete. Employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployee(employee);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
	
	// ------------------- Delete a Book-----------------------------------------
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@PathVariable("id") int id){
		logger.info("Fetching & Deleting Book with id {}", id);
		
		Book book = bookService.findBookById(id);
		if(book == null){
			logger.error("Unable to delete. Book with id {} not found.", id);
			return new ResponseEntity<Object>(new CustomErrorType("Unable to delete. Book with id "+ id + "not found."),
					HttpStatus.NOT_FOUND);
		}
		
		bookService.deleteBook(book);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
	
	// ------------------ Get Single Employee -----------------------------------
	
	@RequestMapping(value="/employees/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable("id") int id){
		logger.info("Fetching employee with id {}", id);
		
		Employee employee = employeeService.findById(id);
		if(employee == null){
			logger.error("Employee with id {} not found.", id);
			return new ResponseEntity<Object>(new CustomErrorType("Employee with id "+id+" not found."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	// ---------------------- Get Single Book -------------------------------------
	
		@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getBook(@PathVariable("id") int id){
			logger.info("Fetching book with id {}", id);
			
			Book book = bookService.findBookById(id);
			if(book == null){
				logger.error("Book with id {} not found.", id);
				return new ResponseEntity<Object>(new CustomErrorType("Book with id "+id+" not found."),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		}
	
	//------------------------ Update Employee ------------------------------------
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
		logger.info("Updating employee {}", employee);
		
		Employee currentEmployee = employeeService.findById(id);
		
		if(currentEmployee == null){
			logger.error("Employee with id {} not found.", id);
			return new ResponseEntity<Object>(new CustomErrorType("Employee with id"+id+"not found."),
					HttpStatus.NOT_FOUND);
		}
		
		employeeService.updateEmployee(currentEmployee, employee);
		
		return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
	}
	
	//------------------------ Update Book ------------------------------------
	
	@RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@PathVariable("id") int id, @RequestBody Book book){
		logger.info("Updating book {}", book);
		
		Book currentBook = bookService.findBookById(id);
		
		if(currentBook == null){
			logger.error("Book with id {} not found.", id);
			return new ResponseEntity<Object>(new CustomErrorType("Book with id"+id+"not found."),
					HttpStatus.NOT_FOUND);
		}
		
		bookService.updateBook(currentBook, book);
		
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}
	
	//------------------------ Sell Books ------------------------------------
	
	@RequestMapping(value = "/books/sell/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> sellBooks(@PathVariable("id") int id, @RequestBody int noOfCopies){
		logger.info("Selling copies of book with id {}", id);
		
		Book currentBook = bookService.findBookById(id);
		
		if(currentBook == null){
			logger.error("Book with id {} not found.", id);
			return new ResponseEntity<Object>(new CustomErrorType("Book with id"+id+"not found."),
					HttpStatus.NOT_FOUND);
		}
		
		
		Book book = currentBook;
		book.setQuantity(book.getQuantity()-noOfCopies);
		bookService.updateBook(currentBook, book);
		
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}	
	
	
	// ------------------- DOWNLOAD ----------------------------------------
	@RequestMapping(value = "/pdfReport.pdf", method = RequestMethod.GET)
	public void getPdfFile( HttpServletResponse response) {
		List<Book> outOfStockBooks = bookService.findOutOfStockBooks();
		ReportFactory reportFactory = new ReportFactory();
		Report pdfReport = reportFactory.getReport("pdf");
		pdfReport.generateReport(outOfStockBooks);
		
	    try {
	      // get file as InputStream
	      InputStream is = new FileInputStream("pdfReport.pdf");
	      //copy it to response's OutputStream
	      IOUtils.copy(is, response.getOutputStream());
	      response.flushBuffer();
	    } catch (IOException ex) {
	    	logger.info("Error writing file to output stream",ex);
	    	throw new RuntimeException("IOError writing file to output stream");
	    }

	}
	
	@RequestMapping(value = "/csvReport.csv", method = RequestMethod.GET)
	public void getCsvFile( HttpServletResponse response) {	
		List<Book> outOfStockBooks = bookService.findOutOfStockBooks();
		ReportFactory reportFactory = new ReportFactory();
		Report pdfReport = reportFactory.getReport("csv");
		pdfReport.generateReport(outOfStockBooks);
		
		try {
	      InputStream is = new FileInputStream("csvReport.csv");
	     
	      IOUtils.copy(is, response.getOutputStream());
	      response.flushBuffer();
	    } catch (IOException ex) {
	    	logger.info("Error writing file to output stream.",ex);
	    	throw new RuntimeException("IOError writing file to output stream");
	    }

	}
}
