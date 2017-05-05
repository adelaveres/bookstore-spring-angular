'use strict';

angular.module('bookStoreApp').controller('EmployeeController',
	['EmployeeService', '$scope', '$location', function( EmployeeService, $scope, $location){
		
		$scope.mySellingForm={};
		
		var self = this;
		self.searchedString;
		self.book = {};
		self.books = [];
		self.searchBooks = false;
		self.searchingCriteria=["Title","Author","Genre"];
		self.searchedCriterion;
		self.successMessage='';
		self.errorMessage='';
		self.noOfBooksToSell=[];
		//
		self.goToLogin=goToLogin;
		self.getAllBooks=getAllBooks;
		self.sellBooks=sellBooks;
		
		self.onlyIntegers = /^\d+$/;
		
		function goToLogin(){
			$location.path('login');
		}
		
		function getAllBooks(){
			if(self.searchedString===undefined || self.searchedString===""){
				return EmployeeService.getAllBooks();
			}
			else{
				if(self.searchBooks){
					self.searchBooks = false;
					self.successMessage="";
					self.errorMessage="";
					
					switch(self.searchedCriterion){
						case "Title": searchBooksByTitle();break;
						case "Author": searchBooksByAuthor();break;
						case "Genre": searchBooksByGenre();break;
						default: searchBooksByTitle();break;
					}
					
				}
				return EmployeeService.getSearchedBooks();
			}
		}
		 
		function searchBooksByTitle(){
			console.log('Searching book');
			EmployeeService.searchBooksByTitle(self.searchedString)
			.then(
				function (response){
				},
				function (errResponse){
				}
			);
		}
		
		function searchBooksByAuthor(){
			console.log('Searching book');
			EmployeeService.searchBooksByAuthor(self.searchedString)
			.then(
				function (response){
				},
				function (errResponse){
				}
			);
		}
		
		function searchBooksByGenre(){
			console.log('Searching book');
			EmployeeService.searchBooksByGenre(self.searchedString)
			.then(
				function (response){
				},
				function (errResponse){
				}
			);
		}
		
		function sellBooks(id){
			var noCopiesToSell = self.noOfBooksToSell[id];
			EmployeeService.getBook(id)
			.then(
				function (response){
					self.book = response;
					console.log(self.book);
					
					console.log('No of books to sell: '+ noCopiesToSell);
					console.log('Existing stock: '+self.book.quantity);
					
					if(noCopiesToSell > 0  &&  noCopiesToSell <= self.book.quantity){
						console.log('Selling books...');
						EmployeeService.sellBooks(id,noCopiesToSell)
						.then(
							function (response){
								console.log('Successfully sold '+noCopiesToSell+' copies.');
								self.successMessage='Successfully sold '+noCopiesToSell+' copies of '+self.book.title;
								self.errorMessage='';
							},
							function (errResponse){
								console.error('Error while selling books.');
								self.successMessage='';
								self.errorMessage='';
							}
						);
					}
					if(noCopiesToSell > self.book.quantity){
						self.successMessage='';
						self.errorMessage='Insufficient stock.';
					}
				},
				function (errResponse){
				}
			);
			
		}
	}
]);