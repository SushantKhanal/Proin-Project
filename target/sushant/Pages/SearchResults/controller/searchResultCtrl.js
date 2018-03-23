angular
    .module('myApp')
    .controller('SearchResultCtrl', searchResultController);

searchResultController.$inject = ['$location', 'SearchResultsService', 'FavouritesService','$scope'];

function searchResultController($location, SearchResultsService, FavouritesService, $scope) {
    var vm = this;
    vm.goBack = goBack;
    vm.searchResults = searchResults;
    vm.displayProfile = displayProfile;

    vm.picPath1 = '';
    vm.users = []; //make this persist on refresh
    vm.user;
    vm.countries;
    vm.searchThis;
    $scope.country;

    getCountries();


    function displayProfile(profile) {
        vm.username = profile;
        FavouritesService.getUserProfile(vm.username)
            .then(
                function(d) {
                    vm.user = d;
                    localStorage['localOtherUser'] = JSON.stringify(vm.user);
                    $location.path('/searchResults/otherAccount');
                },
                function(errResponse){
                    console.error('Error while fetching fav user names');
                }
            );

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
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

}
