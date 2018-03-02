angular
    .module('myApp')
    .controller('FirstPageCtrl', ['$scope', 'SignInService', '$location', function($scope, SignInService, $location) {
   //.controller('ClientSignupController', ['$scope', 'ClientSignupService', '$location', function($scope, ClientSignupService, $location) {

        var vm = this;
        vm.clientSignUp =  clientSignUp;
        vm.userSignIn = userSignIn;
        vm.username;
        vm.password;
        //vm.userMatched;
        function clientSignUp(){
            $location.path('/clientSignUp');
        }

        function userSignIn() {
            checkLogIn(vm.username, vm.password);
            vm.username = '';
            vm.password = '';
        }


        function checkLogIn(uname,pword){
            SignInService.checkLogIn(uname,pword)
                .then(
                    function(response){
                        //return matchedUser();
                        console.log(response);
                        alert("user matched");
                    },
                    function(errResponse){
                        alert('Error while matching username and password');
                    }
                );

        }

    }]);

