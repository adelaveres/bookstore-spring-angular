<nav class="navbar navbar-inverse">
	<div class = "container-fluid">
		<div class = "navbag-header">
			<a class = "navbar-brand">MyBookStore</a>
		</div>
		<ul class = "nav navbar-nav">
		<li><button class="btn btn-link" ng-click="adminCtrl.goToLogin()">Login</button></li>
		<li><button class="btn btn-link active" ng-click="">Employees</button></li>
		<li><button class="btn btn-link active" ng-click="adminCtrl.goToBooks()">Books</button></li>
		</ul>
	</div>
</nav>

<!--    -----------------------------------------    Admin View    -------------------------------------------   -->
	
<div class="generic-container">
	
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Employees </span></div>
		
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="adminCtrl.successMessage">{{adminCtrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="adminCtrl.errorMessage">{{adminCtrl.errorMessage}}</div>
				
	            <form ng-submit="adminCtrl.submitEmployee()" name="form.myEmployeeForm" class="form-horizontal">
	                <input type="hidden" ng-model="adminCtrl.employee.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="ename">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="adminCtrl.employee.name" id="ename" class="username form-control input-sm" placeholder="Employee Name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="ecnp">Personal Numerical Code</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="adminCtrl.employee.pnc" id="ecnp" class="form-control input-sm" placeholder="Employee Personal Numerical Code" required ng-pattern="adminCtrl.onlyIntegers" ng-minlength="13"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="eaddress">Address</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="adminCtrl.employee.address" id="eaddress" class="form-control input-sm" placeholder="Employee Address" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit" value="Add" ng-click="adminCtrl.addingEmployee = true" class="btn btn-primary btn-sm" ng-disabled="form.myEmployeeForm.$invalid || form.myEmployeeForm.$pristine"/>
							<input type="submit" value="Update" ng-click="adminCtrl.updatingEmployee = true" class="btn btn-success btn-sm" ng-disabled="form.myEmployeeForm.$invalid || form.myEmployeeForm.$pristine"/>
	                        <button type="button" ng-click="adminCtrl.employeeReset()" class="btn btn-warning btn-sm" ng-disabled="form.myEmployeeForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
  
	
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Employees </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>PNC</th>
		                <th>NAME</th>
		                <th>ADDRESS</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="e in adminCtrl.getAllEmployees()">
		                <td>{{e.pnc}}</td>
		                <td>{{e.name}}</td>
		                <td>{{e.address}}</td>
		                <td><button type="button" ng-click="adminCtrl.editEmployee(e.id)" class="btn btn-info custom-width">Edit</button></td>
		                <td><button type="button" ng-click="adminCtrl.deleteEmployee(e.id)" class="btn btn-danger custom-width">Delete</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>

