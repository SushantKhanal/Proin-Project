(function(){
    'use strict';
    angular
        .module("myApp")
        .config(routeFunc);

    function routeFunc($routeProvider){

        $routeProvider
            .when('/', {templateUrl: 'Pages/FirstPage/firstPage.jsp'})
            // .when('/adminAccount', {templateUrl: 'Pages/AdminAccountPage/adminAccount.jsp'})
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
    }
})();



           // .when('/', {templateUrl: 'Pages/FirstPage/firstPage.jsp'})
            //.when('/userSignUp', {templateUrl: 'Pages/UserSignUp/ClientSignUp.jsp'})
            // .when('/userAccount', {
            //     resolve: {
            //         "check": function($location){
            //             if(localStorage['ifloggedin']!=='true') {
            //                 $location.path('/')
            //             }
            //         }
            //     },
            //     templateUrl: 'Pages/UserAccount/userAccount.jsp'
            //
            // })

