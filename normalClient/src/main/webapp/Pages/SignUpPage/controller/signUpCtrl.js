//THIS IS THE CONTROLLER FOR CLIENT SIGN UP PAGE
'use strict';

angular
    .module('myApp')
    .controller('ClientSignupController', clientSignUpController);

clientSignUpController.$inject = ['$scope', '$location', 'SignupService'];

function clientSignUpController($scope, $location, SignupService) {
    var vm = this;
    vm.user={id: null, firstName:'', lastName:'', dob:'', bio:'', nation:'', username:'',
        password:'', address:'', email:'', agenda:'', joinDate:'', academics:'', experience: ''};

    vm.users=[];

    vm.submit = submit;
    vm.reset = reset;
    vm.close = close;


    function submit() {
        vm.user.joinDate = new Date();
        console.log(vm.user);
        createUser(vm.user);
        vm.reset();
    }

    function reset(){
        vm.user={id:null, firstName:'', lastName:'', dob:'', bio:'', nation:'', clientType:'', username:'', password:'', address:'', email:'', agenda:'', joinDate:'', academics:'', experience: '', marketDomain: ''};
        $scope.myForm.$setPristine();
    }

// THE FOLLOWING FUNCTION IS CLICKED ON FORM SUBMISSION
    function createUser(user){
        SignupService.createUser(user)
            .then(
                function(data){
                    // alert("Your Sign Up request is registered, " + data.username);
                    vm.close();
                },
                function(errResponse){
                    console.error('Error while creating User');
                }
            );

    }

//THE FOLLOWING FUNCTION IS FOR CLOSING THE SIGN UP PAGE
    function close() {
        vm.reset();
        $location.path('/');
    }

}
