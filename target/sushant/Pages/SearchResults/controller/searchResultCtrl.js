angular
    .module('myApp')
    .controller('SearchResultCtrl', searchResultController);

searchResultController.$inject = ['$location', 'SearchResultsService', 'UserAccountService','$scope'];

function searchResultController($location, SearchResultsService, UserAccountService, $scope) {
    var vm = this;
    vm.goBack = goBack;
    vm.searchResults = searchResults;
    vm.displayProfile = displayProfile;
    vm.backToSearch = backToSearch;
    vm.showList = false;
    vm.showAccount = false;
    vm.showResult = true;
    vm.picPath1 = '';
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
        vm.user = profile;
        vm.showResult = false;
        vm.showAccount = true;

        getProfilePic(vm.user.username);

    }

    function getProfilePic(username){
        UserAccountService.getProfilePic(username)
            .then(
                function(d) {
                    vm.userProfilePic =d;
                    //var profilePicElement = document.getElementById('profile-image1');
                    vm.picPath1 = '/user'+d.picPath;
                    //profilePicElement.setAttribute('src', '/user' + d.picPath);
                },
                function(errResponse){
                    console.error('Error while getting profilePic');
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
