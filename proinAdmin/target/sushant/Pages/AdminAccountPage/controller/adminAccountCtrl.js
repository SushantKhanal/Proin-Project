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
    vm.displayProfile = displayProfile;
    vm.sendEmail = sendEmail;
    vm.showList = false;
    vm.users = '';
    getCountries();

    function sendEmail() {
        AdminAccountService.sendEmail()
            .then(
                function() {
                    console.log("Mail Sent");
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function displayProfile(profile) {
        vm.username = profile;
        AdminAccountService.getUserProfile(vm.username)
            .then(
                function(d) {
                    vm.user = d;
                    localStorage['adminSeesClient'] = JSON.stringify(vm.user);

                    $location.path('/adminAccount/clientAccount');

                },
                function(errResponse){
                    console.error('Error while fetching fav user names');
                }

            );

    }

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
        if (vm.selectedCountry==null) {
            vm.selectedCountry = '';
        }
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