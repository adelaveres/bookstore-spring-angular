<nav class="navbar navbar-inverse">
	<div class = "container-fluid">
		<div class = "navbag-header">
			<a class = "navbar-brand">MyBookStore</a>
		</div>
		<ul class = "nav navbar-nav">
		<li><button class="btn btn-link" ng-click="booksCtrl.goToLogin()">Login</button></li>
		<li><button class="btn btn-link active" ng-click="booksCtrl.goToAdmin()">Employees</button></li>
		<li><button class="btn btn-link active" ng-click="">Books</button></li>
		</ul>
	</div>
</nav>


<div class="generic-container">
	
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Books</span></div>
		
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="booksCtrl.successMessage">{{booksCtrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="booksCtrl.errorMessage">{{booksCtrl.errorMessage}}</div>
				
	            <form ng-submit="booksCtrl.submitBook()" name="form.myBooksForm" class="form-horizontal">
	                <input type="hidden" ng-model="booksCtrl.book.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="btitle">Title</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="booksCtrl.book.title" id="btitle" class="username form-control input-sm" placeholder="Book Title" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="bauthor">Author</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="booksCtrl.book.author" id="bauthor" class="form-control input-sm" placeholder="Book Author" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="bgenre">Genre</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="booksCtrl.book.genre" id="bgenre" class="form-control input-sm" placeholder="Book Genre" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
					
					<div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="bquantity">Quantity</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="booksCtrl.book.quantity" id="bquantity" class="form-control input-sm" placeholder="Number of Copies" required ng-pattern="booksCtrl.onlyIntegers" ng-minlength="1"/>
	                        </div>
	                    </div>
	                </div>
					
					<div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="bprice">Price</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="booksCtrl.book.price" id="bprice" class="form-control input-sm" placeholder="Book Price" required ng-pattern="booksCtrl.onlyNumbers" ng-minlength="1"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit" value="Add" ng-click="booksCtrl.addingBook= true" class="btn btn-primary btn-sm" ng-disabled="form.myBooksForm.$invalid || form.myBooksForm.$pristine"/>
							<input type="submit" value="Update" ng-click="booksCtrl.updatingBook= true" class="btn btn-success btn-sm" ng-disabled="form.myBooksForm.$invalid || form.myBooksForm.$pristine"/>
	                        <button type="button" ng-click="booksCtrl.booksReset()" class="btn btn-warning btn-sm" ng-disabled="form.myBooksForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
		
		<div class="panel-heading"><span class="lead" style="font-color:red;">Out-of-stock books:</span></div>
		<div class="panel-body">
			<div style="margin-left:10px;">
				<!--form method="get" action="pdfReport.pdf"-->
					<a style="margin: 5px 5px;" href="http://localhost:8080/BookStore/api/pdfReport.pdf" download>pdf-Report</a>
					<a style="margin: 5px 5px;" href="http://localhost:8080/BookStore/api/csvReport.csv" download>csv-Report</a>
					<!--button type="submit" onclick="window.open('pdfReport.pdf')">Download!</button-->
					<!--button type="button" ng-click="booksCtrl.generateReport('pdf')" class="btn btn-small">pdf-Report</button-->
					<!--button type="button" ng-click="booksCtrl.generateReport('csv')" class="btn btn-small">csv-Report</button-->
				<!--/form-->
			</div>
		</div>

    </div>
	
    <div class="panel panel-default">
		
		<div class="panel-body">
			
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
		                <th width="100"></th>
						<th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="book in booksCtrl.getAllBooks()">
		                <td>{{book.id}}</td>
						<td>{{book.author}}</td>
						<td>{{book.title}}</td>
		                <td>{{book.genre}}</td>
		                <td>{{book.quantity}}</td>
		                <td>{{book.price | currency}}</td>
		                <td><button type="button" ng-click="booksCtrl.editBook(book.id)" class="btn btn-info custom-width">Edit</button></td>
		                <td><button type="button" ng-click="booksCtrl.deleteBook(book.id)" class="btn btn-danger custom-width">Delete</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
	</div>
</div>