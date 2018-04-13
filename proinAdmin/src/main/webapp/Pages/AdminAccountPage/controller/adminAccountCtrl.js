//THIS IS THE CONTROLLER FOR THE FIRST PAGE FROM WHERE ADMINS CAN SIGN IN OR SIGN UP
angular
    .module('myApp')
    .controller('AdminAccountPageCtrl', adminAccountPageController);

adminAccountPageController.$inject = ['$location', 'AdminAccountService', '$scope', 'NgTableParams'];

function adminAccountPageController($location, AdminAccountService, $scope, NgTableParams) {

    var vm = this;
    vm.welcomeMessage = "Welcome to admin account Page";
    vm.showAccountRequests = showAccountRequests;
    vm.fetchAdminRequests = fetchAdminRequests;
    vm.searchResults = searchResults;
    vm.searchThis = '';
    vm.logOut = logOut;
    vm.displayProfile = displayProfile;
    vm.displayRequestingUser = displayRequestingUser;
    vm.sendEmail = sendEmail;
    vm.showList = false;
    vm.accountType = '';
    vm.approvalType = '';
    vm.users = '';
    vm.requestingUsers = '';
    vm.requestButton = 'Show Account Requests';
    vm.requestingAdminsButton = "Show admin requests";
    vm.showClientRequests = false;
    vm.showAdminRequests = false;
    vm.approveRequest = approveRequest;
    vm.denyRequest = denyRequest;
    vm.approveAdminRequest = approveAdminRequest;
    vm.rejectAdminRequest = rejectAdminRequest;
    getCountries();

//ADMIN STATUS 0 == NEITHER ACCEPTED NOR REJECTED
//ADMIN STATUS 1 == ACCEPTED
//ADMIN STATUS 2 == REJECTED

    $scope.filteredTodos = [];
    $scope.pagination = {
        currentPage:  1
    };
    $scope.noOfItems;
    $scope.numPerPage = 3
        ,$scope.maxSize = 5;
//LAZY FETCHING
    function searchResults(status) {
        if (vm.selectedCountry==null) {
            vm.selectedCountry = '';
        }
        console.log(vm.searchThis, vm.selectedCountry, status);
        AdminAccountService.getMatchedClients(vm.searchThis, vm.selectedCountry, status,
            $scope.pagination.currentPage, $scope.numPerPage)
            .then(
                function(u) {
                    vm.users = u.results;
                    $scope.noOfItems = u.noOfItems;

                    if (vm.users !== []) {
                        vm.showList = true;
                    }
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function approveAdminRequest(username) {
        alert('approve admin?');
        AdminAccountService.approveAdminRequest(username)
            .then(function(){
                fetchAdminRequests();
            }, function(){
                console.log("error approving admin");
            });
    }

    function rejectAdminRequest(username) {
        alert('reject admin?');
        AdminAccountService.rejectAdminRequest(username)
            .then(function(){
                fetchAdminRequests();
            }, function(){
                console.log("error rejecting admin");
            });
    }

    function fetchAdminRequests() {
        vm.showAdminRequests = !vm.showAdminRequests;
        if(vm.showAdminRequests) {
            vm.showClientRequests = false;
            vm.requestButton = 'Show Account Requests';
            AdminAccountService.fetchAdminRequests()
                .then(
                    function(r) {
                        vm.requestingAdmins = r;
                        vm.requestingAdminsButton = "Hide Account Requests";
                        console.log(r);
                    },
                    function(errResponse){
                        console.error('Error while fetching Requesting admins');
                    }
                );
        } else {
            vm.requestingAdminsButton = "Show admin requests";
        }

    }

    //FETCHES ACCOUNT REQUESTS
    function showAccountRequests() {
        vm.showClientRequests = !vm.showClientRequests;
        if(vm.showClientRequests == true) {
            vm.showAdminRequests = false;
            vm.requestingAdminsButton = "Show admin requests";
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

    function approveRequest(username) {
        AdminAccountService.approveClientRequest(username)
            .then(
                function() {
                    console.log("Approval Mail Sent");
                    showAccountRequests();
                },
                function(errResponse){
                    console.error('Error while approving client sign up request');
                }
            );
    }

    function denyRequest(username) {
        AdminAccountService.denyClientRequest(username)
            .then(
                function() {
                    console.log("Denial Mail Sent");
                    showAccountRequests();
                },
                function(errResponse){
                    console.error('Error while denying client sign up request');
                }
            );
    }

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

    function displayRequestingUser(username) {
        AdminAccountService.getRequestingUser(username)
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



    function logOut() {
        localStorage['adminLoggedIn'] = JSON.stringify(false);
        $location.path('/');
    }


}