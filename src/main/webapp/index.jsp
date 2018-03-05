<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset ="utf-8">
    <title>AngularJS $http Example</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-animate.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-touch.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/1.3.3/ui-bootstrap-tpls.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.5/angular-route.js"></script>

    <link href="/Common/css/app.css" rel="stylesheet"/>
</head>
<body ng-app="myApp" class="ng-cloak">

    <div ng-view></div>

    <script src = "app.js"></script>
    <script src= "/Common/HttpService.js"></script>
    <script src="/Pages/FirstPage/controller/firstPageCtrl.js"></script>
    <script src="/Pages/FirstPage/services/client_signIn_service.js"></script>
    <script src="/Pages/UserSignUp/js/service/client_signup_service.js"></script>
    <script src="/Pages/UserSignUp/js/controller/client_signup_controller.js"></script>
    <script src="/Pages/UserAccount/controller/userAccountCtrl.js"></script>
    <script src="/Pages/UserAccount/services/user_account_service.js"></script>
    <script src="/Common/RouteProvider/routeProvider.js"></script>

    <script src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</body>
</html>