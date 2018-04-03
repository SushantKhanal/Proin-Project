(function(){
    'use strict';
    angular
        .module("myApp")
        .config(routeFunc);

    function routeFunc($routeProvider){

        $routeProvider
            .when('/', {templateUrl: 'Pages/FirstPage/firstPage.jsp'})
            .when('/userSignUp', {templateUrl: 'Pages/SignUpPage/signUp.jsp'})
            .when('/normalUserAccount', {
                resolve: {
                    "check": function($location){
                        if(localStorage['ifNormalloggedin']!=='true') {
                            $location.path('/')
                        }
                    }
                },
                templateUrl: 'Pages/UserAccountPage/userAccount.jsp'

            })
    }
})();



