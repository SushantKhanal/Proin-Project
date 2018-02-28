<div class="generic-container" ng-controller="FirstPageCtrl as xxx">
    <div class="panel panel-default">

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-4 control-label">Client SignIn</label>
                <br>
            <%--<form>--%>
                    Username:<input type="text" id="usernameClient" required><br>
                    Password:<input type="password"  id="passwordClient" required><br>
                    <button type="button">Log in</button>
                <%--</form>--%>
            </div>
        </div>


        <div class="row">
            <div class="col-md-12">
                <label class="col-md-4 control-label">Client SignUp</label>
                <br>
                <button ng-click="xxx.clientSignUp()">Client SignUp</button>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-4 control-label">Admin SignIn</label>
                <br>
                <%--<form>--%>
                Username:<input type="text" id="usernameAdmin" required><br>
                Password:<input type="password"  id="passwordAdmin" required><br>
                <button type="button">Log in</button>
                <%--</form>--%>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <label class="col-md-4 control-label">Admin SignUp</label>
                <br>
                <button>Admin SignUp</button>
            </div>
        </div>
        </div>
</div>
