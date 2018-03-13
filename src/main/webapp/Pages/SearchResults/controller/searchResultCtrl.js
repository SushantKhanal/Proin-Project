angular
    .module('myApp')
    .controller('SearchResultCtrl', searchResultController);

searchResultController.$inject = ['$location', 'SearchResultsService', '$scope'];

function searchResultController($location, SearchResultsService, $scope) {
    var vm = this;
    vm.goBack = goBack;
    vm.searchResults = searchResults;
    vm.displayProfile = displayProfile;
    vm.backToSearch = backToSearch;
    vm.showList = false;
    vm.showAccount = false;
    vm.showResult = true;
    vm.users = [];
    vm.user;
    vm.countries;
    vm.searchThis;
    $scope.country;

    getCountries();

    function backToSearch() {
        vm.showAccount = false;
        vm.showResult = true;
    }

    function displayProfile(profile) {
        //$location.path('/otherUserAccount');
        vm.user = profile;
        //$scope.$apply();
        vm.showResult = false;
        vm.showAccount = true;
    }

    function goBack(){
        $location.path('/userAccount');
    }

    function searchResults() {
        console.log(vm.searchThis, $scope.country);
        SearchResultsService.getMatchedUsers(vm.searchThis, $scope.country)
            .then(
                function(u) {
                    vm.users = u;
                    console.log(vm.users);
                    if (vm.users !== []) {
                        vm.showList = true;
                    }
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function getCountries() {
        SearchResultsService.getCountries()
            .then(
                function(c) {
                    vm.countries = c;
                    console.log(vm.countries);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

}
