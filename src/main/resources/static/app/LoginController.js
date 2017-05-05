'use strict';

angular.module('bookStoreApp').controller('LoginController',
	['LoginService', '$scope', '$location', function( LoginService, $scope, $location){
		
		$scope.form = {};
		var self = this;
		
		self.successMessage = '';
        self.errorMessage = '';
		self.loginAccount = {};
		self.loginAccountUsername;
		self.loginAccountPassword;
		//functions
		self.login=login;
	
		function login(){
			var username = self.loginAccountUsername;
			console.log('About to login user: '+username);
		
			LoginService.getLoginAccount(username).then(
				function (loginAccount) {
					console.log('Login Account fetched successfully for user '+username);

					LoginService.login(loginAccount, self.loginAccountPassword)
						.then(
							function(response){
								console.log('Logged in successfully');
									
								if(username==='employee'){
									self.successMessage='Logged in as employee';
									self.errorMessage='';
									$location.path('employee');
									
								}
								if(username==='admin'){
									self.successMessage='Logged in as admin';
									self.errorMessage='';
									$location.path('admin');
								}
							},
							function(errResponse){
								console.error('Error while logging in');
								self.errorMessage = 'Invalid username and/or password.';
								self.successMessage = '';
							}
						);
					
				},
				function (errResponse) {
					console.error('Error while fetching Login Account for user '+username+', Error : '+errResponse.data);
				}
			);
			console.log(self.loginAccount);
		}
		
}]);