(function(){
    'use strict';
    angular
        .module("myApp")
        .config(routeFunc)

    function routeFunc($routeProvider){

        $routeProvider
            .when('/', {templateUrl: 'Pages/FirstPage/firstPage.jsp'})
            .when('/userSignUp', {templateUrl: 'Pages/UserSignUp/SignUp.jsp'})
            .when('/userAccount', {templateUrl: 'Pages/UserAccount/userAccount.jsp'})
    }
})();
