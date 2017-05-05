'use strict';

angular.module('bookStoreApp').factory('EmployeeService',
	['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
				
			var factory={
				loadAllBooks: loadAllBooks,
				getAllBooks: getAllBooks,
				searchBooksByTitle: searchBooksByTitle,
				searchBooksByAuthor: searchBooksByAuthor,
				searchBooksByGenre: searchBooksByGenre,
				getSearchedBooks: getSearchedBooks,
				getBook:getBook,
				sellBooks:sellBooks
			};
			return factory;
			
			function loadAllBooks(){
				console.log('Fetching books..')
				var deferred=$q.defer();
				
				$http.get(urls.BOOK_SERVICE_API)
				.then(
					function (response){
						console.log('Fetched successfully all books');
						$localStorage.books = response.data;
						deferred.resolve(response);
					},
					function (errResponse) {
						console.error('Error while loading books');
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function getAllBooks(){
				return $localStorage.books;
			}
			
			function searchBooksByTitle(searchedTitle){
				console.log('Searching books starting with: '+searchedTitle);
				var deferred=$q.defer();
				
				$http.get(urls.BOOK_SERVICE_API+"title/"+searchedTitle)
				.then(
					function (response){
						console.log('Fetched successfully all searched books');
						$localStorage.searchedBooks = response.data;
						deferred.resolve(response);
					},
					function (errResponse){
						console.error('Error while searching books')
						deferred.resolve(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function searchBooksByAuthor(searchedAuthor){
				console.log('Searching books with author: '+searchedAuthor);
				var deferred=$q.defer();
				
				$http.get(urls.BOOK_SERVICE_API+"author/"+searchedAuthor)
				.then(
					function (response){
						console.log('Fetched successfully all searched books');
						$localStorage.searchedBooks = response.data;
						deferred.resolve(response);
					},
					function (errResponse){
						console.error('Error while searching books')
						deferred.resolve(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function searchBooksByGenre(searchedGenre){
				console.log('Searching books by genre: '+searchedGenre);
				var deferred=$q.defer();
				
				$http.get(urls.BOOK_SERVICE_API+"genre/"+searchedGenre)
				.then(
					function (response){
						console.log('Fetched successfully all searched books');
						$localStorage.searchedBooks = response.data;
						deferred.resolve(response);
					},
					function (errResponse){
						console.error('Error while searching books')
						deferred.resolve(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function getSearchedBooks(){
				return $localStorage.searchedBooks;
			}
			
			function getBook(id){
				var deferred = $q.defer();
				$http.get(urls.BOOK_SERVICE_API+id)
				.then(
					function (response){
						console.log('Fetched successfully book with id: '+id);
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error while fetching book: '+errResponse.data.errorMessage);
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function sellBooks(id, noOfCopies){
				var deferred = $q.defer();
				$http.put(urls.BOOK_SERVICE_API+"sell/"+id, noOfCopies)
				.then(
					function (response){
						console.log('Sold successfully '+noOfCopies+" copies of book with id: "+id);
						loadAllBooks();
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error while selling books: '+ errResponse.data.errorMessage);
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
		}
]);