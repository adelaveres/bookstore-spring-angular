'use strict';

angular.module('bookStoreApp').factory('BooksService',
	['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
				
			var factory={
				loadAllBooks:loadAllBooks,
				getAllBooks:getAllBooks,
				getBook:getBook,
				addBook:addBook,
				deleteBook:deleteBook,
				updateBook:updateBook
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
			
			function addBook(book){
				var deferred = $q.defer();
				$http.post(urls.BOOK_SERVICE_API, book)
				.then(
					function (response){
						console.log('Successfully added book');
						console.log(response.data);
						
						loadAllBooks();
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error while creating book: '+errResponse.data.errorMessage);
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function deleteBook(id){
				var deferred = $q.defer();
				$http.delete(urls.BOOK_SERVICE_API + id)
				.then(
					function (response){
						console.log("Successfully deleted book with id: "+id);
						loadAllBooks();
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error when deleting book: '+errResponse.data.errorMessage);
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
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
			
			function updateBook(id,book){
				var deferred = $q.defer();
				$http.put(urls.BOOK_SERVICE_API+id,book)
				.then(
					function (response){
						console.log('Book updated successfully.');
						loadAllBooks();
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error while updating book.');
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
	}
]);