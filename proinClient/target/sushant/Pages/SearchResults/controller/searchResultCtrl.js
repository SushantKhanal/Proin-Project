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
    vm.count = -1;
    $scope.country;

    getCountries();

    //upArrow = 38
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
        FavouritesService.getUserProfile(vm.username)
            .then(
                function(d) {
                    vm.user = d;
                    localStorage['localOtherUser'] = JSON.stringify(vm.user);
                    if(localStorage['userInfo'] == localStorage['localOtherUser']){
                        $location.path('/userAccount');
                    }else {
                        $location.path('/searchResults/otherAccount');
                    }
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
