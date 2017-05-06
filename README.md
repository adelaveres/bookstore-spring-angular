# BookStore Application
Spring Boot application, using AngularJS, Bootstrap, CSS for the user-interface, Maven for dependencies.


Installation steps:

1. install java- JDK 1.7 or above
	https://java.com/en/download/
	
2. download and install the Eclipse IDE for Java EE Developers
	https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neon3
	
3. install maven- download the binary distribution:
	https://maven.apache.org/download.cgi
	
	3.1. unzip the archive:
	
		Unix-based operating systems (Linux, Solaris and Mac OS X)
			tar zxvf apache-maven-3.x.y.tar.gz
		Windows
			unzip apache-maven-3.x.y.zip

	3.2. A directory called "apache-maven-3.x.y" will be created.

	3.3. Add the bin directory to your PATH, eg:
	
		Unix-based operating systems (Linux, Solaris and Mac OS X)
		  export PATH=/usr/local/apache-maven-3.x.y/bin:$PATH
		Windows
		  set PATH="c:\program files\apache-maven-3.x.y\bin";%PATH%

	3.4. Make sure JAVA_HOME is set to the location of your JDK

	3.5. Run "mvn --version" to verify that it is correctly installed.
	
4. M2E plugin for Eclipse
	install the latest M2Eclipse release by using the following update site from within Eclipse:
	http://download.eclipse.org/technology/m2e/releases
	
Accessing application:

● Employee View: 

	--username: employee
	--password: empl
	
● Admin View:

	--username: admin
	--password: adm

Use cases screenshots:

Login:
 ![alt tag](screenshots/login/1.Login-start.jpg)

Login - wrong password:
 ![alt tag](screenshots/login/2.Login-wrong_pass.jpg)
 
Login - employee:
 ![alt tag](screenshots/login/3.Login-employee.jpg)
 
Login - admin:
 ![alt tag](screenshots/login/4.Login-admin.jpg)

Employee - start page:
 ![alt tag](screenshots/employee/1.Employee-start.jpg)
 
Employee - search boks by title:
 ![alt tag](screenshots/employee/2.Employee-searchTitle.jpg)
 
Employee - search books by author: 
 ![alt tag](screenshots/employee/3.Employee-searchAuthor.jpg)
 
Employee - search books by genre:
 ![alt tag](screenshots/employee/4.Employee-searchGenre.jpg)
 
Employee - selling books, but there are insufficient books in stock:
 ![alt tag](screenshots/employee/5.Employee-sell-Oversell.jpg)

Employee - before selling copies of first book:
 ![alt tag](screenshots/employee/7.Employee-sell - before.jpg)
 
Employee - after selling copies of first book:
 ![alt tag](screenshots/employee/7.Employee-sell-after.jpg)
 
Admin - start page: Employees:
 ![alt tag](screenshots/admin/admin_employees/1.Admin-start-employees.jpg)

Admin - adding an Employee with already existing PNC
 ![alt tag](screenshots/admin/admin_employees/2.Admin-addEmployee-wrong-CNP.jpg)
 
Admin - before adding Employee:
 ![alt tag](screenshots/admin/admin_employees/3.Admin-addEmployee-before.jpg)
 
Admin - after adding Employee:
 ![alt tag](screenshots/admin/admin_employees/4.Admin-addEmployee-after.jpg)
 
Admin - updating employee Andreescu Sanziana - before
 ![alt tag](screenshots/admin/admin_employees/5.Admin-updateEmployee-Andreescu_Sanziana-before.jpg)
 
Admin - updating employee Andreescu Sanziana - click on Edit button
 ![alt tag](screenshots/admin/admin_employees/6.Admin-updateEmployee-Andreescu_Sanziana-clickEdit.jpg)
 
Admin - updating employee Andreescu Sanziana - change employee name
 ![alt tag](screenshots/admin/admin_employees/7.Admin-updateEmployee-Andreescu_Sanziana-changeName.jpg)
 
 Admin - updating employee Andreescu Sanziana - after:
 ![alt tag](screenshots/admin/admin_employees/8.Admin-updateEmployee-Andreescu_Sanziana-after.jpg)
 
 Admin - reset form button (having a leftover success message and input in the form fields) 
 ![alt tag](screenshots/admin/admin_employees/9.Admin-Reset-before(leftover-succesMessage).jpg)
 
 Admin - reset form button - after reset:
 ![alt tag](screenshots/admin/admin_employees/10.Admin-Reset-after.jpg)
 
 Admin - before delete - Andrei Musat:
 ![alt tag](screenshots/admin/admin_employees/11.Admin-Delete-Andrei_Musat-before.jpg)
 
 Admin - message after delete  - Andrei Musat:
 ![alt tag](screenshots/admin/admin_employees/12.Admin-Delete-Andrei_Musat-after-message.jpg)
 
 Admin - list after delete - Andrei Musat: 
 ![alt tag](screenshots/admin/admin_employees/13.Admin-Delete-Andrei_Musat-after-list.jpg)
 
 Admin - books page:
 ![alt tag](screenshots/admin/admin_books/1.Admin-books-start.jpg)
 
 Admin - before adding a book:
 ![alt tag](screenshots/admin/admin_books/2.Admin-addBook-before.jpg)
 
 Admin - after adding a book:
 ![alt tag](screenshots/admin/admin_books/3.Admin-addBook-after.jpg)
 
 Admin - adding book but a book with same title & author already exists
 ![alt tag](screenshots/admin/admin_books/4.Admin-addBook-identical_book_exists.jpg)
 
 Admin - update book - author: Dava Sobel - before:
 ![alt tag](screenshots/admin/admin_books/5.Admin-updateBook-Dava_Sobel-before.jpg)
 
 Admin - update book - author: Dava Sobel - click on Edit button:
 ![alt tag](screenshots/admin/admin_books/6.Admin-updateBook-Dava_Sobel-clickEdit.jpg)
 
 Admin - update book - author: Dava Sobel - change author name:
 ![alt tag](screenshots/admin/admin_books/7.Admin-updateBook-Dava_Sobel-changeName.jpg)
 
 Admin - update book - author: Dava Sobel - after:
 ![alt tag](screenshots/admin/admin_books/8.Admin-updateBook-Dava_Sobel-after.jpg)
 
 Admin - delete book - before:
 ![alt tag](screenshots/admin/admin_books/9.Admin-deleteBook-NewBook-before.jpg)
 
 Admin - delete book - after list:
 ![alt tag](screenshots/admin/admin_books/10.Admin-deleteBook-NewBook-after-list.jpg)
 
 Admin - delete book - after message:
 ![alt tag](screenshots/admin/admin_books/11.Admin-deleteBook-NewBook-after-message.jpg)
 
 Admin - generate pdf Report with out-of-stock books:
 ![alt tag](screenshots/admin/admin_books/12.Admin-generatePdfReport-1.jpg)
 
 Admin - the generated pdf Report
 ![alt tag](screenshots/admin/admin_books/13.Admin-generatePdfReport-2.jpg)
 
 Admin - generate csv Report with out-of-stock books:
 ![alt tag](screenshots/admin/admin_books/14.Admin-generateCsvReport-1.jpg)
 
 Admin - the generated csv Report openned with Microsoft Excel editor:
 ![alt tag](screenshots/admin/admin_books/15.Admin-generateCsvReport-2.jpg)
