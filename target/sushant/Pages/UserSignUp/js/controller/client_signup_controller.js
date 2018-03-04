//THIS IS THE CONTROLLER FOR CLIENT SIGN UP PAGE
'use strict';

angular
    .module('myApp')
    .controller('ClientSignupController', clientSignUpController);

clientSignUpController.$inject = ['$scope', 'ClientSignupService', '$location'];

function clientSignUpController($scope, ClientSignupService, $location) {
    var vm = this;
    vm.user={id: null, clientType:'', username:'', password:'', address:'',email:'',agenda:'',academics: '',experience: '', marketDomain: ''};
    vm.users=[];

    vm.ifPersonal = ifPersonal;
    vm.ifCorporate = ifCorporate;

    vm.submit = submit;
    vm.reset = reset;
    vm.none = none;
    vm.close = close;

    function ifPersonal() {
        vm.element = $("#personalButton");
        vm.element.toggleClass("btn-warning");
        vm.personal = false;//for ng-show
        vm.corporate = false;//for ng-show
        if (($("#corporateButton").hasClass("btn-warning")) && ($("#personalButton").hasClass("btn-warning"))) {
            $("#corporateButton").toggleClass("btn-warning");
        }
        if($("#personalButton").hasClass("btn-warning")) {
            vm.personal = true;
            vm.requirePersonal = true;
            vm.requireCorporate = false;
            vm.user.marketDomain = '';
            vm.user.clientType = 'Personal';
        }
    }

    function ifCorporate() {
        vm.element = $("#corporateButton");
        vm.element.toggleClass("btn-warning");
        vm.corporate=false;
        vm.personal=false;
        if (($("#corporateButton").hasClass("btn-warning")) && ($("#personalButton").hasClass("btn-warning"))) {
            $("#personalButton").toggleClass("btn-warning");
        }

        if ($("#corporateButton").hasClass("btn-warning")) {
            vm.corporate=true;
            vm.requireCorporate = true;
            vm.requirePersonal = false;
            vm.user.experience = '';
            vm.user.academics = '';
            vm.user.clientType = 'Corporate';
        }
    }
//THE FOLLOWING FUNCTION IS RESPONSIBLE FOR UN-CLICKING BOTH 'PERSONAL' AND 'CORPORATE' BUTTONS ON RESET OR SUBMISSION
    function none() {
        vm.corporate = false;
        vm.personal = false;
        vm.requirePersonal = true;
        vm.requireCorporate = true;
        if ($("#corporateButton").hasClass("btn-warning")) {
            $("#corporateButton").toggleClass("btn-warning");
        }
        if($("#personalButton").hasClass("btn-warning")) {
            $("#personalButton").toggleClass("btn-warning");
        }
    }

    function submit() {
        //console.log(vm.user);
        createUser(vm.user);
        vm.reset();
        vm.none();
    }

    function reset(){
        vm.user={id: null, clientType:'', username:'',address:'',email:'',agenda:'',academics: '',experience: '', marketDomain: ''};
        $scope.myForm.$setPristine();
    }

//THE FOLLOWING FUNCTION IS CLICKED ON FORM SUBMISSION
    function createUser(user){
        ClientSignupService.createUser(user)
            .then(
                function(response){
                    return fetchAllUsers();
                },
                function(errResponse){
                    console.error('Error while creating User');
                }
            )
            .then(
                function(d){
                    alert("Your Sign Up is successful, " + vm.users[(d.length -1)].username);
                    vm.close();
                });
    }

    function fetchAllUsers(){
        return ClientSignupService.fetchAllUsers()
            .then(
                function(d) {
                    vm.users = d;
                    vm.index = (vm.users.length - 1);
                    console.log(vm.users);
                    return d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }
//THE FOLLOWING FUNCTION IS FOR CLOSING THE SIGN UP PAGE
    function close() {
        vm.reset();
        vm.none();
        $location.path('/');
    }

}
