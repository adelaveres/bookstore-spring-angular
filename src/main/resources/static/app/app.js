var app = angular.module('bookStoreApp',['ngStorage','ngRoute']);

app.constant('urls', {
    BASE: 'http://localhost:8080/BookStore',
	LOGIN_SERVICE_API: 'http://localhost:8080/BookStore/api/login/',
	BOOK_SERVICE_API: 'http://localhost:8080/BookStore/api/books/',
	EMPLOYEE_SERVICE_API: 'http://localhost:8080/BookStore/api/employees/'
});

app.config(	
    function($routeProvider) {	
		$routeProvider
		.when('/',{
			templateUrl: 'partials/login',
			controller: 'LoginController',
			controllerAs: 'loginCtrl'
		})
		.when('/login', {
			templateUrl: 'partials/login',
			controller: 'LoginController',
			controllerAs: 'loginCtrl'
		})
		.when('/employee',{
			templateUrl: 'partials/employee',
			controller: 'EmployeeController',
			controllerAs: 'employeeCtrl',
			resolve: {
				users: function ($q, EmployeeService) {
					console.log('Load all books');
					var deferred = $q.defer();
					EmployeeService.loadAllBooks().then(deferred.resolve, deferred.resolve);
					return deferred.promise;
					}
				}
		})
		.when('/admin',{
			templateUrl: 'partials/admin',
			controller: 'AdminController',
			controllerAs: 'adminCtrl',
			resolve: {
				users: function ($q, AdminService) {
					console.log('Load all employees');
					var deferred = $q.defer();
					AdminService.loadAllEmployees().then(deferred.resolve, deferred.resolve);
					return deferred.promise;
					}
				}
		})
		.when('/books',{
			templateUrl: 'partials/books',
			controller: 'BooksController',
			controllerAs: 'booksCtrl'
		});
		//.when('/pdfReport',{
			//templateUrl: 'partials/pdfReport'
		//});
});
