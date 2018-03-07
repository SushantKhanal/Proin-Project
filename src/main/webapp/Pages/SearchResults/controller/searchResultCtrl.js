angular
    .module('myApp')
    .controller('SearchResultCtrl', searchResultController);

searchResultController.$inject = ['$location', 'SearchResultsService'];

function searchResultController($location, SearchResultsService) {
    var vm = this;
    vm.goBack = goBack;
    vm.searchProfiles = searchProfiles;
    vm.searchThis;

    getCountries();

    function goBack(){
        $location.path('/userAccount');
    }

    function searchProfiles() {
       console.log(vm.searchThis);
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
