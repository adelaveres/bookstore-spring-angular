<nav class="navbar navbar-inverse">
	<div class = "container-fluid">
		<div class = "navbag-header">
			<a class = "navbar-brand">MyBookStore</a>
		</div>
		<ul class = "nav navbar-nav">
		<li><button class="btn btn-link" ng-click="employeeCtrl.goToLogin()">Login</button></li>
		</ul>
	</div>
</nav>


<div class="generic-container">
	
    <div class="panel panel-default">
			
		<div class="alert alert-success" role="alert" ng-if="employeeCtrl.successMessage">{{employeeCtrl.successMessage}}</div>
		<div class="alert alert-danger" role="alert" ng-if="employeeCtrl.errorMessage">{{employeeCtrl.errorMessage}}</div>
		
		<div class="panel-body">
			
			<div class="row" style="margin-bottom:20px;">
				<div class="col-md-12">
					<label class="col-md-1 lable label-style" for="search">Search:</label>	
					<div class="col-md-6">
						<input type="text" id="search" class="form-control input-sm" ng-model="employeeCtrl.searchedString" ng-change="employeeCtrl.searchBooks=true" placeholder="..."></input>
					</div>
					<div class="col-md-5">
						<select ng-model="employeeCtrl.searchedCriterion" ng-change="employeeCtrl.searchBooks=true">
							<option ng-repeat="x in employeeCtrl.searchingCriteria">{{x}}</option>
						</select>
					</div>
				</div>
			</div>
			
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>Id</th>
		                <th>Author</th>
						<th>Title</th>
		                <th>Genre</th>
		                <th>Quantity</th>
						<th>Price</th>
		                <th width="200"></th>
						
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="book in employeeCtrl.getAllBooks()">
		                <td>{{book.id}}</td>
						<td>{{book.author}}</td>
						<td>{{book.title}}</td>
		                <td>{{book.genre}}</td>
		                <td>{{book.quantity}}</td>
		                <td>{{book.price | currency}}</td>
						
						<td>
							<form ng-submit="employeeCtrl.sellBooks(book.id)" name="form.mySellingForm" class="form-horizontal">
								<div class="row">
									<div class="col-md-1"></div>
									<input type="text" class="col-md-3"  ng-model="employeeCtrl.noOfBooksToSell[book.id]" placeholder="no." required ng-pattern="employeeCtrl.onlyNumbers" />
									<div class="col-md-1"></div>
									<div class="form-actions col-md-7">
										<input type="submit" value="Sell" class="btn btn-warning custom-width" ng-disabled="form.mySellingForm.$invalid || form.mySellingForm.$pristine"/>
									</div>
								</div>
							</form>
						</td>
					</tr>
		            </tbody>
		        </table>		
			</div>
		</div>
	</div>
</div>