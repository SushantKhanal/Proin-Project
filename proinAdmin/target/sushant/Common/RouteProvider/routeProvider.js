(function(){
    'use strict';
    angular
        .module("myApp")
        .config(routeFunc);

    function routeFunc($routeProvider){

        $routeProvider
            .when('/', {templateUrl: 'Pages/FirstPage/firstPage.jsp'})
            .when('/adminAccount', {
                resolve: {
                    "check": function($location){
                        if(localStorage['adminLoggedIn']!=='true') {
                            $location.path('/')
                        }
                    }
                },
                templateUrl: 'Pages/AdminAccountPage/adminAccount.jsp'

            })
            .when('/adminAccount/clientAccount', {templateUrl: 'Pages/ClientAccountPage/clientAccountPage.jsp'})
            .when('/adminAccount/clientAccount/differentPath', {templateUrl: 'Pages/ClientAccountPage/clientAccountPage.jsp'})
            .when('/adminAccount/signUp/', {templateUrl: 'Pages/SignUpPage/adminSignUp.jsp'})
    }
})();



