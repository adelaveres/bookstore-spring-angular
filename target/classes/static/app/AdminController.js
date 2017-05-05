'use strict';

angular.module('bookStoreApp').controller('AdminController',
	['AdminService', '$scope', '$location', function( AdminService, $scope, $location){
		
		$scope.myEmployeeForm = {};
		
		var self = this;
		self.successMessage='';
		self.errorMessage='';
		self.employee={};
		self.addingEmployee;
		self.updatingEmployee;
		//
		self.goToLogin=goToLogin;
		self.goToBooks=goToBooks;
		self.submitEmployee=submitEmployee;
		self.employeeReset=employeeReset;
		self.getAllEmployees=getAllEmployees;
		self.editEmployee=editEmployee;
		self.deleteEmployee=deleteEmployee;
		
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
		
		function goToLogin(){
			$location.path('login');
		}
		
		function goToBooks(){
			$location.path('books');
		}
		
		function submitEmployee(){
			console.log('Submitting');
			if(self.addingEmployee){
				addEmployee();
				self.addingEmployee=false;
			}
			if(self.updatingEmployee){
				updateEmployee();
				self.updatingEmployee=false;
			}
		}
		
		function addEmployee(){
			console.log('About to add employee '+self.employee.name);
			AdminService.addEmployee(self.employee)
			.then(
				function (response){
					console.log('Successfully created employee');
					self.successMessage = 'Employee added successfully.';
					self.errorMessage = '';
					self.employee={};
				},
				function (errResponse){
					console.error('Error while creating employee');
					self.successMessage = '';
					self.errorMessage = 'Identical CNP already exists.';
				}
			);
		}
		
		function updateEmployee(){
			console.log('About to update employee');
			AdminService.updateEmployee(self.employee.id, self.employee)
			.then(
				function (response){
					console.log('Employee updated successfully.');
					self.successMessage='Employee updated successfully.';
					self.errorMessage='';
					self.employee={};
				},
				function (errResponse){
					console.error('Error in updating employee with id: '+self.employee.id);
					self.successMessage='';
					self.errorMessage='Employee not found.';
				}
			);
		}
		
		function employeeReset(){
			self.successMessage='';
            self.errorMessage='';
            self.employee={};
            $scope.myEmployeeForm.$setPristine; //reset Form
		}
		
		function getAllEmployees(){
			return AdminService.getAllEmployees();
		}
		
		function editEmployee(id){
			AdminService.getEmployee(id)
			.then(
				function (response){
					self.employee = response;
					console.log(self.employee);
				},
				function (errResponse){
				}
			);
		}
		
		function deleteEmployee(id){
			console.log('About to delete employee with id: '+id);
			AdminService.deleteEmployee(id)
			.then(
				function (response){
					console.log('Successfully deleted employee with id: '+id);
					 
					self.successMessage='Employee deleted successfully.';
					self.errorMessage='';
				},
				function (errResponse){
					console.error('Error in deleting employee with id: '+id);
					self.successMessage='';
					self.errorMessage='';
				}
			);
		}
	}
]);