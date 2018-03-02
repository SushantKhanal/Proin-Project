(function(){
    'use strict';
    angular
        .module("myApp")
        .config(routeFunc)
    function routeFunc($routeProvider){

        $routeProvider
            .when('/', {templateUrl: 'Pages/FirstPage/firstPage.jsp'})
            .when('/clientSignUp', {templateUrl: 'Pages/UserSignUp/SignUp.jsp'})
    }
})();