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
    //vm.userMatched;

    function userSignUp(){
        $location.path('/userSignUp');
    }

    function userSignIn() {
        checkLogIn(vm.username, vm.password);
        vm.username = '';
        vm.password = '';
    }

    function checkLogIn(uname,pword){
        ClientSignInService.checkLogIn(uname,pword)
            .then(
                function(response){
                    console.log(response);
                    //alert("user matched");
                    ClientSignInService.setResponse(response);
                    $location.path('/userAccount');
                },
                function(errResponse){
                    alert('Error while matching username and password');
                }
            );
    }

}
