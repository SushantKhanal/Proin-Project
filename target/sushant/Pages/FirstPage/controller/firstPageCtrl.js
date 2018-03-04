//THIS IS THE CONTROLLER FOR THE FIRST PAGE FROM WHERE CLIENTS AND ADMINS CAN SIGN IN OR SIGN UP
angular
    .module('myApp')
    .controller('FirstPageCtrl', firstPageController);

firstPageController.$inject = ['$scope', 'ClientSignInService', '$location'];

function firstPageController($scope, ClientSignInService, $location) {

    var vm = this;
    vm.userSignUp =  userSignUp;
    vm.userSignIn = userSignIn;
    vm.username;
    vm.password;

    //WHEN USER-SIGN-UP BUTTON IS CLICKED, USER-SIGN-UP PAGE IS DISPLAYED
    function userSignUp(){
        $location.path('/userSignUp');
    }

    //WHEN USER-SIGN-IN BUTTON IS CLICKED, CHECKLOGIN FUNCTION IS CALLED
    function userSignIn() {
        checkLogIn(vm.username, vm.password);
        vm.username = '';
        vm.password = '';
    }

    //THIS FUNCTION TAKES THE USERNAME AND PASSWORD PROVIDED TO THE BACKEND. THE PROFILE THAT MATCHES IS RETURNED
    function checkLogIn(uname,pword){
        ClientSignInService.checkLogIn(uname,pword)
            .then(
                function(response){
                    console.log(response);
                    //THE RETURNED USER DATA IS SAVED ON A VARIABLE IN CLIENTSIGNIN SERVICE, SO THAT IT CAN BE ACCESSED THROUGH OTHER CONTROLLERS AS WELL
                    ClientSignInService.setResponse(response);
                    $location.path('/userAccount'); //THE PARTICULAR USER ACCOUNT IS SHOWN
                },
                function(errResponse){
                    alert('Error while matching username and password');
                }
            );
    }

}
