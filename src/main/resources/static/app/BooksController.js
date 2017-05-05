'use strict';

angular.module('bookStoreApp').controller('BooksController',
	['BooksService', '$scope', '$location', function( BooksService, $scope, $location){
		
		$scope.myBooksForm = {};
		
		var self = this;
		self.successMessage='';
		self.errorMessage='';
		self.book={};
		self.addingBook;
		self.updatingBook;
		//
		self.goToLogin=goToLogin;
		self.goToAdmin=goToAdmin;
		self.submitBook=submitBook;
		self.booksReset=booksReset;
		self.getAllBooks=getAllBooks;
		self.editBook=editBook;
		self.deleteBook=deleteBook;
		self.generateReport=generateReport;
		
		self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([.]\d+)?$/;
		
		function goToLogin(){
			$location.path('login');
		}
		
		function goToAdmin(){
			$location.path('admin');
		}
		
		function submitBook(){
			console.log('Submitting...');
			if(self.addingBook){
				addBook();
				self.addingBook = false;
			}
			if(self.updatingBook){
				updateBook();
			}
		}
		
		function addBook(){
			console.log('Adding book: '+self.book.title);
			BooksService.addBook(self.book)
			.then(
				function (response){
					console.log('Book added.');
					self.successMessage='Book added successfully.';
					self.errorMessage='';
					self.book={};
				},
				function (errResponse){
					console.log('Error in adding book');
					self.successMessage='';
					self.errorMessage='Identical book already exists - same author and title.';
				}
			);
		}
		
		function booksReset(){
			self.successMessage='';
            self.errorMessage='';
            self.book={};
            $scope.myBooksForm.$setPristine; //reset Form
		}
		
		function getAllBooks(){
			return BooksService.getAllBooks();
		}
		
		
		function editBook(id){
			BooksService.getBook(id)
			.then(
				function (response){
					self.book = response;
					console.log(self.book);
				},
				function (errResponse){
				}
			);
		}
		
		function deleteBook(id){
			console.log('About to delete book with id: '+id);
			BooksService.deleteBook(id)
			.then(
				function (response){
					console.log('Book deleted.');
					self.successMessage='Book deleted successfully.';
					self.errorMessage='';
				},
				function (errResponse){
					console.error('Error in deleting book with id: '+id);
					self.successMessage='';
					self.errorMessage='';
				}
			);
		}
		
		function updateBook(){
			console.log('About to update book with id: '+self.book.id);
			BooksService.updateBook(self.book.id,self.book)
			.then(
				function (response){
					console.log('Book updated.');
					self.successMessage='Book updated successfully.';
					self.errorMessage='';
					self.book={};
				},
				function (errResponse){
					console.error('Error while updating book with id: '+self.book.id);
					self.successMessage='';
					self.errorMessage='Book not found.';
				}
			);
		}
		
		function generateReport(type){
			
		}
	}
]);