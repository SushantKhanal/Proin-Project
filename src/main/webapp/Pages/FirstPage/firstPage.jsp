<div ng-controller="FirstPageCtrl as xxx">
    <div>
        <h3>Client SignIn</h3>
        <form>
            Username:<input type="text" id="usernameClient" required><br>
            Password:<input type="password"  id="passwordClient" required>
            <br>
            <button type="button">Log in</button>
        </form>
        <br>
    </div>
    <h3>Client SignUp</h3>
    <button ng-click="xxx.clientSignUp()">Client SignUp</button>
    <br>
    <div>
        <h3>Admin SignIn</h3>
        <form>
            Username:<input type="text" id="usernameAdmin" required><br>
            Password:<input type="password"  id="passwordAdmin" required>
            <br>
            <button type="button">Log in</button>
        </form>
    </div>
    <br>
    <h3>Admin SignUp</h3>
    <button>Admin SignUp</button>
</div>
