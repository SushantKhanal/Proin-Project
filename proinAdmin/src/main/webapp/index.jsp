<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset ="utf-8">
    <title>Proin Project</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-animate.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-touch.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/1.3.3/ui-bootstrap-tpls.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.5/angular-route.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/angular-base64-upload@0.1.23/dist/angular-base64-upload.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="/Common/css/app.css" rel="stylesheet"/>
    <%--'/Common/css' refers to the ClientSide CSS; 'Common/css' refers to the admin side css --%>

</head>
<body ng-app="myApp" class="ng-cloak">

    <div ng-view></div>

    <script src = "app.js"></script>
    <script src="Common/RouteProvider/routeProvider.js"></script>
    <script src="Common/HttpService.js"></script>
    <script src="Common/factory/ModalFactory.js"></script>
    <script src="Pages/FirstPage/controller/firstPageCtrl.js"></script>
    <script src="Pages/FirstPage/services/admin_signIn_service.js"></script>
    <script src="Pages/AdminAccountPage/controller/adminAccountCtrl.js"></script>
    <script src="Pages/AdminAccountPage/services/admin_account_service.js"></script>
    <script src="Pages/ClientAccountPage/controller/clientAccountCtrl.js"></script>
    <script src="Pages/ClientAccountPage/controller/favouritesCtrl.js"></script>
    <script src="Pages/ClientAccountPage/services/client_account_service.js"></script>
    <script src="Pages/ClientAccountPage/services/favourites_service.js"></script>

    <script src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</body>
</html>
