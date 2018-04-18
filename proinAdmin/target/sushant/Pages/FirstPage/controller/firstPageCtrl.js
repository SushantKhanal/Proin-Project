//THIS IS THE CONTROLLER FOR THE FIRST PAGE FROM WHERE ADMINS CAN SIGN IN OR SIGN UP
angular
    .module('myApp')
    .controller('FirstPageCtrl', firstPageController);

firstPageController.$inject = ['AdminSignInService', '$location'];

function firstPageController(AdminSignInService, $location) {

    var vm = this;
    vm.username;
    vm.password;
    vm.adminSignIn = adminSignIn;
    vm.adminSignUp = adminSignUp;

    $("#passwordAdmin").on('keydown', function(e) {
        if (e.which == 13 || event.keyCode == 13) {
            //e.preventDefault();
            adminSignIn();
        }
    });

    function adminSignIn() {
        AdminSignInService.checkLogIn(vm.username, vm.password)
            .then(function(r){
                vm.username = '';
                vm.password = '';
                if(r !== '') {
                    vm.admin = r;
                    localStorage['adminInfo'] = JSON.stringify(vm.admin);
                }

                localStorage['adminLoggedIn'] = JSON.stringify(true);

                $location.path('/adminAccount'); //THE ADMIN ACCOUNT IS SHOWN

            },function(errorResponse) {
                vm.username = '';
                vm.password = '';
                alert("error matching admin username, password");
            })
    }

    function adminSignUp() {
        alert("You want to sign up?");
        $location.path('/adminAccount/signUp/');
    }

}