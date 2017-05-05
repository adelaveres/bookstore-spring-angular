<!DOCTYPE html>

<html lang="en" ng-app="bookStoreApp">
	<head>
		<title>${title}</title>
		<!--link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"-->
		<link href="css/bootstrap.css" rel="stylesheet"/>
		<link href="css/app.css" rel="stylesheet"/>
	</head>
	
	<body>
		<div ng-view></div>
		
		<script src="js/lib/angular.min.js" ></script>
		<script src="js/lib/angular-ui-router.min.js" ></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
		<script src="js/lib/localforage.min.js" ></script>
		<script src="js/lib/ngStorage.min.js"></script>
		<script src="app/app.js"></script>
		<script src="app/LoginController.js"></script>
		<script src="app/LoginService.js"></script>
		<script src="app/EmployeeController.js"></script>
		<script src="app/EmployeeService.js"></script>
		<script src="app/AdminController.js"></script>
		<script src="app/AdminService.js"></script>
		<script src="app/BooksController.js"></script>
		<script src="app/BooksService.js"></script>
	</body>
	
</html>