'use strict';

angular
    .module('myApp')
    //.controller('ClientSignupController', ['$scope', 'ClientSignupService', function($scope, ClientSignupService) {
    .controller('ClientSignupController', ['$scope', function($scope) {
        var vm = this;
        vm.user={id: null, clientType:'', username:'',address:'',email:'',agenda:'',academics: '',experience: '', marketDomain: ''};
        vm.users=[];

        vm.ifPersonal = ifPersonal;
        vm.ifCorporate = ifCorporate;

        vm.submit = submit;
        vm.reset = reset;

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

        function submit() {
            console.log(vm.user);
            //createUser(vm.user);
            vm.reset();
        }


        function reset(){
            vm.user={id: null, clientType:'', username:'',address:'',email:'',agenda:'',academics: '',experience: '', marketDomain: ''};
            $scope.myForm.$setPristine();
        }


        // function createUser(user){
        //     ClientSignupService.createUser(user)
        //         .then(
        //             fetchAllUsers,
        //             function(errResponse){
        //                 console.error('Error while creating User');
        //             }
        //         );
        // }

        // function fetchAllUsers(){
        //     ClientSignupService.fetchAllUsers()
        //         .then(
        //             function(d) {
        //                 vm.users = d;
        //             },
        //             function(errResponse){
        //                 console.error('Error while fetching Users');
        //             }
        //         );
        // }

}]);
