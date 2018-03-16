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
    vm.showList = false; //make this persist on refresh
    vm.showAccount = false;
    vm.showResult = true;
    vm.picPath1 = '';
    vm.users = []; //make this persist on refresh
    vm.user;
    vm.countries;
    vm.searchThis;
    $scope.country;

    getCountries();

    //TO MAKE THE RESULTS LIST PERSIST ON REFRESH
    // var localUserData = localStorage['localOtherUser'];
    //
    // if(localUserData !== undefined) {
    //     vm.user = JSON.parse(localUserData);
    // }
    //
    // var localShowList1 = localStorage['localShowList'];
    //
    // if(localShowList1 !== undefined) {
    //     vm.users = JSON.parse(localShowList1);
    // }
    //*********************************************//


    function displayProfile(profile) {
        vm.user = profile;
        localStorage['localOtherUser'] = JSON.stringify(vm.user);
        $location.path('/searchResults/otherAccount')

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
                    // localStorage['localUsers'] = JSON.stringify(vm.users);

                    console.log(vm.users);
                    if (vm.users !== []) {
                        vm.showList = true;
                        //localStorage['localShowList'] = JSON.stringify(true);
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
