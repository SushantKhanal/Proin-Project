angular
    .module('myApp')
    .controller('SearchProCleintsCtrl', searchProCleintsCtrl);

searchProCleintsCtrl.$inject = ['SearchProClientsService'];

function searchProCleintsCtrl(SearchProClientsService) {

    var vm = this;
    var returnedValue = [];
    vm.counter = 0;
    vm.fetchId = fetchId;
    var ids = [];
    vm.goBack = goBack;
    vm.searchResults = searchResults;
    vm.displayProfile = displayProfile;

    vm.users = []; //make this persist on refresh
    vm.user;
    vm.searchThis;
    vm.selectedCountry;

    getCountries();

    function displayProfile(profile) {
        // vm.username = profile;
        // FavouritesService.getUserProfile(vm.username)
        //     .then(
        //         function(d) {
        //             vm.user = d;
        //             localStorage['localOtherUser'] = JSON.stringify(vm.user);
        //             if(localStorage['userInfo'] == localStorage['localOtherUser']){
        //                 $location.path('/userAccount');
        //             }else {
        //                 $location.path('/searchResults/otherAccount');
        //             }
        //         },
        //         function(errResponse){
        //             console.error('Error while fetching fav user names');
        //         }
        //     );
    }

    function goBack(){
        // $location.path('/userAccount');
    }

    function searchResults() {
        console.log(vm.searchThis, vm.selectedCountry);
        SearchProClientsService.getMatchedProUsers(vm.searchThis, vm.selectedCountry)
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
        SearchProClientsService.getCountries()
            .then(
                function(c) {
                    vm.countries = c.countries;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function fetchId() {
        SearchProClientsService.fetchValue()
            .then(
                function(d) {
                    ids.push(d.value);
                    console.log(ids);
                },
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
        SearchProClientsService.fetchValue()
            .then(
                function(d) {
                    ids.push(d.value);
                    console.log(ids);
                },
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
        SearchProClientsService.fetchValue()
            .then(
                function(d) {
                    ids.push(d.value);
                    console.log(ids);
                },
                function(errResponse){
                    console.error('Error while updating User');
                }
            );

    }

}
