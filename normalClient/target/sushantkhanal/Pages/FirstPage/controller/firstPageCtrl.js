//THIS IS THE CONTROLLER FOR THE FIRST PAGE FROM WHERE CLIENTS CAN SIGN IN OR SIGN UP
angular
    .module('myApp')
    .controller('FirstPageCtrl', firstPageController);

firstPageController.$inject = ['$location', 'SignInService'];

function firstPageController($location, SignInService) {

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
        SignInService.fetchNormalUser(uname, pword)
            .then(
                function(response){
                    vm.user = response;
                    vm.user.dob = new Date(vm.user.dob);
                    vm.user.dob = vm.user.dob.getFullYear()+'-'+(vm.user.dob.getMonth()+1)+'-'+vm.user.dob.getDate();
                    vm.user.joinDate = new Date(vm.user.joinDate);
                    vm.user.joinDate = vm.user.joinDate.getFullYear()+'-'+(vm.user.joinDate.getMonth()+1)+'-'+vm.user.joinDate.getDate();

                    localStorage['NormalUserInfo'] = JSON.stringify(vm.user);

                    localStorage['ifNormalloggedin'] = JSON.stringify(true);

                    $location.path('/normalUserAccount'); //THE PARTICULAR USER ACCOUNT IS SHOWN
            },
                function(){
                alert("Username, password didn't match");
            })
    }

}

