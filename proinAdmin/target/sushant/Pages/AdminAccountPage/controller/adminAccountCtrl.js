//THIS IS THE CONTROLLER FOR THE FIRST PAGE FROM WHERE ADMINS CAN SIGN IN OR SIGN UP
angular
    .module('myApp')
    .controller('AdminAccountPageCtrl', adminAccountPageController);

adminAccountPageController.$inject = ['$location', 'AdminAccountService', '$scope'];

function adminAccountPageController($location, AdminAccountService, $scope) {

    var vm = this;
    var x;
    vm.welcomeMessage = "Welcome to admin account Page";
    vm.showAccountRequests = showAccountRequests;
    vm.searchResults = searchResults;
    vm.searchThis = '';
    vm.logOut = logOut;
    vm.displayProfile = displayProfile;
    vm.sendEmail = sendEmail;
    vm.showList = false;
    vm.accountType = '';
    vm.approvalType = '';
    vm.users = '';
    vm.requestingUsers = '';
    vm.requestButton = 'Show Account Requests';
    vm.showClientRequests = false;
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

    //FETCHES ACCOUNT REQUESTS
    function showAccountRequests() {
        vm.showClientRequests = !vm.showClientRequests;
        if(vm.showClientRequests == true) {
            AdminAccountService.fetchAccountRequests()
                .then(
                    function(r) {
                        vm.requestingUsers = r;
                        vm.requestButton = 'Hide Account Requests';
                    },
                    function(errResponse){
                        console.error('Error while fetching Requesting Users');
                    }
                );
        } else {
            vm.requestButton = 'Show Account Requests';
        }

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

    function searchResults(status) {
        if (vm.selectedCountry==null) {
            vm.selectedCountry = '';
        }
        console.log(vm.searchThis, vm.selectedCountry, status);
        AdminAccountService.getMatchedClients(vm.searchThis, vm.selectedCountry, status)
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