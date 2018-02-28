angular
    .module('myApp')
    .controller('FirstPageCtrl', ['$scope', '$location', function($scope, $location) {
        var vm = this;
        vm.clientSignUp =  clientSignUp;

        function clientSignUp(){
            $location.path('/signup');
        }

    }])

