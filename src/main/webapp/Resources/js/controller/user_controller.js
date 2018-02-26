(function(){
    'use strict';
    angular
        .module("myApp")
        .controller('UserController',['$scope', 'UserService', function($scope, UserService){
            var vm = this;
            vm.user = {id:null, username: '', address: '', email: ''};
            vm.users = [];

            vm.submit = submit;
            vm.edit = edit;
            vm.remove = remove;
            vm.reset = reset;

            fetchAllUsers();

            function fetchAllUsers(){
                UserService.fetchAllUsers()
                    .then(
                        function(d){
                            vm.users = d;
                        },
                        function(errResponse){
                            console.log("error while fetching users");
                        }
                    )
            };

            function createUser(user){
                UserService.createUser(user)
                    .then(
                        fetchAllUsers(),
                        function(errResponse) {
                            console.log("error while creating user");
                        }
                    )
            }

            function updateUser(user, id){
                UserService.updateUser(user,id)
                    .then(
                        fetchAllUsers(),
                        function(errResponse) {
                            console.log("error while updating user");
                        }
                    )
            }

            function deleteUser(id) {
                UserService.deleteUser(id)
                    .then(
                        fetchAllUsers(),
                        function(errResponse) {
                            console.log("error while deleting user");
                        }
                    )
            }


        }])
})();