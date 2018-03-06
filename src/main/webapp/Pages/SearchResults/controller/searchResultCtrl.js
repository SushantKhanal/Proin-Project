angular
    .module('myApp')
    .controller('SearchResultCtrl', searchResultController);

searchResultController.$inject = ['$location'];

function searchResultController($location) {
    var vm = this;
    vm.goBack = goBack;
    function goBack(){
        $location.path('/userAccount');
    }
}