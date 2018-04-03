//THIS IS THE CONTROLLER FOR THE FIRST PAGE FROM WHERE CLIENTS CAN SIGN IN OR SIGN UP
angular
    .module('myApp')
    .controller('FirstPageCtrl', firstPageController);

firstPageController.$inject = ['$location'];

function firstPageController($location) {

    var vm = this;
    vm.userSignUp =  userSignUp;
    vm.userSignIn = userSignIn;
    vm.username;
    vm.password;
    vm.user;

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
        alert("you tried to sign in");
    }

}

