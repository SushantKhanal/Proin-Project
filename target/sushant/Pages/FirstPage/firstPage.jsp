<!-- Top content -->
<h3 class="heading">Welcome to PROIN app. If you don't have an account, please sign up.</h3>
<div class="top-content" ng-controller="FirstPageCtrl as xxx">
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-5">

                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>Login for our clients</h3>
                                <p>Enter username and password to log on:</p>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form role="form" action="" method="post" class="login-form">
                                <div class="form-group">
                                    <label class="sr-only">Username</label>
                                    <input type="text" ng-model="xxx.username" placeholder="Username..." class="form-username form-control" id="usernameClient">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only">Password</label>
                                    <input type="password" ng-model="xxx.password" placeholder="Password..." class="form-password form-control" id="passwordClient">
                                </div>
                                <button type="button" ng-click="xxx.userSignIn()" class="btn btn-primary">Sign in!</button>
                            </form>
                            <div class="social-login">
                                <button class="btn btn-warning" ng-click="xxx.clientSignUp()">SignUp for Clients</button>
                            </div>
                        </div>
                    </div>



                </div>

                <div class="col-sm-1 middle-border"></div>
                <div class="col-sm-1"></div>

                <div class="col-sm-5">

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
                                    <input type="text" placeholder="Username..." class="form-username form-control" id="usernameAdmin">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only">Password</label>
                                    <input type="password" placeholder="Password..." class="form-password form-control" id="passwordAdmin">
                                </div>
                                <button type="button" class="btn btn-primary">Sign in!</button>
                            </form>
                            <div class="social-login">
                                <button class="btn btn-warning">SignUp for Admins</button>
                            </div>
                        </div>
                    </div>

                </div>
                </div>
        </div>
    </div>
</div>