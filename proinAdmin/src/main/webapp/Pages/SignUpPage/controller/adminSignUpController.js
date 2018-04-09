//THIS IS THE CONTROLLER FOR CLIENT SIGN UP PAGE
'use strict';

angular
    .module('myApp')
    .controller('AdminSignUpController', adminSignUpController);

adminSignUpController.$inject = ['$scope', '$location'];

function adminSignUpController($scope, $location) {
    var vm = this;
    vm.admin={id: null, firstName:'', lastName:'', dob:'', bio:'', nation:'', username:'',
        password:'', address:'', email:'', agenda:'', joinDate:'', academics:'', experience: ''};
    
    vm.submit = submit;
    vm.reset = reset;
    vm.close = close;

    function submit() {
        vm.admin.joinDate = new Date();
        console.log(vm.admin);
        createadmin(vm.admin);
        vm.reset();
    }

    function reset(){
        vm.admin={id:null, firstName:'', lastName:'', dob:'', bio:'', nation:'', clientType:'', username:'', password:'', address:'', email:'', agenda:'', joinDate:'', academics:'', experience: '', marketDomain: ''};
        $scope.myForm.$setPristine();
    }

// THE FOLLOWING FUNCTION IS CLICKED ON FORM SUBMISSION
    function createadmin(admin){
        console.log(admin);
        // SignupService.createadmin(admin)
        //     .then(
        //         function(data){
        //             // alert("Your Sign Up request is registered, " + data.username);
        //             vm.close();
        //         },
        //         function(errResponse){
        //             console.error('Error while creating admin');
        //         }
        //     );

    }

//THE FOLLOWING FUNCTION IS FOR CLOSING THE SIGN UP PAGE
    function close() {
        vm.reset();
        $location.path('/');
    }

}
