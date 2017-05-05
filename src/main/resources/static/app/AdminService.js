'use strict';

angular.module('bookStoreApp').factory('AdminService',
	['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
				
			var factory={
				loadAllEmployees:loadAllEmployees,
				getAllEmployees:getAllEmployees,
				addEmployee:addEmployee,
				deleteEmployee:deleteEmployee,
				getEmployee:getEmployee,
				updateEmployee:updateEmployee
			};
			return factory;
			
			function loadAllEmployees(){
				console.log('Fetching employees..')
				var deferred=$q.defer();
				
				$http.get(urls.EMPLOYEE_SERVICE_API)
				.then(
					function (response){
						console.log('Fetched successfully all employees');
						$localStorage.employees = response.data;
						deferred.resolve(response);
					},
					function (errResponse) {
						console.error('Error while loading employees');
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function getAllEmployees(){
				return $localStorage.employees;
			}
			
			function addEmployee(employee){
				var deferred = $q.defer();
				$http.post(urls.EMPLOYEE_SERVICE_API, employee)
				.then(
					function (response){
						console.log('Successfully added Employee');
						console.log(response.data);
						
						loadAllEmployees();
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error while creating Employee : '+errResponse.data.errorMessage);
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function deleteEmployee(id){
				var deferred = $q.defer();
				$http.delete(urls.EMPLOYEE_SERVICE_API+id)
				.then(
					function (response){
						console.log('Successfully deleted employee with id '+ id);
						loadAllEmployees();
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error in deleting employee: '+errResponse.data.errorMessage);
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
			
			function getEmployee(id){
				var deferred = $q.defer();
				$http.get(urls.EMPLOYEE_SERVICE_API+id)
				.then(
					function (response){
						console.log('Successfully fetched employee with id: '+id);
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error while fetching employee with id: '+id);
						deferred.reject(response);
					}
				);
				return deferred.promise;
			}
			
			function updateEmployee(id,employee){
				var deferred = $q.defer();
				$http.put(urls.EMPLOYEE_SERVICE_API+id,employee)
				.then(
					function (response){
						console.log('Successfully updated employee with id: '+id);
						loadAllEmployees();
						deferred.resolve(response.data);
					},
					function (errResponse){
						console.error('Error while updating employee: '+errResponse.data.errorMessage);
						deferred.reject(errResponse);
					}
				);
				return deferred.promise;
			}
	}
]);