//THIS IS THE CONTROLLER FOR THE FIRST PAGE FROM WHERE ADMINS CAN SIGN IN OR SIGN UP
angular
    .module('myApp')
    .controller('AdminAccountPageCtrl', adminAccountPageController);

adminAccountPageController.$inject = ['$location', 'AdminAccountService'];

function adminAccountPageController($location, AdminAccountService) {

    var vm = this;
    vm.welcomeMessage = "Welcome to admin account Page";
    vm.showAccountRequests = showAccountRequests;
    vm.searchResults = searchResults;
    vm.searchThis = '';
    vm.logOut = logOut;
    vm.selectedCountry = '';
    vm.showList = false;
    vm.users = '';
    getCountries();

    function showAccountRequests() {
        console.log("This feature is not added yet");
    }

    function getCountries() {
        AdminAccountService.getCountries()
            .then(
                function(c) {
                    vm.countries = c;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function searchResults() {
        console.log(vm.searchThis, vm.selectedCountry);
        AdminAccountService.getMatchedClients(vm.searchThis, vm.selectedCountry)
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


    function logOut() {
        localStorage['adminLoggedIn'] = JSON.stringify(false);
        $location.path('/');
    }


}