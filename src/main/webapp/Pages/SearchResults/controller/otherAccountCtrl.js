angular
    .module('myApp')
    .controller('OtherAccountCtrl', otherAccountController);

otherAccountController.$inject = ['$location','UserAccountService', 'OtherAccountService'];

function otherAccountController($location, UserAccountService, OtherAccountService) {
    var vm = this;

    vm.backToSearch = backToSearch;

    vm.picPath1 = '';

    vm.favUsername = '';

    vm.addToFav = addToFav;

    var localUserData = localStorage['localOtherUser'];

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
        //checkIfFav(vm.user.username);
    }

    // //CHECKS IF CURRENT ACCOUNT IS TAGGED FAVOURITE
    // function checkIfFav(otherUsername) {
    //     //OtherAccountService.checkIfFav()
    //     OtherAccountService.checkIfFav(otherUsername)
    //         .then(
    //             function() {
    //                 console.log("this user is tagged fav");
    //             },
    //             function(errResponse){
    //                 console.error('this user is not tagged fav');
    //             });
    // }

    //RESPONSIBLE FOR THE RED COLOR ON HEART ICON
    function addToFav() {
        $('.makeFav').toggleClass('redBackground');
        if ($(".makeFav").hasClass("redBackground")){
            sendFav();
        }
    }

    //GETS DATA TO SEND TO SEND_FAV_USER
    function sendFav() {
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        var favUser = JSON.parse(localUserData);
        sendFavUser(loggedInUser.username, favUser.username);
    }

    //SENDS THE DATA RECEIVED TO BACKEND
    function sendFavUser(lUsername, fUsername){
        OtherAccountService.sendFavUser(lUsername, fUsername)
            .then(
                function() {
                    console.log("Fav user added to database");
                },
                function(errResponse){
                    console.error('Error while getting favUsername');
                });
    }

    //THE GO_BACK BUTTON
    function backToSearch() {
        $location.path('/userAccount/searchResults')
    }

    //GETS THE ACCOUNT PROFILE PICTURE ON LOAD
    function getProfilePic(username){
        UserAccountService.getProfilePic(username)
            .then(
                function(d) {
                    vm.userProfilePic =d;
                    vm.picPath1 = '/user'+d.picPath;
                },
                function(errResponse){
                    console.error('Error while getting profilePic');
                }
            );
    }

}
