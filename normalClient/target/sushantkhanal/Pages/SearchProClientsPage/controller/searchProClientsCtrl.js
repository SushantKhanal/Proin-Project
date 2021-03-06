angular
    .module('myApp')
    .controller('SearchProCleintsCtrl', searchProCleintsCtrl);

searchProCleintsCtrl.$inject = ['SearchProClientsService', '$location', 'NormalAccountService', '$scope'];

function searchProCleintsCtrl(SearchProClientsService, $location, NormalAccountService, $scope) {

    var vm = this;
    var returnedValue = [];
    vm.counter = 0;
    vm.count = -1;
    vm.fetchId = fetchId;
    var ids = [];
    vm.goBack = goBack;
    vm.searchResults = searchResults;
    vm.displayProfile = displayProfile;
    vm.picPath1='';

    vm.users = ''; //make this persist on refresh
    vm.user;
    vm.searchThis;
    vm.selectedCountry;
    var userData = localStorage['NormalUserInfo'];

    $scope.filteredTodos = [];
    $scope.pagination = {
        currentPage:  1
    };
    $scope.noOfItems;
    $scope.numPerPage = 3
        ,$scope.maxSize = 5;

    if(userData !== undefined) {
        vm.normalUser = JSON.parse(userData);
    }
    getProfilePic(vm.normalUser.username);
    getCountries();

    function getProfilePic(username){
        NormalAccountService.getProfilePic(username)
            .then(
                function(d) {
                    vm.userProfilePic =d;
                    if(d.picPath == undefined) {
                        return;
                    }
                    vm.picPath1 = '/user'+d.picPath+"?"+ new Date().getTime();
                },
                function(errResponse){
                    console.error('Error while getting profilePic');
                }
            );
    }

    //DOWN-ARROW NAVIGATION
    $("#arrowNavigation").on('keydown', function(e) {
        if (e.which == 40 || event.keyCode == 40) {
            if(vm.showList == true) {
                vm.count++;
                //alert("hehahaha");
                if(vm.count == (vm.users.length)){
                    vm.count = 0;
                }
                if(vm.count == 0){
                    if($('#user'+(vm.users.length-1)).hasClass('selectElement')) {
                        $('#user'+(vm.users.length - 1)).removeClass('selectElement');
                    }
                }
                if(vm.count>0){
                    $('#user'+(vm.count - 1)).removeClass('selectElement');
                }
                $('#user'+vm.count).addClass('selectElement');

            }
        }
    });

    //UP-ARROW NAVIGATION
    $("#arrowNavigation").on('keydown', function(e) {
        if (e.which == 38 || event.keyCode == 38) {
            if(vm.showList == true) {
                vm.count--;
                if(vm.count == -1) {
                    vm.count = vm.users.length - 1;
                    $('#user0').removeClass('selectElement');
                }
                //alert("hehahaha");
                if(vm.count>-1){
                    $('#user'+(vm.count + 1)).removeClass('selectElement');
                }
                $('#user'+vm.count).addClass('selectElement');
            }
        }
    });

    //CLICK WHEN ENTER IS PRESSED
    $("#arrowNavigation").on('keydown', function(e) {
        if (e.which == 13 || event.keyCode == 13) {
            $('.selectElement').click();
        }
    });

    function displayProfile(profile) {
        vm.username = profile;
        SearchProClientsService.getProUserProfile(vm.username)
            .then(
                function(d) {
                    localStorage['localProUser'] = JSON.stringify(d);
                        $location.path('/searchProClients/proAccount');
                },
                function(errResponse){
                    console.error('Error while fetching proAccount');
                }
            );
    }

    function goBack(){
        $location.path('/normalUserAccount');
    }

    function searchResults() {
        console.log(vm.searchThis, vm.selectedCountry);

        SearchProClientsService.getMatchedProUsers(vm.searchThis, vm.selectedCountry,
            $scope.pagination.currentPage, $scope.numPerPage)
            .then(
                function(u) {
                    vm.users = u.results;
                    vm.noOfItems = u.noOfItems;
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
