'use strict';

angular.module('bookStoreApp').factory('LoginService',
	['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
				
			var factory={
				login:login,
				getLoginAccount:getLoginAccount
			};
			return factory;
			
			function getLoginAccount(username){
				console.log('Fetching login account for user: '+username);
				var deferred = $q.defer();
				$http.get(urls.LOGIN_SERVICE_API+username)
					.then(
						function(response){
							console.log('Fetched successfully Login Account for user: '+username);
							console.log(response.data);
							deferred.resolve(response.data);
						},
						function(errResponse){
							console.error('Error while fetching Login Account for user: '+username);
							console.log(response.data);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}
			
			function login(loginAccount, enteredPassword){
				console.log('Logging in...');
				var deferred = $q.defer();
				$http.put(urls.LOGIN_SERVICE_API+enteredPassword, loginAccount)
					.then(
						function (response){
							deferred.resolve(response.data);
						},
						function (errResponse){
							console.error('Error while logging in user: '+errResponse.data.errorMessage);
							deferred.reject(errResponse);
						}
					);
				return deferred.promise;
			}
		}
]);