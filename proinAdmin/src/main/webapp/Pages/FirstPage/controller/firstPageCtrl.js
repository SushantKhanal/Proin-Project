//THIS IS THE CONTROLLER FOR THE FIRST PAGE FROM WHERE ADMINS CAN SIGN IN OR SIGN UP
angular
    .module('myApp')
    .controller('FirstPageCtrl', firstPageController);

firstPageController.$inject = ['AdminSignInService'];

function firstPageController(AdminSignInService) {

    var vm = this;
    vm.username;
    vm.password;
    vm.adminSignIn = adminSignIn;

    function adminSignIn() {
        //console.log(vm.username, vm.password);
        AdminSignInService.checkLogIn(vm.username, vm.password)
            .then(function(r){
                console.log("response", r);
            },function(errorResponse) {
                console.log(errorResponse);
            })

    }

}