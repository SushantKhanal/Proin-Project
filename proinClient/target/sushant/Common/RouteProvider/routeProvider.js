(function(){
    'use strict';
    angular
        .module("myApp")
        .config(routeFunc);

    function routeFunc($routeProvider){

        $routeProvider
            .when('/', {templateUrl: 'Pages/FirstPage/firstPage.jsp'})
            .when('/userSignUp', {templateUrl: 'Pages/UserSignUp/ClientSignUp.jsp'})
            .when('/userAccount', {
                resolve: {
                    "check": function($location){
                        if(localStorage['ifloggedin']!=='true') {
                            $location.path('/')
                        }
                    }
                },
                templateUrl: 'Pages/UserAccount/userAccount.jsp'

            })
            .when('/userAccount/searchResults', {
                resolve: {
                    "check": function($location){
                        if(localStorage['ifloggedin']!=='true') {
                            $location.path('/')
                        }
                    }
                },
                templateUrl: 'Pages/SearchResults/SearchResults.jsp'

            })
            .when('/searchResults/otherAccount', {templateUrl: 'Pages/OtherAccount/otherAccount.jsp'})
            .when('/searchResults/otherUser', {templateUrl: 'Pages/OtherAccount/otherAccount.jsp'})
            .when('/userAccount/followers', {templateUrl: 'Pages/FollowersPage/NormalFollowers.jsp'})
        //'/searchResults/otherAccount'
    }
})();
