
<!--    -----------------------------------------    Login    -------------------------------------------   -->


<div class="generic-container" style="text-align:center; margin: 5% 30%; max-width:40%;">
	<div class="panel panel-default">
		<div class="panel-heading"><span class="lead">Login</span></div>
		<div class="alert alert-success" role="alert" ng-if="loginCtrl.successMessage">{{loginCtrl.successMessage}}</div>
		<div class="alert alert-danger" role="alert" ng-if="loginCtrl.errorMessage">{{loginCtrl.errorMessage}}</div>

		
		
		<div class="panel-body">
			<div class="formcontainer">
				<form ng-submit="loginCtrl.login()" name="loginForm" class="form-horizontal">
					<input type="hidden" ng-model="loginCtrl.loginAccount.loginId" />
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="username">Username</label>
							<div class="col-md-7">
								<input type="text" ng-model="loginCtrl.loginAccountUsername" id="username" class="username form-control input-sm" placeholder="username" required ng-minlength="3"/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="pass">Password</label>
							<div class="col-md-7">
								<input type="password" ng-model="loginCtrl.loginAccountPassword" id="pass" class="form-control input-sm" placeholder="password" required ng-minlength="3"/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-actions" style="text-align:center">
							<input type="submit" value="Login" class="btn btn-primary" ng-disabled="loginForm.$invalid || loginForm.$pristine"/>
						</div>
					</div>
					
					
				</form>
			</div>
		</div>
	</div>
</div>

