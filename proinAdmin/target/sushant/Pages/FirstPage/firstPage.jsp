<div class="col-sm-5" ng-controller="FirstPageCtrl as xxx">
    <h2>SignIn and SignUp for admins</h2>
    <div class="form-box">
        <div class="form-top">
            <div class="form-top-left">
                <h3>Login for our Admins</h3>
                <p>Enter username and password to log on:</p>
            </div>
        </div>

        <div class="form-bottom">
            <form role="form" action="" method="post" class="login-form">
                <div class="form-group">
                    <label class="sr-only">Username</label>
                    <input type="text" ng-model="xxx.username" placeholder="Username..." class="form-username form-control" id="usernameAdmin">
                </div>
                <div class="form-group">
                    <label class="sr-only">Password</label>
                    <input type="password" ng-model="xxx.password" placeholder="Password..." class="form-password form-control" id="passwordAdmin">
                </div>
                <button type="button" class="btn btn-primary" ng-click="xxx.adminSignIn()">Sign in!</button>
            </form>
            <div class="social-login">
                <button class="btn btn-warning" ng-click="xxx.adminSignUp()">SignUp for Admins</button>
            </div>
        </div>
    </div>
    <%--*************************************************************--%>
</div>